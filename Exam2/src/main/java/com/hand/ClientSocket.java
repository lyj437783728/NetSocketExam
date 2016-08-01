package com.hand;

import java.io.*;  
import java.net.*;  
  
public class ClientSocket{  
    Socket socket;  
 
    BufferedOutputStream bos;  
    
    public static void main(String[] args)   { 
        new ClientSocket();  
    }  
    
    public ClientSocket()  {  
        try   {  
            socket = new Socket("127.0.0.1", 8099);  
               
            bos = new BufferedOutputStream(socket.getOutputStream());  
            FileInputStream fis=new FileInputStream("target.pdf");
			BufferedInputStream bis=new BufferedInputStream(fis);
			
			byte[] input =new byte[1024];
			int len = 0;
			while((len = bis.read(input)) != -1){
				bos.write(input);
			}
            
			bis.close();
			fis.close();
            bos.close();  

            socket.close();  
            System.out.println("send successed");
        } catch (IOException e) {  
        	
        }  
    }  
  
   
}  
