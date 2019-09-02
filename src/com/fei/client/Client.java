package com.fei.client;
import com.fei.Word;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {

    // IP and port
    private static String ip = "localhost";
    private static int port = 3005;

    public String find(String key) throws Exception {

        Word word = new Word(key);
        word.setInstruction("findWord");

        return communication(word);
    }

    public String add(String key,String val) throws Exception {

        Word word = new Word(key,val);
        word.setInstruction("addWord");

        return communication(word);
    }

    public String remove(String key) throws Exception {
        Word word = new Word(key);
        word.setInstruction("removeWord");

        return communication(word);
    }

    public String communication(Word word) throws IOException {
        Word result;
        String outputStr = Word.word2Yaml(word);

        try (Socket socket = new Socket(ip, port);) {
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());

            output.writeUTF(outputStr);
            output.flush();

            while (true) {
                if (input.available() > 0) {
                    result = Word.yaml2Word(input.readUTF());
                    System.out.println(result);
                    break;
                }
            }
        }
        return result.getMsg()==null ? result.getValue(): result.getMsg();
    }
}
