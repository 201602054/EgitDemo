package dao;
/**
 * dao��ȫ���ŵ��Ƕ����ݿ�����Ĺ淶
 * ��userinfo����ɾ�Ĳ�Ĺ淶
 * @author Tedu
 *
 */
import java.util.List;
public interface UserInfoDao {
	//��ѯ
	List<UserInfoDao> findAll();
	//����
	int insert(UserInfoDao userInfoDao);
	//�޸�
	
	//ɾ��
	
}
