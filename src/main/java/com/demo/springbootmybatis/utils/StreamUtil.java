package com.demo.springbootmybatis.utils;

import java.io.*;

import static cn.afterturn.easypoi.excel.entity.enmus.CellValueType.String;

public class StreamUtil {

    /**
     *  如果是字节流文件，例如图片、流媒体等，可以用如下方式获取。
     * @param path
     * @throws IOException
     */
    public static void testRead(String path) throws IOException {
        int temp = -1;
        FileInputStream fis = new FileInputStream(path);
        BufferedInputStream bs = new BufferedInputStream(fis);
        while ((temp = bs.read()) != -1) {
            System.out.println((char)temp);
        }
        bs.close();
        fis.close();
    }

    /**
     *   如果是纯文本字符文件，例如txt,log,csv等格式，可以用如下方式获取。
     * @param path
     * @throws IOException
     */
    public static void testRead2(String path) throws IOException {
        String temp = null;
        FileReader in = new FileReader(path);
        BufferedReader br = new BufferedReader(in);
        while ((temp = br.readLine()) != null) {
            System.out.println(temp);
        }
    }

    /**
     * 复制文件
     * @param path1
     * @param path2
     * @throws IOException
     */
    public static void test3(String path1,String path2) throws IOException {
        File file = new File(path1);
        FileInputStream fis = new FileInputStream(file);
        FileOutputStream fos = new FileOutputStream(path2);
        int len = -1;
        byte[] b = new byte[1024];
        while ((len = fis.read(b)) != -1) {
//            fos.write(b);
            fos.write(b,0,len);
            System.out.println(new String(b));
        }
        fos.close();
        fis.close();
    }

    /**
     * 将文件转为byte[]数组
     * @param path1
     * @return
     * @throws IOException
     */
    public static byte[] getFileBytes(String path1) throws IOException {
        File file = new File(path1);
        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int len = -1;
        byte[] b = new byte[1024];
        while ((len = fis.read(b)) != -1) {
            bos.write(b,0,b.length);
            System.out.println(new String(b));
        }
        bos.close();
        fis.close();
        return bos.toByteArray();
    }

    /**
     * 字节缓冲流
     * @param path
     */
    public static void testBuffer(String path) throws IOException {
        File file = new File(path);
        //字节输入流
        FileInputStream fis = new FileInputStream(file);
        //字节缓冲输入流
        BufferedInputStream bis = new BufferedInputStream(fis);
        //字节输出流
        FileOutputStream fos = new FileOutputStream(file);
        //字节缓冲输出流
        BufferedOutputStream bos = new BufferedOutputStream(fos);

        bos.write(50);
        bos.write('a');

        bos.flush();
        System.out.println(bis.read());
        System.out.println((char)bis.read());

        fos.close();
        bos.close();
        fis.close();
        bis.close();
    }

    /**
     * 字符流
     * @param path
     */
    public static void testReaderAndWriter(String path, String path2) throws IOException {
        File file = new File(path);
        //字节输入流
        FileInputStream fis = new FileInputStream(file);
        //字符输入流
        InputStreamReader isr = new InputStreamReader(fis,"utf-8");
        //字符缓冲输入流
        BufferedReader br = new BufferedReader(isr);

        //字节输出流
        FileOutputStream fos = new FileOutputStream(path2);
        //字符输出流
        OutputStreamWriter osw = new OutputStreamWriter(fos,"utf-8");
        //缓冲字符输出流
        BufferedWriter bw = new BufferedWriter(osw);

        int n = 0;
        //方式1
//        while((n = isr.read()) != -1) {
//            System.out.println((char) n);
//        }
        //方式2
        char[] b = new char[10];
        while((n = br.read()) != -1) {
            bw.write(b,0,n);
            bw.flush();
        }
        fis.close();
        isr.close();
        fos.close();
        osw.close();
        br.close();
        bw.close();
    }

    /**
     * 对象序列化
     * @param path
     * @return
     */
    public static Object getObject(String path,Object obj) throws IOException, ClassNotFoundException {
        //写文件
        FileOutputStream fos = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        //读文件
        FileInputStream fis = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(fis);
        oos.writeObject(obj);
        oos.flush();
        Object o = ois.readObject();
        System.out.println(o.toString());
        fis.close();
        ois.close();
        fos.close();
        oos.close();
        return o;
    }
}
