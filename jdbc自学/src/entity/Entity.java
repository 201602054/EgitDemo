package entity;
/**
 * ��my_db���ݿ��stu����ֶν��з�װ
 * @author Tedu
 *
 */
public class Entity {
	private int id;
	private String name;
	private int grade;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	
	@Override
	public String toString() {
		return " [id=" + id + ", name=" + name + ", grade=" + grade + "]";
	}
	
	
	
}	
