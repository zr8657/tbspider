package org.webmaic.example.service;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webmaic.example.Entry.ZhiHuUser;
import org.webmaic.example.Entry.ZhiHuUserIdList;
import org.webmaic.example.util.MatcherUtil;
import org.webmaic.example.util.RedisUtil;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.selector.JsonPathSelector;

import java.time.LocalDateTime;

@Service
public class ZhiHuUserInfoService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String USER_JSONPATH = "$.initialState.entities.users.";

    @Autowired
    RedisUtil redisUtil;

    public void getUserInfoService(String userId) {

        if (redisUtil.get(userId, 1) != null) {
            logger.info("此用户已存储过，将跳过");
            return;
        }

        //路径配置
        System.setProperty("webdriver.firefox.bin", "D:\\Mozilla Firefox\\firefox.exe");

        WebDriver driver = new FirefoxDriver();

        driver.get("https://www.zhihu.com/people/" + userId + "/activities");

        logger.info("当前打开页面的标题是： " + driver.getTitle());

        try {
            //强制等待等7秒
            Thread.sleep(7000);

            String html = driver.getPageSource();
            //抽个人信息JSON，正则有点难看不过能用，后期改
            String json = MatcherUtil.generalMatcher(html, " id=\"js-initialData\" type=\"text/json\">(.*?)</script>", 1);
            //获取用户属性
            ZhiHuUser zhiHuUser = analysisUserJson(json, userId);

            //已存储用户id存入redis1
            redisUtil.set(userId, userId, 1);

            //爬取20个关注他的
            Spider.create(new ZhiHuUserIdList())
                    .addUrl("https://www.zhihu.com/api/v4/members/"+userId+"/followers?offset=0&limit=20")
                    .run();
            //爬取20个他关注的
            Spider.create(new ZhiHuUserIdList())
                    .addUrl("https://www.zhihu.com/api/v4/members/"+userId+"/followees?offset=0&limit=20")
                    .run();
        } catch (Exception e) {
            e.printStackTrace();
            redisUtil.set(userId, userId, 2);
            logger.debug("用户信息获取出现异常，已将失败用户存入Redis2");
        } finally {
            //关闭并退出浏览器
            driver.quit();
        }

    }

    private ZhiHuUser analysisUserJson(String json, String userId) {

        ZhiHuUser zhiHuUser = new ZhiHuUser();

        /**
         * 时间
         */
        LocalDateTime localDateTime = LocalDateTime.now();
        zhiHuUser.setCreateTime(localDateTime);
        /**
         * 用户Id
         */
        zhiHuUser.setUserId(userId);

        //签名
        String headLine = new JsonPathSelector(USER_JSONPATH + userId + ".headline").select(json);
        zhiHuUser.setHeadLine(headLine);

        //urlToken
        String urlToken = new JsonPathSelector(USER_JSONPATH + userId + ".urlToken").select(json);
        zhiHuUser.setUrlToken(urlToken);

        //回答被收藏数
        String favoritedCount = new JsonPathSelector(USER_JSONPATH + userId + ".favoritedCount").select(json);
        zhiHuUser.setFavoritedCount(favoritedCount);

        //多少人关注当前用户
        String followerCount = new JsonPathSelector(USER_JSONPATH + userId + ".followerCount").select(json);
        zhiHuUser.setFollowerCount(followerCount);

        //公司
        String companyName = new JsonPathSelector(USER_JSONPATH + userId + ".employments[0].company.name").select(json);
        zhiHuUser.setCompanyName(companyName);

        //职位
        String position = new JsonPathSelector(USER_JSONPATH + userId + ".employments[0].job.name").select(json);
        zhiHuUser.setPosition(position);

        //行业
        String business = new JsonPathSelector(USER_JSONPATH + userId + ".business.name").select(json);
        zhiHuUser.setBusiness(business);

        //回答数
        String answerCount = new JsonPathSelector(USER_JSONPATH + userId + ".answerCount").select(json);
        zhiHuUser.setAnswerCount(answerCount);

        //写的文章数
        String articlesCount = new JsonPathSelector(USER_JSONPATH + userId + ".articlesCount").select(json);
        zhiHuUser.setArticlesCount(articlesCount);

        //现居地
        String locations = new JsonPathSelector(USER_JSONPATH + userId + ".locations[0].name").select(json);
        zhiHuUser.setLocations(locations);

        //性别gender
        String gender = new JsonPathSelector(USER_JSONPATH + userId + ".gender").select(json);
        zhiHuUser.setGender(gender);

        //专业
        String major = new JsonPathSelector(USER_JSONPATH + userId + ".educations[0].major.name").select(json);
        zhiHuUser.setMajor(major);

        //学校
        String school = new JsonPathSelector(USER_JSONPATH + userId + ".educations[0].school.name").select(json);
        zhiHuUser.setSchool(school);

        //获得感谢数
        String thankedCount = new JsonPathSelector(USER_JSONPATH + userId + ".thankedCount").select(json);
        zhiHuUser.setThankedCount(thankedCount);

        //参与公共编辑数
        String logsCount = new JsonPathSelector(USER_JSONPATH + userId + ".logsCount").select(json);
        zhiHuUser.setLogsCount(logsCount);

        //获得赞同数
        String voteUpCount = new JsonPathSelector(USER_JSONPATH + userId + ".voteupCount").select(json);
        zhiHuUser.setVoteUpCount(voteUpCount);

        //被知乎收录的文章数
        String includedArticlesCount = new JsonPathSelector(USER_JSONPATH + userId + ".includedArticlesCount").select(json);
        zhiHuUser.setIncludedArticlesCount(includedArticlesCount);

        //昵称
        String name = new JsonPathSelector(USER_JSONPATH + userId + ".name").select(json);
        zhiHuUser.setName(name);

        //提问数
        String questionCount = new JsonPathSelector(USER_JSONPATH + userId + ".questionCount").select(json);
        zhiHuUser.setQuestionCount(questionCount);

        return zhiHuUser;
    }
}
