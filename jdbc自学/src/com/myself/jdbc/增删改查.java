package com.myself.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import entity.Entity;

public class ��ɾ�Ĳ� {
	public static void main(String[] args) {
//		Entity���Ƕ�my_db���ݿ��stu����ֶν��з�װ
		Entity entity=new Entity();
		entity.setId(111);
		entity.setName("ww");
		entity.setGrade(100);
		//insert(entity);//��ӷ���
		//delete(111);
		selecet();//��ѯ
	}
	
	//����
	public static void insert(Entity entity){
		Connection conn=null;
		Statement statement=null;
		try {
			//ע������
			Class.forName("com.mysql.jdbc.Driver");
			//��ȡ����
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_db",
					"root", 
					"201602054");
			//����sql
			statement=conn.createStatement();
			String sql="insert into stu(id,name,grade) values("+entity.getId()+",'"+entity.getName()+"',"+entity.getGrade()+")";
			int rows = statement.executeUpdate(sql);
			System.out.println(rows);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (statement!=null) {
					statement.close();
				}
				if (conn!=null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	//ɾ��
	public static void delete(int id){
		Connection conn=null;
		Statement statement=null;
		try {
			//ע������
			Class.forName("com.mysql.jdbc.Driver");
			//��ȡ����
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_db",
					"root", 
					"201602054");
			//����sql
			statement=conn.createStatement();
			String sql="delete from stu where id="+id;
			int rows = statement.executeUpdate(sql);
			System.out.println(rows);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (statement!=null) {
					statement.close();
				}
				if (conn!=null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	//��ѯ
	public static void selecet(){
		ResultSet rs=null;
		Connection conn=null;
		Statement statement=null;
		List<Entity> stus=new ArrayList<>();//List���ϴ�Ų�ѯ��䷵�ص��ֶ���Ϣ
		try {
			//ע������
			Class.forName("com.mysql.jdbc.Driver");
			//��ȡ����
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_db",
					"root", 
					"201602054");
			//����sql
			statement=conn.createStatement();
			String sql="select * from stu";
			rs = statement.executeQuery(sql);
			 while (rs.next()) {
				 Entity entity=new Entity();//Entity�Ƿ�װ��,����entity����
			    	//��ȡÿ��ÿ���ֶε���Ϣ
					int int1 = rs.getInt("id");
					String string = rs.getString("name");
					int int2 = rs.getInt("grade");
					entity.setId(int1);
					entity.setName(string);
					entity.setGrade(int2);
					stus.add(entity);
				}
			 System.out.println(stus);
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (statement!=null) {
					statement.close();
				}
				if (conn!=null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
