package com.demo.springbootmybatis.utils;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class EsaypoiUtil {

    /**
     * 导出excel
     * @param response
     * @param templatePath
     * @param fileName
     * @param data
     */
    public static void export(HttpServletResponse response, String templatePath, String fileName, Map<String,Object> data) {
        // 获取导出excel指定模版，第二个参数true代表显示一个Excel中的所有 sheet
        TemplateExportParams params = new TemplateExportParams(templatePath, true);
        try {
            // 简单模板导出方法
            Workbook book = ExcelExportUtil.exportExcel(params, data);
            //下载方法
            downloadExcel(response, book, fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 下载excel
     * @param response
     * @param workbook
     * @param fileName
     * @throws Exception
     */
    private static void downloadExcel(HttpServletResponse response, Workbook workbook, String fileName) throws IOException {
        response.reset();
        response.setContentType("application/x-msdownload");
        fileName = fileName + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        ServletOutputStream outStream = null;
        try {
            response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("gb2312"), "ISO-8859-1") + ".xls");
            outStream = response.getOutputStream();
            workbook.write(outStream);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(outStream != null) {
                try {
                    outStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
