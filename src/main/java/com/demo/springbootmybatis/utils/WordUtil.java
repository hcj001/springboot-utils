package com.demo.springbootmybatis.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;

/**
 * 创建word动态文档(freemarker )
 * 		step1、配置模板word文档，把需要动态替换的内容改成变量；
 * 		step2、将word模板另存为xml文件;
 * 		step3、将xml文件另存为.ftl文件;
 * 		step4、Ctrl+F查找变量name，修改word模板中的<w:t>name</w:t> 的name为${name}; 注意<w:t></w:t>标签内的内容才改
*/
public class WordUtil {

    public static Configuration configuration = null;
    /** 模板名字*/
    private String templateName;
    /** 生成后的文件名*/
    private String fileName;
    /** 生成后的文件路径*/
    private String filePath;
    public WordUtil(String templatePath) {
        this.configuration = new Configuration(Configuration.getVersion());
        configuration.setDefaultEncoding("UTF-8");
        configuration.setClassForTemplateLoading(this.getClass(),templatePath);
    }

    /**
     * 创建word到指定目录
     * @param paramMap
     */
    public void createWord(Map<String, Object> paramMap) {
        Template template = null;
        File outFile = new File(filePath+fileName);
        Writer writer = null;
        try {
            template = configuration.getTemplate(templateName);
            writer = new BufferedWriter(new OutputStreamWriter((new FileOutputStream(outFile)),"utf-8"));
            template.process(paramMap,writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    /**
     * 下载生成word并下载
     * @param response
     * @param paramMap
     */
    public void createWord(HttpServletResponse response, Map<String, Object> paramMap) {
        Template template = null;
        OutputStreamWriter out = null;
        Writer writer = null;
        try {
            template = configuration.getTemplate(templateName);
            writer = response.getWriter();
            template.process(paramMap,writer);
            out.close();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
