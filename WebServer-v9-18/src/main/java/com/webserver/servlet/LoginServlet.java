package com.webserver.servlet;

import java.io.RandomAccessFile;

import com.webserver.http.HttpRequest;
import com.webserver.http.HttpResponse;

public class LoginServlet extends HttpServlet {
	public void service(HttpRequest request, HttpResponse response){
		System.out.println("��ʼ�����¼...");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		System.out.println("�û���:"+username+",����:"+password);
		
		try(RandomAccessFile raf=new RandomAccessFile("user.dat", "rw")) {
			byte[] data1=new byte[32];//���
			for (int i = 0; i < raf.length()/100; i++) {
				raf.seek(i*100);//�ƶ�RAFָ�뵽��Ӧÿһ����¼��ʼ��λ��
				raf.read(data1);//32�ֽ�  ���û���
				String user=new String(data1, "utf-8").trim();//ת��Ϊ�ַ���
				if (user.equals(username)) {
					raf.read(data1);//������
					String pass=new String(data1, "utf-8").trim();//ת��Ϊ�ַ���
					if (pass.equals(password)) {
						forward("/myweb/login_success.html", request, response);
						return;
					}
					break;//�û�����ȷ,���벻��ȷҲҪ����ѭ��
				}
			}
			forward("/myweb/login_fail.html", request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		System.out.println("��¼����������!");
	}
}
