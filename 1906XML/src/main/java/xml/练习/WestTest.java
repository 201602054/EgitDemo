package xml.��ϰ;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class WestTest {
	public static void main(String[] args) {
		try {
			SAXReader reader=new SAXReader();
			Document doc=reader.read("west.xml");
			Element root=doc.getRootElement();
			
			/*//��ȡ��Ϊperson��ȫ��Ԫ��
			List<Element> list=root.elements("person");
			for (Element element : list) {
				System.out.println(element.asXML());
			}*/
			
			//��ȡpersonԪ����Ԫ��nickname �����nickname������
			List<Element> list=root.elements("person");
			/*for (Element element : list) {
				Element e2=element.element("nickname");
				System.out.println(e2.asXML());
				System.out.println(e2.getTextTrim());
				System.out.println(element.elementTextTrim("nickname"));
			}*/
			
			//��ȡpersonԪ�ص���Ԫ��name������gender��ֵ
			for (Element element : list) {
				Element element2=element.element("name");
				/*String s=element2.attributeValue("gender");
				System.out.println(s);*/
				
				org.dom4j.Attribute att= element2.attribute(0);//��ȡindex(0)λ�õ�����(������="value")
				System.out.println(att.asXML());
				
			}
			
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
}
