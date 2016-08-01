package com.hand;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class App 
{
    public static void main( String[] args ){
    	new ReadByGet().start();
    }
    
}


class ReadByGet extends Thread{
	public void run(){
		try {
			URL url = new URL("http://hq.sinajs.cn/list=sz300170");
			URLConnection connection = url.openConnection();
			InputStream is = connection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			
			String line;
			String str;
			StringBuilder builder = new StringBuilder();
			while((line = br.readLine()) != null){
				//str = new String(line.getBytes("utf-8"),"utf-8");
				//System.out.println(str);
				builder.append(line);
			}
			br.close();
			isr.close();
			is.close();
//			String str = builder.toString();
//			str = new String(str.getBytes("GBK"),"UTF-8");
			//System.out.println(builder.toString());
			
			str = builder.toString();
			String a[] = str.split("\"");
			str = a[1];
			String arr[]=str.split(",");
			
			try {
				
				//DOM
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();			
				DocumentBuilder builder2 = factory.newDocumentBuilder();
				Document document = builder2.newDocument();
				Element root = document.createElement("stock");				
				
				Element name  = document.createElement("name");
//				arr[0] = new String(arr[0].getBytes("GBK"),"UTF-8");
//				System.out.println(arr[0]);
				name.setTextContent(arr[0]);
				Element open  = document.createElement("open");
				open.setTextContent(arr[1]);
				Element close  = document.createElement("close");
				close.setTextContent(arr[2]);
				Element current  = document.createElement("current");
				current.setTextContent(arr[3]);
				Element high  = document.createElement("high");
				high.setTextContent(arr[4]);
				Element low  = document.createElement("low");
				low.setTextContent(arr[5]);
				
				root.appendChild(name);	
				root.appendChild(open);	
				root.appendChild(close);	
				root.appendChild(current);	
				root.appendChild(high);	
				root.appendChild(low);	
				document.appendChild(root);
				
				
				//--------------------------
				
				TransformerFactory factory2 = TransformerFactory.newInstance();
				Transformer transformer = factory2.newTransformer();
				StringWriter writer = new StringWriter();
				transformer.transform(new DOMSource(document), new StreamResult(writer));
				System.out.println(writer.toString());
				
				transformer.transform(new DOMSource(document), new StreamResult(new File("hand.xml")));
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}