package com.sunline.funcpub.util;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;


/**
 * 应用模块名称<p>
 * 代码描述<p>
 * Copyright: Copyright (C) 2021 xnew, Inc. All rights reserved. <p>
 * Company: 北京新纽科技有限公司<p>
 *
 * @author greenwave
 * @since 2021/2/5 9:48
 */
public class TT {
    public static void main(String[] args) throws IOException {
        String in = "D:\\data\\sqlscript\\data";
        String out = "D:\\out.sql";
        // 合并
        mergeFileToOne(in, out);
    }

    /**
     * 递归获取文件夹以及子文件夹下的文件
     *
     * @param path
     * @return
     */
    public static List getFiles(String path) {
        List list = new ArrayList();
        File[] files = new File(path).listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                List node = getFiles(files[i].getPath());
                list.addAll(node);
            } else {
                list.add(files[i].getPath());
            }
        }
        return list;
    }


    /**
     * 合并文件
     *
     * @param inPath
     * @param outPath
     * @throws IOException
     */
    public static void mergeFileToOne(String inPath, String outPath) throws IOException {
//        BufferedWriter bw = new BufferedWriter(new FileWriter(outPath));
//        BufferedWriter bw = new BufferedWriter(new FileWriter(outPath));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outPath), "UTF-8"));
        System.out.println("--->合并目标文件夹路径：" + inPath);
        System.out.println("--->正在读取文件...");
        List<String> files = getFiles(inPath);
        System.out.println("--->合并文件数量：" + files.size());
        for (int i = 0; i < files.size(); i++) {
            File file = new File(files.get(i));
            BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
//                System.out.println(line);
                bw.write(line);
                bw.newLine();
            }
            bufferedReader.close();
        }
        System.out.println("--->合并完成！");
        System.out.println("--->合并文件输出路径：" + outPath);
        bw.close();
    }
}
