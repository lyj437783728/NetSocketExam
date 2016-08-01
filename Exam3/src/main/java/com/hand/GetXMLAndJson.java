package com.hand;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


public class GetXMLAndJson {

	public static void main(String[] args) {

		new Get().start();
	}

}
class Get extends Thread{

	HttpClient ht = HttpClients.createDefault();

	public void run(){
		HttpGet hg = new HttpGet("http://hq.sinajs.cn/list=sz300170");
		try {

			HttpResponse response = ht.execute(hg);
			HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity, "UTF-8");
			//System.out.println(result);
			
			//对数据处理，得到一些需要的字段
			String str;
			String a[] = result.split("\""); 
			str = a[1];
			String arr[]=str.split(",");
//			for (int i = 0; i < arr.length; i++) {
//				System.out.println(arr[i]);
//			}
			
			
			//写xml文件
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();			
			DocumentBuilder builder2 = factory.newDocumentBuilder();
			Document document = builder2.newDocument();
			Element root = document.createElement("stock");				
			
			Element name  = document.createElement("name");

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
			

			
			TransformerFactory factory2 = TransformerFactory.newInstance();
			Transformer transformer = factory2.newTransformer();
			StringWriter writer = new StringWriter();
			transformer.transform(new DOMSource(document), new StreamResult(writer));
			//System.out.println(writer.toString());
			
			transformer.transform(new DOMSource(document), new StreamResult(new File("hand.xml")));
			
			
			
			//写json文件
			JsonObject object = new JsonObject();
			object.addProperty("name", arr[0]);
			object.addProperty("open", arr[1]);
			object.addProperty("close", arr[2]);
			object.addProperty("current", arr[3]);
			object.addProperty("high", arr[4]);
			object.addProperty("low", arr[5]);
			
			String strjson = object.toString();
			//System.out.println(strjson);

			FileOutputStream fos = new FileOutputStream("hand.json");
			OutputStreamWriter osw = new OutputStreamWriter(fos,"utf-8");
			BufferedWriter bw = new BufferedWriter(osw);
			bw.write(strjson);
			bw.flush();
			
			bw.close();
			osw.close();
			fos.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}