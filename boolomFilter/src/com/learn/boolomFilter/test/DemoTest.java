package com.learn.boolomFilter.test;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

public class DemoTest {


    public static void main(String[] args) {

        BloomFilter<String> b = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_16), 100000, 0.000001);
        b.put("admin@sit-demo.com");
        System.out.println(b.mightContain("admin@sit-demo.com"));
    }
}
