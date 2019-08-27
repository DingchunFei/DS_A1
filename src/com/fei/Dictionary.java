package com.fei;


import java.io.*;
import java.util.HashMap;
import java.util.Properties;

public class Dictionary {

    public static Properties properties = new Properties();
    private static HashMap<String,String> dict = new HashMap<>();

    static{
        try {
            InputStream in = Dictionary.class.getClassLoader().getResourceAsStream("dictionary.properties");

            properties.load(in);

            for (String key : properties.stringPropertyNames()) {
                System.out.println(key + "=" + properties.getProperty(key));
                dict.put(key,properties.getProperty(key));
            }

        } catch (FileNotFoundException e) {
            System.out.println(".properties not found!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String findWord(String key){
        String value = dict.get(key);
        if(value ==null){
            return null;
        }else{
            return value;
        }
    }

    public String addWord(String key,String value) throws IOException {

        if(key!=null && value!=null){
            if(dict.containsKey(key)){
                return "duplicate word!";
            }
            dict.put(key,value);
            return null;
        } else if(key == null && value ==null){
            return "word & meaning can not be null!";
        } else if(key==null){
            return "word can not be null!";
        } else{
            return "meaning can not be null!";
        }
    }

    public String removeWord(String key){
        if(dict.containsKey(key)) {  //表示找到了
            dict.remove(key);
            return null;
        }else{
            return "can not find"+key+"in dictionary!";
        }
    }
}
