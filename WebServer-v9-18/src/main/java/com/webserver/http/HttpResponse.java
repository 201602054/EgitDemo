package com.webserver.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * ��Ӧ����
 * �����ÿһ��ʵ�����ڱ�ʾ���͸��ͻ��˵�һ��
 * �������Ӧ���ݡ�
 * ÿ����Ӧ��������������:
 * ״̬�У���Ӧͷ����Ӧ����
 * @author ta
 *
 */
public class HttpResponse {
	//״̬�������Ϣ
	private int statusCode = 200;
	private String statusReason = "OK";

	//��Ӧͷ�����Ϣ
	private Map<String,String> headers = new HashMap<>();


	//��Ӧ���������Ϣ
	//��Ӧ���Ķ�Ӧ��ʵ���ļ�
	private File entity;

	//��������ص���Ϣ
	private Socket socket;
	private OutputStream out;

	public HttpResponse(Socket socket) {//��ClientHandlerʵ��HttpResponseʱ���ݹ�����socket
		try {
			this.socket = socket;
			this.out = socket.getOutputStream();//��ʼ��out
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ����ǰ��Ӧ����������һ����׼����Ӧ
	 * ��ʽ���͸��ͻ���
	 */
	public void flush() {
		/*
		 * 1����״̬��
		 * 2������Ӧͷ
		 * 3������Ӧ����
		 */
		sendStatusLine();
		sendHeaders();
		sendContent();
	}
	/**
	 * ����״̬��
	 */
	private void sendStatusLine() {
		System.out.println("HttpResponse:��ʼ����״̬��...");
		try {
			//����״̬��
			String line = "HTTP/1.1"+" "+statusCode+" "+statusReason;
			System.out.println("״̬��:"+line);
			out.write(line.getBytes("ISO8859-1"));//״̬��תΪ�ֽ�д��
			out.write(13);//written CR
			out.write(10);//written LF
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("HttpResponse:״̬�з������!");
	}
	/**
	 * ������Ӧͷ
	 */
	private void sendHeaders() {
		System.out.println("HttpResponse:��ʼ������Ӧͷ...");
		try {
			/*
			 * ����headers(Map)����������Ӧͷ����
			 * ��������: entrySet()����  ��ѭ������
			 * 	
			 */
			Set<Entry<String,String>> entrySet= headers.entrySet();
			for(Entry<String,String> header : entrySet) {
				String name = header.getKey();//map��ÿһ��Ԫ�ض���getKey(),getValue()����
				String value = header.getValue();
				String line = name+": "+value;//ƴ��Ϊ�ַ�������,ע��""����   ð��+�ո�
				System.out.println("��Ӧͷ:"+line);
				out.write(line.getBytes("ISO8859-1"));
				out.write(13);
				out.write(10);
			}


			//��������CRLF��ʾ��Ӧͷ�������
			out.write(13);//written CR
			out.write(10);//written LF
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("HttpResponse:��Ӧͷ�������!");
	}
	/**
	 * ������Ӧ����
	 */
	private void sendContent() {
		if (entity!=null) {//entity�Ǵ�������file�ļ�
			try(FileInputStream fis= new FileInputStream(entity);){
				System.out.println("HttpResponse:��ʼ������Ӧ����...");
				//������Ӧ����
				int len = -1;
				byte[] data = new byte[1024*10];
				while((len = fis.read(data))!=-1) {//���
					out.write(data,0,len);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("HttpResponse:��Ӧ���ķ������!");
	}


	public File getEntity() {
		return entity;
	}
	/**
	 * ������Ӧ����ʵ���ļ�
	 * ���õ�ͬʱ���Զ����������Ӧͷ:
	 * Content-Type��Content-Length
	 * @param entity
	 */
	public void setEntity(File entity) {//entity�Ǵ�ClientHandler��������file
		this.entity = entity;
		String fileName = entity.getName();//��ȡ�ļ���
		System.out.println("��Դ��:"+fileName);
		//��ȡ����Դ��׺��
		String ext = fileName.substring(fileName.lastIndexOf(".")+1).toLowerCase();
		System.out.println("��Դ��׺��:"+ext);

		String type = HttpContext.getMimeType(ext);//����Content-Type��ֵ

		putHeader("Content-Type", type);//putHeader����µ���Ӧͷ
		putHeader("Content-Length", entity.length()+"");


	}
	/**
	 * ��ȡ��Ӧ���Ķ�Ӧ��ʵ���ļ��ķ���
	 * @return
	 */
	public int getStatusCode() {
		return statusCode;
	}

	/**
	 * ����״̬��ķ���
	 * @param statusCode
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * ���úͻ�ȡ״̬�����ķ���
	 * @return
	 */
	public String getStatusReason() {
		return statusReason;
	}
	public void setStatusReason(String statusReason) {
		this.statusReason = statusReason;
	}


	/**
	 * ��ǰ��Ӧ����������µ���Ӧͷ
	 * @param name ��Ӧͷ������
	 * @param value ��Ӧͷ��ֵ
	 */
	public void putHeader(String name,String value) {
		this.headers.put(name, value);
	}

}








