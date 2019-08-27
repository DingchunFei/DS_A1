package com.fei;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Word {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String instruction;

    private String key;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String value;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String msg=null;


    public Word() {
    }

    public Word(String key) {
        this.key = key;
    }

    public Word(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public Word(String key, String value, String instruction) {
        this.key = key;
        this.value = value;
        this.instruction = instruction;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    @Override
    public String toString() {
        return "Word{" +
                "instruction='" + instruction + '\'' +
                ", key='" + key + '\'' +
                ", value='" + value + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }

    public static String wordToJson(Word word) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        //调用mapper的 writeValueAsString() 方法把一个对象转为一个JSON字符串
        String jsonStr = mapper.writeValueAsString(word);
        return jsonStr;
    }

    public static Word jsonToWord(String jsonStr) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        //当反序列化json时，未知属性会引起的反序列化被打断，这里我们禁用未知属性打断反序列化功能，
        //因为，例如json里有4个属性，而我们的bean中只定义了2个属性，其它8个属性将被忽略
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        Word word = mapper.readValue(jsonStr, Word.class);
        return word;
    }
}
