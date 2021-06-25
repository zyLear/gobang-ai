package com.zylear.gobangai.test;

import java.util.HashSet;

/**
 * @author xiezongyu
 * @date 2021/6/25
 */
public class SetTest {

    public static void main(String[] args) {

        long l = System.currentTimeMillis();
        HashSet<String> set = new HashSet<>(10000000);
        for (int i = 0; i < 10000000; i++) {
            set.add(new String(String.valueOf(i)));
        }

        System.out.println((System.currentTimeMillis() - l));

    }

}
