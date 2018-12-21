package com.tom.code;

import com.tom.code.entity.User;
import freemarker.core.ArithmeticEngine;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class FreemarkerUtil {

    public Configuration getConfiguration(){
        try {
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
            // 为所有模板定义变量
            cfg.setSharedVariable("tom","zhang");
            // 设置编码格式
            cfg.setDefaultEncoding("UTF-8");
            // 设置区域,关系到 日期时间的显示
            cfg.setLocale(Locale.CHINESE);
            // 设置异常处理：忽略异常，打印异常，抛出异常，打印异常（HTML格式）
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.DEBUG_HANDLER);
            // 设置数值型的处理方式，默认ArithmeticEngine.CONSERVATIVE_ENGINE
            cfg.setArithmeticEngine(ArithmeticEngine.CONSERVATIVE_ENGINE);
            //
            cfg.setClassForTemplateLoading(this.getClass(), "/ftl");
            return cfg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void generateFile(Object object,Class obj){

    }


    public void generateFile(Object object,String className){
        FileWriter out = null;
        try {
            Configuration cfg = getConfiguration();
            // 获取当前类所处的路径
            String classString = object.getClass().getResource("").getFile();
            System.out.println("当前类所处路径 = " + classString);
            String fileStrings = classString.replace("target/classes","src/main/java");
            System.out.println("当前项目的路径 = " + fileStrings);

            // 存放需要新建的目录名和文件
            Map<String,String> map = new HashMap<String, String>();
            // service层
            map.put("service",className+"Service.java");
//            map.put("service/impl",className+"ServiceImpl.java");
//            // 实体层
//            map.put("entity",className+".java");
//            // 数据访问层
//            map.put("repository",className+"Repository.java");
//            map.put("repository/impl",className+"RepositoryImpl.java");
//            map.put("repository/enhance",className+"RepositoryEnhance.java");


            Map<String,String> valueMap = new HashMap<String, String>();
            String packagess =  classString.substring(classString.indexOf("classes")+8) ;
            String packages = packagess.substring(0,packagess.length()-1).replace("/",".");
            System.out.println("package = " + packages);
            valueMap.put("package",packages);

            valueMap.put("class",className);
            valueMap.put("classObj",className.toLowerCase().substring(0,1) + className.substring(1));

            for(String key : map.keySet()){
                String directory = fileStrings + key ;
                File file = new File(directory);
                if(!file.exists()){
                    file.mkdir();
                }
                out = new FileWriter(new File(directory + "/"+map.get(key)));
                Template temp = cfg.getTemplate(key.replace("/","")+".ftl");
                valueMap.put("directory",key);
                temp.process(valueMap, out);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        FreemarkerUtil freemarkerUtil = new FreemarkerUtil();
//        System.out.println(freemarkerUtil.getClass().getResource("").getFile()+"1.txt");
//        System.out.println(freemarkerUtil.getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
        freemarkerUtil.generateFile( freemarkerUtil,"User");
    }
}
