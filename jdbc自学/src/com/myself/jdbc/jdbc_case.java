package com.myself.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;//mysql��jdbc��Driver�ӿ�ʵ����
//java.sql.Driver��jdbc�ӿ�

/**
 * ����lib�ļ���,����������,ѡ�иð�,�һ�--build bath--add to build
 * @author Tedu
 * 
 */
public class jdbc_case {
	public static void main(String[] args) throws ClassNotFoundException {
		Connection connection=null;
		Statement stmt=null;
		 ResultSet rs =null;
		try {
			//1)ע������,����������������
			//DriverManager.registerDriver(new Driver());//ע����������һ
			Class.forName("com.mysql.jdbc.Driver");//ע������������  ����ʹ�õڶ���
			
			
			//2)��������    									my_db�����ݿ��� 	useSSL=true�Ǵ���Ĳ���
			connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/my_db?useSSL=true",
					"root", 
					"201602054");
			System.out.println(connection);
			
			
			
			//*************************************************************
			DatabaseMetaData md = connection.getMetaData();//��ȡ���ӵ�Ԫ������Ϣ,��������Ϣ
			System.out.println(md.getDatabaseProductName());//��ȡ���ݿ�����
			System.out.println(md.getDriverName());//��ȡ��������
			System.out.println(md.getURL());
			System.out.println(md.getUserName());
			//*************************************************************
			
			
			
			//3)����sql�������ݿ�
			String sql="select * from stu";//sql���
				//statement����:ʵ��sql����
		    stmt = connection.createStatement();
				//executeQuery(sql)ִ�в�ѯ���,executeUpdate(sql)ִ����ɾ��,executeBatch()����һ������,����������
		    rs = stmt.executeQuery(sql);//����SQL��䲢���շ��ؽ����
		    
		    
		    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
		    ResultSetMetaData md1 = rs.getMetaData();//��ȡ�������Ԫ����
		    int columnCount = md1.getColumnCount();//��ȡ��
		   // String name=md1.getColumnName(column);
		    //������ȡÿһ�ֶε�����
		    //�����Ժͱ��ֶ�һ��
		    //���ڷ�װ����
		    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
		    
		    
			//4)������Ӧ���ResultSet(�ײ�ά����һ��ָ���������α�)			
		    while (rs.next()) {//��ǰ�ƶ��α�    ÿ�ζ�һ��,ÿ��һ���ֽ�һ���ֽڶ�ȡ
		    	//��ȡÿ��ÿ���ֶε���Ϣ
				int int1 = rs.getInt("id");
				String string = rs.getString("name");
				int int2 = rs.getInt("grade");
				System.out.printf("id:%s,name:%s,grade:%s\n",int1,string,int2);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//5)�ͷ���Դ
			try {
				if (rs!=null) {
					rs.close();
				}
				if (stmt!=null) {
					stmt.close();
				}
				if (connection!=null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}
