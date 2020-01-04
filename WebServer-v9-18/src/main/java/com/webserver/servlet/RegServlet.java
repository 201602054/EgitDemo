package com.webserver.servlet;

import java.io.RandomAccessFile;
import java.util.Arrays;

import com.webserver.http.HttpRequest;
import com.webserver.http.HttpResponse;

public class RegServlet  extends HttpServlet{
	public void service(HttpRequest request,HttpResponse response){
		System.out.println("RegServlet:��ʼ����ע��...");
		
		/*
		 * 1:ͨ��request ��ȡ�û��ύ����Ϣ
		 * 2:����Ϣд��user.dat�ļ�����
		 * 3:����response��Ӧע����ҳ��
		 */
		//parameters��Map������,���д洢��ע����Ϣ��ÿһ������,getParameter()�ǻ�ȡ����ֵ�ķ���
		String username=request.getParameter("username");//"username"��username��reg.html�е�����������
		String password=request.getParameter("password");
		String nickname=request.getParameter("nickname");
		int age=Integer.parseInt(request.getParameter("age"));
		System.out.println("username:"+username+"  "+",password:"+password+"  "+
		",nickname:"+nickname+"  "+",age:"+age);
		
		
		/*
		 * ÿ���ֽ�ռ��100�ֽ�
		 * �����û���,����,�ǳƾ�Ϊ32�ֽ�,����4�ֽ�
		 * */
		try (RandomAccessFile raf=new RandomAccessFile("user.dat", "rw");){
			
			//Ҫ�ȼ����û��Ƿ��Ѿ�����
			boolean b=false;
			byte[] data1=new byte[32];//���
			for (int i = 0; i < raf.length()/100; i++) {
				raf.seek(i*100);//�ƶ�RAFָ�뵽��Ӧÿһ����¼��ʼ��λ��
				raf.read(data1);//32�ֽ�  ���û���
				String user=new String(data1, "utf-8").trim();//��trim()��Ӱ��if�жϽ��
				if (user.equals(username)) {
					forward("/myweb/have_user.html", request, response);
					b=true;
					break;
				}
			}
			
		 if (!b) {//b==false  û�и��û����ܽ�ע����Ϣд���ļ�
			//��ָ���ƶ����ļ�ĩβ,������Ϣ����
			raf.seek(raf.length());
			//д���û���
			byte[] data=username.getBytes("UTF-8");
			data=Arrays.copyOf(data, 32);//����
			raf.write(data);
			//д������
			data=password.getBytes("UTF-8");
			data=Arrays.copyOf(data, 32);
			raf.write(data);
			//д���ǳ�
			data=nickname.getBytes("UTF-8");
			data=Arrays.copyOf(data, 32);
			raf.write(data);
			//д������
			raf.writeInt(age);
			
			//��Ӧע��ɹ���ҳ����ͻ���
			forward("/myweb/reg_success.html", request, response);
		  }
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("RegServlet:����ע�����");
	}
}
