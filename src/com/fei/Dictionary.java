package com.fei;


import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

public class Dictionary {

    public static Properties properties = new Properties();
    private static HashMap<String,String> dict = new HashMap<>();

    static{
        Yaml yaml = new Yaml();
        InputStream inputStream = Dictionary.class.getClassLoader()
                .getResourceAsStream("dictionary.YAML");
        dict = (HashMap<String, String>) yaml.load(inputStream);

        for (String str:dict.keySet()) {
            System.out.println(str +"    " +dict.get(str));
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

    public synchronized String addWord(String key,String value) throws IOException {

        if(!checkString(key) && !checkString(value)){
            if(dict.containsKey(key)){
                return "duplicate word!";
            }
            dict.put(key,value);
            return null;
        } else if(checkString(key) &&  checkString(value)){
            return "word & meaning can not be null!";
        } else if(checkString(key)){
            return "word can not be null!";
        } else{
            return "meaning can not be null!";
        }
    }

    public synchronized String removeWord(String key){
        if(dict.containsKey(key)) {  //表示找到了
            dict.remove(key);
            return null;
        }else{
            return "can not find"+key+"in dictionary!";
        }
    }

    public boolean checkString(String str){
        if(str==null || "".equals(str)){
            return true;
        }else{
            return false;
        }
    }
}
