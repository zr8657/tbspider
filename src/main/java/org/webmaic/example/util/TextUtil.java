package org.webmaic.example.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TextUtil {

    public final static String URLTXT_PATH = "src/main/resources/";

    /**
     * @param textName :xxx.txt
     */
    public static void textWriter(String textName, List<String> list) {
        BufferedWriter writer;
        File file = new File(URLTXT_PATH + textName);
        try {
            //如果没有文件就创建
            if (!file.isFile()) {
                file.createNewFile();
            }
            writer = new BufferedWriter(new FileWriter(URLTXT_PATH + textName,true));
            try {
                for (String l : list) {
                    writer.write(l + "\r\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取txt文件内容并按行放入list中
     */
    public static ArrayList<String> textReader(String textName) {
        File file = new File(URLTXT_PATH + textName);
        ArrayList list = new ArrayList();
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader io = new InputStreamReader(fileInputStream);
            BufferedReader reader = new BufferedReader(io);
            String terminalInfo = null;
            try {
                while((terminalInfo=reader.readLine())!=null){
                    list.add(terminalInfo);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                reader.close();
                io.close();
                fileInputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
