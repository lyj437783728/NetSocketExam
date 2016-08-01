package com.hand;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;


public class MyServerSocket extends Thread {

	
	public void run() {
		try {
			ServerSocket serverSocket = new ServerSocket(12345);
			while(true){
				//阻塞 
				Socket socket = serverSocket.accept();
				System.out.println("find a client");
							
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
