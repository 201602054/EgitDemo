package com.webserver.core;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * ��user.dat�ļ��е��û���Ϣ��ʾ������̨
 * @author Tedu
 *
 */
public class ShowAllUserDemo {

	public static void main(String[] args) throws IOException {
		//����RAF����
		RandomAccessFile raf=new RandomAccessFile("user.dat", "r");
		//�����ȡ�ֽ���  ���д��ʽ
		byte[] data=new byte[32]; 
		//ѭ����ȡ���м�¼��Ϣ   raf.length();��ȡ�ļ��ĳ���
		for(int i=0;i<raf.length()/100;i++){//ÿ���û���Ϣ100�ֽ�
			//���û���32�ֽ�
			raf.read(data);
			String username=new String(data, "utf-8").trim();//�洢����������
			//������32�ֽ�
			raf.read(data);
			String pwd=new String(data, "utf-8" ).trim();
			//���ǳ�32�ֽ�
			raf.read(data);
			String nickname=new String(data, "utf-8").trim();
			//������4�ֽ�
			int age=raf.readInt();//ע�ⲻ��д��read();  read()ֻ��ȡһ���ֽ� 
			System.out.println(username+","+pwd+","+nickname+","+age);
		}

		System.out.println(raf.getFilePointer());


		raf.close();
	}

}
