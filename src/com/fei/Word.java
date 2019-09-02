package com.fei;

import org.yaml.snakeyaml.Yaml;

public class Word {

    private String instruction;

    private String key;

    private String value;

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

    public static Word yaml2Word(String yamlStr){
        Yaml yaml = new Yaml();
        Word word = yaml.load(yamlStr);
        return word;
    }

    public static String word2Yaml(Word word){
        Yaml yaml = new Yaml();
        String yamlStr = yaml.dump(word);
        return yamlStr;
    }


}
