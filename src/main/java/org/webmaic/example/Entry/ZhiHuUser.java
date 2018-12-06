package org.webmaic.example.Entry;

import java.time.LocalDateTime;

public class ZhiHuUser {

    /**
     * userId
     */
    private String userId;
    /**
     * 签名
     */
    private String headLine;
    /**
     * urlToken
     */
    private String urlToken;
    /**
     * 回答被收藏数
     */
    private String favoritedCount;
    /**
     * 多少人关注当前用户
     */
    private String followerCount;
    /**
     * 公司
     */
    private String companyName;
    /**
     * 职位
     */
    private String position;
    /**
     * 行业
     */
    private String business;
    /**
     * 回答数
     */
    private String answerCount;
    /**
     * 写的文章数
     */
    private String articlesCount;
    /**
     * 现居地
     */
    private String locations;
    /**
     * 性别gender
     * 1男0女-1未知
     */
    private String gender;
    /**
     * 专业
     */
    private String major;
    /**
     * 学校
     */
    private String school;
    /**
     * 获得感谢数
     */
    private String thankedCount;
    /**
     * 参与公共编辑数
     */
    private String logsCount;
    /**
     * 获得赞同数
     */
    private String voteUpCount;
    /**
     * 被知乎收录的文章数
     */
    private String includedArticlesCount;
    /**
     * 昵称
     */
    private String name;
    /**
     * 提问数
     */
    private String questionCount;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getHeadLine() {
        return headLine;
    }

    public void setHeadLine(String headLine) {
        this.headLine = headLine;
    }

    public String getUrlToken() {
        return urlToken;
    }

    public void setUrlToken(String urlToken) {
        this.urlToken = urlToken;
    }

    public String getFavoritedCount() {
        return favoritedCount;
    }

    public void setFavoritedCount(String favoritedCount) {
        this.favoritedCount = favoritedCount;
    }

    public String getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(String followerCount) {
        this.followerCount = followerCount;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(String answerCount) {
        this.answerCount = answerCount;
    }

    public String getArticlesCount() {
        return articlesCount;
    }

    public void setArticlesCount(String articlesCount) {
        this.articlesCount = articlesCount;
    }

    public String getLocations() {
        return locations;
    }

    public void setLocations(String locations) {
        this.locations = locations;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getThankedCount() {
        return thankedCount;
    }

    public void setThankedCount(String thankedCount) {
        this.thankedCount = thankedCount;
    }

    public String getLogsCount() {
        return logsCount;
    }

    public void setLogsCount(String logsCount) {
        this.logsCount = logsCount;
    }

    public String getVoteUpCount() {
        return voteUpCount;
    }

    public void setVoteUpCount(String voteUpCount) {
        this.voteUpCount = voteUpCount;
    }

    public String getIncludedArticlesCount() {
        return includedArticlesCount;
    }

    public void setIncludedArticlesCount(String includedArticlesCount) {
        this.includedArticlesCount = includedArticlesCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(String questionCount) {
        this.questionCount = questionCount;
    }

    @Override
    public String toString() {
        return "ZhiHuUser{" +
                "userId='" + userId + '\'' +
                ", headLine='" + headLine + '\'' +
                ", urlToken='" + urlToken + '\'' +
                ", favoritedCount='" + favoritedCount + '\'' +
                ", followerCount='" + followerCount + '\'' +
                ", companyName='" + companyName + '\'' +
                ", position='" + position + '\'' +
                ", business='" + business + '\'' +
                ", answerCount='" + answerCount + '\'' +
                ", articlesCount='" + articlesCount + '\'' +
                ", locations='" + locations + '\'' +
                ", gender='" + gender + '\'' +
                ", major='" + major + '\'' +
                ", school='" + school + '\'' +
                ", thankedCount='" + thankedCount + '\'' +
                ", logsCount='" + logsCount + '\'' +
                ", voteUpCount='" + voteUpCount + '\'' +
                ", includedArticlesCount='" + includedArticlesCount + '\'' +
                ", name='" + name + '\'' +
                ", questionCount='" + questionCount + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
