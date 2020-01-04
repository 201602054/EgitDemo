package com.webserver.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.webserver.servlet.HttpServlet;

/**
 * ���ﱣ�����з������ص�������Ϣ
 * @author Tedu
 *
 */
public class ServerContext {
	private static Map<String,HttpServlet> servletMapping=new HashMap<>();
	
	static{
		try {
			initServletMapping();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * ��ʼ������·�����ӦServlet�Ĺ�ϵ
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	private static void initServletMapping() throws Exception{
//		servletMapping.put("/myweb/reg",new RegServlet() );
//		servletMapping.put("/myweb/login",new LoginServlet() );
		
		
		/*
		 * ����conf/Servlets.xml�ļ�
		 * ����ǩ�����е�<servlet>��ǩ��ȡ�����ҽ�ÿ��<servlet>�е�����:
		 * path��Ϊkey
		 * className��ֵ���÷�����ض�Ӧ���ಢʵ����,��ʵ�����Ķ�������ΪHttpServlet
		 * ����Ϊvalue���뵽servletMapping��ɳ�ʼ��.
		 * */
		try {
			SAXReader reader=new SAXReader();
			Document doc=reader.read("./conf/Servlets.xml");
			Element root=doc.getRootElement();
			List<Element> elements = root.elements("servlet");
			for (Element element : elements) {
				String key=element.attributeValue("path");
				String value=element.attributeValue("className");
				
				//����
				Class cls=Class.forName(value);
				Object o=cls.newInstance();
//�����ǿתΪHttpServlet����,��λservletMapping��<String, HttpServlet>���͵�Map
				HttpServlet HttpServlet=(HttpServlet)o;
				servletMapping.put(key, HttpServlet);
				
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	/**
	 *���ݸ���������·����ȡ��Ӧ��Servlet
	 * @param path
	 * @return
	 */
	public static HttpServlet getServlet(String path){
		return servletMapping.get(path);
	}
}
