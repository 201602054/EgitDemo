package xml.��ϰ;

import java.util.List;

import javax.swing.text.StyledEditorKit.ForegroundAction;
import javax.swing.text.html.HTML.Attribute;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Aoo {
	public static void main(String[] args) throws DocumentException {
		//����SAXReader
		SAXReader reader=new SAXReader();
		//����Document
		Document doc=reader.read("books.xml");
//		System.out.println(doc.asXML());//��ȡȫ����Ϣ:����ͷ
		
		//��ȡ��Ŀ¼
		Element root=doc.getRootElement();
//		System.out.println(root.asXML());//��ȡ��Ŀ¼
		
		
		//��ȡȫ����Ŀ¼
		List<Element> list=root.elements();
		/*for (Element e : list) {
			System.out.println(e.asXML());
		}*/
		
		
		//��ȡָ����ȫ����Ŀ¼
		list=root.elements("book");
		/*for (Element e : list) {
			System.out.println(e.asXML());
		}*/
		
		
		//��ȡԪ����һ��������������Ԫ��
		/*for (Element e : list) {
			Element ele=e.element("name");
			System.out.println(ele.asXML());
		}*/
		
		
		//��ȡԪ�ص��ı�
/*		for (Element e : list) {//list�зŵ���book��ǩ��Ԫ��
			String s=e.elementTextTrim("name");//���ص���book��ǩ��Ԫ��name��ǩ���ı�����
			System.out.println(s);
//			Element el=e.element("name");
//			String ss=el.getTextTrim();
//			System.out.println(ss);   47-49�ȼ���45-46����
		}*/
		
		
		//��ȡԪ�ص�����
/*		for(Element e:list){
			Element ee=e.element("name");//��ȡbookԪ���µ���Ԫ��name
			String s0=ee.getName();//��ȡnameԪ�ص�����
			System.out.println(s0);
		}*/
		
		
		//��ȡԪ�ص�����(��ֵ)
/*		for (Element e : list) {
			String id=e.attributeValue("id");
			System.out.println(id);              //67-68���ص���bookԪ�ص�id���Ե�ֵ
//			org.dom4j.Attribute a=e.attribute(0);
//			System.out.println(a.asXML());         69-70���ص���bookԪ�صĵ�һ������
		}*/
		
		
		//��ȡ���Ե�����,ֵ
		for (Element e : list) {
			org.dom4j.Attribute attr=e.attribute(0);//��ȡ��ǰ��ǩ�е�һ������
			//String attr=e.attributeValue("id"); //��ȡ��ǰ��ǩ��ָ�����Ե�ֵ
			System.out.println(attr.getName());
			System.out.println(attr.getValue());
		}
	}
}
