package com.fei.test;

import java.util.Iterator;
import java.util.Vector;

public class test {


    public static void main(String[] args) {
        InterfaceTest itf = ()-> {return "你好";};

        String str = itf.ayaya();
        System.out.println(str);
    }
}


