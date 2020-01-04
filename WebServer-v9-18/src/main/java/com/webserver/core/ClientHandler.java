package com.webserver.core;

import java.io.File;
import java.io.IOException;
import java.net.Socket;

import com.webserver.exception.EmptyRequestException;
import com.webserver.http.HttpRequest;
import com.webserver.http.HttpResponse;
import com.webserver.servlet.HttpServlet;
import com.webserver.servlet.RegServlet;

/**
 * ���̸߳�������ָ���ͻ��˵Ľ�������
 * ������̷�Ϊ����:
 * 1:׼������
 * 2:��������
 * 3:������Ӧ
 * @author ta
 *
 */
public class ClientHandler implements Runnable{
	private Socket socket;
	public ClientHandler(Socket socket) {
		this.socket = socket;
	}

	public void run() {//ʵ��Runnable�ӿ���Ҫ��дrun()����
		try {
			//1 ׼������
			HttpRequest request = new HttpRequest(socket);
			HttpResponse response = new HttpResponse(socket);
			/*
			 * 2 ��������
			 * 2.1:ͨ��request��ȡ�ͻ����������Դ
			 *     ��Ӧ�ĳ���·��uri��ֵ
			 * 2.2:��webappsĿ¼��ͨ����Ӧ�ĳ���·
			 *     ��Ѱ�Ҹ���Դ    
			 */
			String path = request.getRequestURI();
			System.out.println("path:"+path);

			
			//�����жϸ������Ƿ�Ϊ����ҵ��     
			HttpServlet servlet=ServerContext.getServlet(path);//���ݸ���������·����ȡ��Ӧ��Servlet
			if (servlet!=null) {//Map��������path,���Զ�������Ӧ��Servletʵ��������service����
				servlet.service(request,response);//����ҵ��
			}else{
				File file = new File("./webapps"+path);
				if(file.exists()) {
					System.out.println("����Դ���ҵ�!");
					
					response.setEntity(file);//����ϵͳ�ļ�(��Դ)�����Ӧͷ������

					System.out.println("��Ӧ�ͻ������!");
				}else {
					System.out.println("����Դ������!");

					File notFound = new File("./webapps/root/404.html");
					//����״̬����������
					response.setStatusCode(404);
					response.setStatusReason("NOT FOUND");

					//���������ļ�Ϊ404ҳ��
					response.setEntity(notFound);

				}
			}
			//3��Ӧ�ͻ���
			response.flush();
		}catch(EmptyRequestException e){
			//�������������,��������,���ü��������׳�,���������ҵ��
			System.out.println("�����󲻴���!!!!!!!");
		}
		catch (Exception e) {
			e.printStackTrace();
		} finally {
			/*
			 * ������Ӧ��Ϻ���ͻ��˶Ͽ�����
			 * ���������HTTP1.0�ķ�ʽ��
			 * 1.1���������Ӻ���ж������
			 * ��Ӧ��������Ҫ�������Ϣͷ��
			 * ��Ӧͷ�Ĵ���
			 */
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
	}
}





