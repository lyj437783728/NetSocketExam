package com.hand;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

public class GetPDF {

	public static void main(String[] args) {
		new ReadByGet().start();
	}

}
class ReadByGet extends Thread{
	public void run(){
		try {
			URL url = new URL("http://files.saas.hand-china.com/java/target.pdf");
			URLConnection connection = url.openConnection();
			InputStream is = connection.getInputStream();
			//InputStreamReader isr = new InputStreamReader(is);
			//BufferedReader br = new BufferedReader(isr);
			BufferedInputStream bis = new BufferedInputStream(is,1000);
			
			FileOutputStream fos = new FileOutputStream("target.pdf");
			//OutputStreamWriter osw = new OutputStreamWriter(fos,"utf-8");
			//BufferedWriter bw = new BufferedWriter(osw);
			//PrintWriter pw = new PrintWriter(osw);
			BufferedOutputStream bos = new BufferedOutputStream(fos,1000);
			
			byte input[] = new byte[100];
			int len =0;
			while((len = bis.read(input)) != -1){
				bos.write(input,0,len);
			}
			bis.close();
			is.close();
			bos.close();
			fos.close();
			System.out.println("done");
//			String line;
			//StringBuilder builder = new StringBuilder();
//			while((line = br.readLine()) != null){
//				pw.println(line);
//			}
			//pw.println(builder.toString());
//			pw.flush();
//			br.close();
//			isr.close();
//			is.close();
//			
//			//bw.close();
//			osw.close();
//			fos.close();
		
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}