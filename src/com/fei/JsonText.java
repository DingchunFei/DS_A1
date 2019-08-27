package com.fei;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonText {

    private String key;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String value;


    public JsonText() {
    }

    public JsonText(String key) {
        this.key = key;
    }

    public JsonText(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }



    public static void main(String[] args) throws IOException {

        //1.导入jar包
        //创建ObjectMapper mapper 对象
        ObjectMapper mapper = new ObjectMapper();

        //3.调用mapper的 writeValueAsString() 方法把一个对象转为一个JSON字符串
        JsonText json = new JsonText("10001");
        String jsonStr = mapper.writeValueAsString(json);
        System.out.println(jsonStr);

        //将JAVA对象转化为Json格式的文件
        //mapper.writeValue(new File("src/com/cn/test/JsonText.json"), json);


    }
}