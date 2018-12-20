package com.tom.code;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

public class FreemarkerUtil {

    public void generateFile(){
        FileWriter out = null;
        try {
            // 通过一个文件输出流，就可以写到相应的文件中，此处用的是绝对路径
            String classString = this.getClass().getResource("").getFile();
            String fileStrings = classString.replace("target/classes","src/main/java") + "service";
            File file = new File(fileStrings);
            if(!file.exists()){
                file.mkdir();
            }
            String fileString = fileStrings+"/1.txt";
            out = new FileWriter(new File(fileString));
            Map<String,String> map = new HashMap<String, String>();
            map.put("name","zhang1");
            // 通过Freemaker的Configuration读取相应的ftl
            Configuration cfg = new Configuration();
            // 设定去哪里读取相应的ftl模板文件 这里是去class的目录下，用getClass().getResource("/").getFile()可获取文件地址
            cfg.setClassForTemplateLoading(this.getClass(), "/ftl");
            // 在模板文件目录中找到名称为name的文件
            Template temp = cfg.getTemplate("2.ftl");
            temp.process(map, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        FreemarkerUtil freemarkerUtil = new FreemarkerUtil();
        System.out.println(freemarkerUtil.getClass().getResource("").getFile()+"1.txt");
//        System.out.println(freemarkerUtil.getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
        freemarkerUtil.generateFile();
    }
}
