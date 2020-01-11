package com.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 * @author 陈宜康
 * @date 2019/9/13 20:53
 * @forWhat
 */
public class cssSort {

    private static final String READFILE = "F:\\Study\\css结构整理\\css.css";
    private static final String WRITEFILE = "F:\\Study\\css结构整理\\css.css";

    public static void main(String[] args) throws IOException {
        cssSort(READFILE);
    }

    public static void cssSort(String filename) throws IOException {
        // 先根据文件名,转化成FileReader对象
        // BufferedReader装饰器类
        // FileReader普通的InputStream类
        // 我们知道,装饰器类的基本用法,就是在装饰器的构造方法中传入原本的对象.装饰器对象
        // 在执行方法时,不仅会执行原本对象的方法,还会执行"修饰"的方法
        BufferedReader in = new BufferedReader(new FileReader(filename));
        Stack<String> stack = new Stack<>();
        String result = "";
        ArrayList<String> list = new ArrayList<>();
        int c = 0;
        while ((c = in.read()) != -1) {
            char at = (char) c;
            if ('\n' == at || '\r' == at) {
                continue;
            }
            result += at;
            if ('{' == at) {
                stack.push(String.valueOf(at));
            } else if ('}' == at) {
                stack.pop();
                if (stack.empty()) {
                    list.add(result.trim().replaceAll("\\s{2,}", " "));
                    result = "";
                }
            }
        }
        Collections.sort(list, (String o1, String o2) -> {
            for (int i = 0, _lenth = Math.min(o1.length(), o2.length()); i < _lenth; i++) {
                char a1 = o1.charAt(i);
                char a2 = o2.charAt(i);
                if (a1 == a2) {
                    continue;
                } else {
                    if (String.valueOf(a1).matches("[a-zA-Z]+") && !String.valueOf(a2).matches("[a-zA-Z]+")) {
                        return 1;
                    } else {
                        // 既不相等,又都是字母
                        return o1.compareTo(o2);
                    }
                }
            }
            return o1.compareTo(o2);
        });
        for (String str :
                list) {
            System.out.println(str);
        }
        // 关闭流
        in.close();
        PrintWriter out = new PrintWriter(READFILE);
        for (String str :
                list) {
            out.println(str);
        }
        out.close();
    }
}
