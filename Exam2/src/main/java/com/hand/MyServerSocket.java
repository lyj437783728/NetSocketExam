package com.hand;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;  
import java.net.Socket;  
  
public class MyServerSocket {  
	
    private ServerSocket ss;  
    private Socket socket;  
    private BufferedInputStream in;  
 
    
    public static void main(String[] args)   {  
        new MyServerSocket();  
    }  
    
    public MyServerSocket(){  
            try {
				ss = new ServerSocket(8099);  

				    socket = ss.accept();  
				    in = new BufferedInputStream(socket.getInputStream());  
 
				    FileOutputStream fos=new FileOutputStream("new_target.pdf");
					BufferedOutputStream bos=new BufferedOutputStream(fos);
				    
				    byte[] input =new byte[1024];
				    int len = 0;
					while((len = in.read(input))!=-1){
						bos.write(input,0,len);
					}
					
					bos.close();
					fos.close();

				    in.close();  
				    socket.close();  
				    ss.close();  

			} catch (IOException e) {
				e.printStackTrace();
			}
    }
            
    
}   
