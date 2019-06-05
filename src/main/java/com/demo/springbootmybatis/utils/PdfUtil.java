package com.demo.springbootmybatis.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.ElementList;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.css.StyleAttrCSSResolver;
import com.itextpdf.tool.xml.exceptions.CssResolverException;
import com.itextpdf.tool.xml.html.CssAppliers;
import com.itextpdf.tool.xml.html.CssAppliersImpl;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.ElementHandlerPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;
import freemarker.template.Configuration;
import freemarker.template.Template;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.Charset;

public class PdfUtil {

    //获取模板并填充数据
    public static String getContent(String fileName,Object data) throws Exception{
        // 创建一个Configuration对象
        Configuration configuration = new Configuration(Configuration.getVersion());
        // 告诉config对象模板文件存放的路径。
//        String url = PdfUtil.class.getClassLoader().getResource("templates/").getPath();
//        String url = "/templates/";
//        configuration.setDirectoryForTemplateLoading(new File(url));
        configuration.setClassForTemplateLoading(PdfUtil.class,"/templates");
        // 设置config的默认字符集。一般是utf-8
        configuration.setDefaultEncoding("utf-8");
        //从config对象中获得模板对象。需要制定一个模板文件的名字。
        Template template = configuration.getTemplate(fileName+".ftl");
        StringWriter writer = new StringWriter();
        //模版和数据匹配
        template.process(data, writer);
        writer.flush();
        String html = writer.toString();
//        return getImgs(html);
        return html;
    }

    /**
     * 生成PDF到输出流中（ServletOutputStream用于下载PDF）
     * @param templateName ftl模板名称
     * @param data 输入到FTL中的数据
     * @return
     */
    public static byte[] exportToResponse(String templateName, Object data)throws Exception{
        String html = getContent(templateName,data);
        try{
            return html2pdf(html);
        }catch (Exception ex){
            System.out.println(ex);
//            throw  new SimpleException("PDF export to response fail");
        }
        return null;
    }

    public static byte[] html2pdf(String html) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4);
        //document.setPageCount(40);
        //document.setPageSize(PageSize.A4);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PdfWriter writer = PdfWriter.getInstance(document,os);
        document.open();

        XMLWorkerFontProvider fontProvider = new XMLWorkerFontProvider(){
            @Override
            public Font getFont(String fontname, String encoding, float size, int style) {
                return super.getFont(fontname == null ? "宋体" : fontname, encoding, size, style);
            }
        };
        CssAppliers cssAppliers = new CssAppliersImpl(fontProvider);

        HtmlPipelineContext htmlContext = new HtmlPipelineContext(cssAppliers);

        CSSResolver cssResolver = new StyleAttrCSSResolver();
        ElementList elements = new ElementList();
        ElementHandlerPipeline end = new ElementHandlerPipeline(elements, null);
        HtmlPipeline htmlPipeline = new HtmlPipeline(htmlContext, end);
//    CssResolverPipeline cssPipeline = new CssResolverPipeline(cssResolver, htmlPipeline);
        CssResolverPipeline cssPipeline = new CssResolverPipeline(cssResolver, new PdfWriterPipeline(document,writer));
        // XML Worker
        XMLWorkerHelper worker =  XMLWorkerHelper.getInstance();
        //html = html.replace('\"','\'');
        worker.parseXHtml(writer, document, new ByteArrayInputStream(html.getBytes("UTF-8")),new ByteArrayInputStream(html.getBytes()),Charset.forName("UTF-8"),fontProvider);
        document.close();
        return os.toByteArray();
    }
}
