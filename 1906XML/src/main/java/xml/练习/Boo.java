package xml.��ϰ;


import java.util.List;

import javax.swing.text.html.HTML.Attribute;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.w3c.dom.Attr;

public class Boo {
	public static void main(String[] args) throws DocumentException {
		SAXReader reader=new SAXReader();
		Document doc=reader.read("student.xml");
		//��ȡ��Ŀ¼
		Element root=doc.getRootElement();
//		System.out.println(root.asXML());
		
		//��ȡȫ����Ŀ¼
		/*List<Element> list=root.elements();
		for (Element e : list) {
			System.out.println(e.asXML());
		}*/
		
		//��ȡָ����ȫ����Ŀ¼
		List<Element> list=root.elements("student");
		/*		for (Element e : list) {
			System.out.println(e.asXML());
		}*/
		
		//��ȡԪ����һ��������������Ԫ��
/*		for (Element e : list) {
			Element ee=e.element("name");
			System.out.println(ee.asXML());
		}*/
		
		//��ȡԪ�ص��ı�
/*		for (Element e : list) {
			Element ee=e.element("id");
			String string=ee.getTextTrim();
			System.out.println(string);
			
			System.out.println(e.elementTextTrim("id"));//�ȼ���39-41��
		}*/
		
		//��ȡԪ�ص�����
//		for (Element e : list) {
//			Element ee=e.element("gender");
//			System.out.println(ee.getName());
//		}
		
		//��ȡԪ�ص�����
//		for (Element e : list) {
//			Element ee=e.element("name");
//			org.dom4j.Attribute att=ee.attribute("id");
//			System.out.println(att.asXML());
//		}
		
		//��ȡ���Ե�����,ֵ
//		for (Element e : list) {
//			Element ee=e.element("name");
//			org.dom4j.Attribute att=ee.attribute("id");
//			System.out.println(att.getName());
//			System.out.println(att.getValue());
//		}
	}
}

