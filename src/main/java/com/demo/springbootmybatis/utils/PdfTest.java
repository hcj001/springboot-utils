package com.demo.springbootmybatis.utils;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.*;

public class PdfTest {
    // 利用模板生成pdf
    public static void fillTemplate(String templatePath,String newPDFPath) {
        PdfReader reader;
        FileOutputStream out;
        ByteArrayOutputStream bos;
        PdfStamper stamper;
        try {
            out = new FileOutputStream(newPDFPath);// 输出流
            reader = new PdfReader(templatePath);// 读取pdf模板
            bos = new ByteArrayOutputStream();
            stamper = new PdfStamper(reader, bos);
            AcroFields form = stamper.getAcroFields();

            form.setField("name","xxxx");
            form.setField("age","20");
            stamper.setFormFlattening(true);// 如果为false那么生成的PDF文件还能编辑，一定要设为true
            stamper.close();

            Document doc = new Document();
            PdfCopy copy = new PdfCopy(doc, out);
            doc.open();
            PdfImportedPage importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), 1);
            copy.addPage(importPage);
            doc.close();

        } catch (IOException e) {
            System.out.println(1);
        } catch (DocumentException e) {
            System.out.println(2);
        }

    }

    /**
     * 1.通过Adobe Acrobat pro软件打开pdf文件制作模板
     * 2.
     * 只能填充固定字段模板
     * @param templatePath
     * @param map
     * @return
     */
    public static byte[] fillTemplate2(String templatePath, Map<String,Object> map) {
        PdfReader reader;
        ByteArrayOutputStream bos;
        PdfStamper stamper;
        try {
            //设置中文字体，否则中文无法显示
//            BaseFont bf = BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
            BaseFont bf = BaseFont.createFont("/static/simsun.ttc,1", BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
//            Font FontChinese = new Font(bf, 12, Font.NORMAL);
            reader = new PdfReader(templatePath);// 读取pdf模板
            bos = new ByteArrayOutputStream();
            stamper = new PdfStamper(reader, bos);
            AcroFields form = stamper.getAcroFields();
            //设置basefont属性
            form.addSubstitutionFont(bf);
            Map<String,String> fields = (Map<String,String>) map.get("fields");
            for(String key : fields.keySet()) {
                form.setField(key,fields.get(key));
            }
            stamper.setFormFlattening(true);// 如果为false那么生成的PDF文件还能编辑，一定要设为true
            stamper.close();
            Document doc = new Document();
            doc.open();
            doc.close();
            return bos.toByteArray();
        } catch (IOException e) {
            System.out.println(1);
        } catch (DocumentException e) {
            System.out.println(e);
        }
        return null;
    }
}
