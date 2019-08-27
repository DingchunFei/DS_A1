package com.fei.server;
import com.fei.Word;
import com.fei.Dictionary;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static com.fei.Word.jsonToWord;
import static com.fei.Word.wordToJson;

import javax.net.ServerSocketFactory;

public class Server {
	
	// Declare the port number
	private static int port = 3005;
	
	// Identifies the user number connected
	private static int counter = 0;

	private static Dictionary dic;

	static{
		dic = new Dictionary();
	}

	public static void main(String[] args)
	{
		ServerSocketFactory factory = ServerSocketFactory.getDefault();
		
		try(ServerSocket server = factory.createServerSocket(port))
		{
			System.out.println("Waiting for client connection-");
			
			// Wait for connections.
			while(true)
			{
				Socket client = server.accept();
				counter++;
				System.out.println("Client "+counter+": Applying for connection!");
							
				// Start a new thread for a connection
				Thread t = new Thread(() -> {
					try {
						serveClient(client);
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
				t.start();
			}
			
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}
	
	private static void serveClient(Socket client) throws Exception {
		try(Socket clientSocket = client) {
			// Input stream
			DataInputStream input = new DataInputStream(clientSocket.getInputStream());
			// Output Stream
			DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());

			while(true) {
				if (input.available() > 0) {
					String inputStr = input.readUTF();
					Word word = jsonToWord(inputStr);
					String tmpResult;

					if (word.getInstruction() == null) {
						word.setMsg("instruction can not be null!");
					} else if (word.getInstruction().equals("findWord")) {
						tmpResult = dic.findWord(word.getKey());
						if (tmpResult == null) {
							word.setMsg("can not find '"+word.getKey()+"' word in dictionary!");
						} else {
							word.setValue(tmpResult);
						}
					} else if (word.getInstruction().equals("addWord")) {
						tmpResult = dic.addWord(word.getKey(), word.getValue());
						if (tmpResult==null) {
							word.setValue("word '"+word.getKey()+"' has been added successfully");
							System.out.println("word '" + word.getKey() + "' has been added to dictionary");
						} else {
							word.setMsg(tmpResult);			//将返回的错误信息打印出来
						}
					} else if (word.getInstruction().equals("removeWord")) {
						tmpResult = dic.removeWord(word.getKey());
						if (tmpResult==null) {
							word.setValue("word '"+word.getKey()+"' has been removed successfully!");
						} else {
							word.setMsg(tmpResult);
						}
					} else {
						word.setMsg("instruction can not be found!");
					}
					output.writeUTF(wordToJson(word));
					output.flush();
				}

			}
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
