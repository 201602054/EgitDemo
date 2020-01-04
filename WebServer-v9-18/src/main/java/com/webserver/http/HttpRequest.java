package com.webserver.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import com.webserver.exception.EmptyRequestException;


/**
 * �������
 * �����ÿһ��ʵ�����ڱ�ʾ�ͻ��˷��͹�����
 * һ���������������
 * һ�����������������:�����У���Ϣͷ����Ϣ����
 * @author ta
 *
 */
public class HttpRequest {
	//1)�����������Ϣ
	//����ķ�ʽ
	private String method;
	//����·��
	private String uri;
	//Э��汾
	private String protocol;
	
	//uri�����󲿷�(?)�������
	private String requestURI;
	//uri�в�������(?)�Ҳ�����
	private String queryString;
	//������������е�ÿһ������
	private Map<String, String> parameters=new HashMap<>();
	
	//2)��Ϣͷ�����Ϣ
	/*
	 * key:��Ϣͷ������
	 * value:��Ϣͷ��Ӧ��ֵ
	 */
	private Map<String,String> headers = new HashMap<>();
	
	//3)��Ϣ���������Ϣ
	
	
	//��������ص�����
	private Socket socket;
	private InputStream in;
	/**
	 * ���췽�������ڳ�ʼ���������
	 * @throws EmptyRequestException 
	 */
	public HttpRequest(Socket socket) throws EmptyRequestException {
		try {
			this.socket = socket;
			this.in = socket.getInputStream();
			/*
			 * ���������Ϊ����:
			 * 1:����������
			 * 2:������Ϣͷ
			 * 3:������Ϣ����
			 */
			System.out.println("HttpRequest:��ʼ��������...");
			parseRequestLine();
			parseHeaders();
			parseContent();
			System.out.println("HttpRequest:�����������!");
			
		} catch(EmptyRequestException e){//������쳣֮ǰ�����Զ�����쳣,�����������׳�.��������쳣ֱ�Ӳ����쳣
			throw e;
		}
		catch (Exception e) {//����쳣
			e.printStackTrace();
		}
	}
	/**
	 * ����������
	 * @throws EmptyRequestException 
	 */
	private void parseRequestLine() throws EmptyRequestException {
		System.out.println("HttpRequest:��ʼ����������");
		try {
			/*
			 * ͨ����������ȡ��һ���ַ�����
			 * һ�������еĵ�һ���ַ�����������
			 * �е����ݡ�
			 * ��ȡ���Ժ󣬰���" "(�ո�)���Ϊ������
			 * Ȼ�������������ݷֱ����õ������ж�Ӧ
			 * ������method,uri,protocol��
			 * 
			 * http://localhost:8088/index.html
			 */
			//1.����������   ��ȡ�����к���Ϣͷ���õ�readLine�����÷���ʵ��(���븴��)  
			String line = readLine();
			
			//�жϸ������Ƿ�Ϊ������
			if ("".equals(line)) {//��Ϊ������,readLine()�����᷵��һ���մ�
				throw new EmptyRequestException();
			}
			
			//2.��ֲ���ֵ
			String[] data = line.split("\\s");//�������а��հ��ַ��ָ�
			method = data[0];//��ֵ������ʽ
			uri = data[1];//��ֵ������·��
			protocol = data[2];//��ֵ��Э��
			
			//3.��һ������uri  Ҫ�жϿͻ������������Դ����ҵ��
			parseURI();
			
			System.out.println("method:"+method);// GET
			System.out.println("uri:"+uri);// /index.html
			System.out.println("protocol:"+protocol);// HTTP/1.1
			
		}catch(EmptyRequestException e){//������쳣֮ǰ�����Զ�����쳣,�����������׳�.
			throw e;
		} catch (Exception e) {//�����쳣
			e.printStackTrace();
		}
		
		System.out.println("HttpRequest:�������������");
	}
	
	/**
	 * ��һ������uri
	 */
	private void parseURI(){
		System.out.println("HttpRequest:��һ������uri...");
		/*
		 * �����ڽ���֮ǰ,Ҫ���жϵ�ǰuri�Ƿ��в���,������uri��
		 * �Ƿ���"?",����˵���в���,����û��.
		 * ��û�в�,ֱ�ӽ�uri��ֵ��requestURI����
		 * 
		 * ���в�,Ӧ���Ȱ�"?"���,Ȼ��"?"��ำֵ��requestURI,�Ҳ�
		 * ��ֵ��queryString
		 * 
		 * Ȼ�󽫲������ְ���"&"���Ϊÿһ�����,ÿ���������"="���Ϊ����ֵ
		 * ����������Ϊkey,ֵ��Ϊvalue���浽parameters���Map��
		 * */
		if (uri.indexOf("?")!=-1) {//trueʱ˵������?,��˵���������ҵ��
			String[]data=uri.split("\\?");//?�ǵ�Ҫת��
			requestURI=data[0];
			if (data.length>1) {//��ֺ�data���ȱ������1�ſ��Ը�queryString��ֵ,�����±�Խ��
				queryString=data[1];
				
				try {
					//��queryString�а�����%XXX����ת��   ���ע����Ϣ����������������
					queryString=URLDecoder.decode(queryString, "utf-8");//��queryString����%�İ���utf-8ת��Ϊԭ������Ϣ
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				
				parseParameters(queryString);
			}
		}else{//������ַ��û�в���,��:�ͻ������������Դ������ҵ��,uri���Ƕ�Ӧ����Դ����·��
			requestURI=uri;
		}
		System.out.println("requestURI:"+requestURI);
		System.out.println("queryString:"+queryString);
		System.out.println("parameters:"+parameters);
		System.out.println("HttpRequest:����uri���");
	}
	
	/**
	 * ��������,������һ���ַ��� ��ʽ:name=value&nickname=value&...
	 * ���������Ĳ��������parameters���Map��
	 * @param line
	 */
	private void parseParameters(String line){
		String[] data=line.split("&");//���ע����Ϣ��ÿһ����Ϣ
		for (String string : data) {
			String[] para=string.split("=");//���ÿһ����Ϣ��Ӧ�����Ժ�ֵ
			if (para.length>1) {
				parameters.put(para[0], para[1]);//�����ҳע����Ϣ����ÿһ����Ϣ��Ӧ�����Ժ�ֵ
			}else{
				parameters.put(para[0], null);//�ͻ����������nullֵҲҪ���б���
				//���������ƺͲ���ֵ�ֱ𱣴浽parameters(�Ǹ�Map)��
			}
		}
	}
	
	/**
	 * ������Ϣͷ
	 */
	private void parseHeaders() {
		System.out.println("HttpRequest:��ʼ������Ϣͷ");
		try {
			/*
			 * ѭ������readLine������ȡÿһ��
			 * �ַ�����ÿһ�о���һ����Ϣͷ��
			 * ���readLine�������ص��ַ�����
			 * һ�����ַ���ʱ��Ӧ��ֹͣѭ����ȡ
			 * ������(��Ϊ������ȡ����CRLF)
			 * 
			 * ��ȡ��ÿһ����Ϣͷ�����ǿ��԰���
			 * ": "(��:ð�ſո�)�����в�֣���
			 * ��Ϣͷ��������Ϊkey����Ϣͷ��ֵ��Ϊ
			 * value���浽headers���Map�������Ϣ
			 * ͷ�Ľ�������
			 * 
			 */
			while(true) {
				String line = readLine();//
				if("".equals(line)) {//������ȡ��Ϣͷ,ֱ������
					break;
				}//��Ϣͷ��ʽ:  ����: ֵ       (��ð�ſո����)
				String[] data = line.split(":\\s");//����ȡ������Ϣͷ���հ��ַ�(�����ǿո�)���
				headers.put(data[0], data[1]);//����洢��Ϣͷ��Map��
			}
			
			
			System.out.println("headers:"+headers);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("HttpRequest:������Ϣͷ���");
	}
	
	/**
	 * ������Ϣ����
	 */
	private void parseContent() {
		System.out.println("HttpRequest:��ʼ������Ϣ����");
		/*
		 * ������Ϣͷ�е�Content-Length���жϵ�ǰ�����Ƿ�����Ϣ����
		 * */
		if (headers.containsKey("Content-Length")) {
			int length=Integer.parseInt(headers.get("Content-Length"));
			
			try {
				//��ȡ�����е������ֽ�
				byte[] data=new byte[length];//һ���Զ�ȡ
				in.read(data);
				
				//����Content-Typeָ������������������
				String type=headers.get("Content-Type");
				
				//�ж��Ƿ�Ϊҳ��form���ύ���û��������Ϣ
				if ("application/x-www-form-urlencoded".equals(type)) {
					//�������ݾ���һ���ַ���,ԭGET������url��?�Ҳ������
					String line=new String(data, "ISO8859-1");//���������ֽ�ת��Ϊ�ַ���
					
					line=URLDecoder.decode(line,"utf-8");//���line֮ǰҪת��
					
					parseParameters(line);//�����������ݴ浽����map��
					
				}//������������ӷ�֧�ж���������
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("HttpRequest:������Ϣ�������");
	}
	
	/**
	 * ���ж�ȡ���ݵķ���--������,��Ϣͷ
	 * @return
	 * @throws IOException
	 */
	private String readLine() throws IOException {
		int d = -1;
		char c1='a',c2='a';
		StringBuilder builder = new StringBuilder();//���ڷ���ƴ���ַ���
		while((d = in.read())!=-1) {//���ַ���ȡ
			c2 = (char)d;
			if(c1==13&&c2==10) {//�������CR(13)LF(10)˵�������ж���
				break;
			}
			builder.append(c2);//�������ַ�ƴ��
			c1 = c2;//��¼�ϴ�ƴ�ӵ��ַ�,����if�ж�
		}
		return builder.toString().trim();
	}
	
	
	/**
	 * �����е�����ʽ,����·����Э��汾ֻ�ܻ�ȡ(get),�����޸�(set)   �����װ
	 * @return
	 */
	public String getMethod() {
		return method;
	}
	public String getUri() {
		return uri;
	}
	public String getProtocol() {
		return protocol;
	}
	
	
	
	/**
	 * �õ���Ϣͷ�ķ���
	 * @param name
	 * @return
	 */
	public String getHeader(String name) {
		return headers.get(name);
	}
	
	
	/**
	 * �õ���ַ����?�������
	 * @return
	 */
	public String getRequestURI() {
		return requestURI;
	}
	/**
	 * �õ���ַ����?�Ҳ�����   ��������
	 * @return
	 */
	public String getQueryString() {
		return queryString; 
	}
	/**
	 * ���ݲ�������ȡ����ֵ
	 * @param name
	 * @return
	 */
	public String getParameter(String name){
		return parameters.get(name);//parameters��Map������
	}
}







