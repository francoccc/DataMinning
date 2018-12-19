package com;

public class Test {

    public static void main(String[] args) {
        ReadXMLConfig config = ReadXMLConfig.getInstance();
        System.out.println(config.getConfigValue("dbpool.url"));
    }
}
