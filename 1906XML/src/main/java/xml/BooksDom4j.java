package xml;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class BooksDom4j {
	public static void main(String[] args)
			throws Exception{
		/*
		 * ʹ�� Dom4j API ��ȡXML�ļ� 
		 */
		SAXReader reader=new SAXReader();
		File file = new File("books.xml");
		//����SAXReader��ȡbooks.xml
		//�����ȡ�ɹ��ͻᴴ��һ��dom����
		//��ṹ�����εġ������ȡʧ����
		//�׳��쳣: xml��ʽ���ļ��Ҳ���
		Document doc=reader.read(file);
		//����ȡ�Ľ��
//		System.out.println(doc.asXML()); 

		//�ҵ���Ԫ��, ��Ϊ�������
		Element root = doc.getRootElement();
		//root ���� books Ԫ��

//		System.out.println(root.asXML()); 
		//Ŀ�꣺��ʾȫ��������

		//1. ��ȡȫ����Ԫ��API
		List<Element> list=root.elements();
//		for(Element e:list) {
//			System.out.println(e.asXML()); 
//		}
		
		//2. ��ȡȫ��ָ�����Ƶ���Ԫ��
		list = root.elements("book");
		for(Element e:list) {
			//e��bookԪ��
			//book ����3����Ԫ�� name author date
//			System.out.println(e.asXML()); 
			//3. �ҵ�Ԫ���еĵ�һ��������������Ԫ��
			Element n = e.element("name");
//			System.out.println(n.asXML());
			//4. ��ȡԪ���е��ı�
			String name = n.getTextTrim();
//			System.out.println(name); 

			//e �� book Ԫ�أ���������
			//5. ��ȡԪ�ص�����ֵ
			String id=e.attributeValue("id");
			System.out.println(id); 
		}

	}
}
