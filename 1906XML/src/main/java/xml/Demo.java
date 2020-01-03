package xml;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
/**
 * ֱ�Ӷ�ȡXML����Ԫ�ص��ı�
 * @author Tedu
 *
 */
public class Demo {
	public static void main(String[] args) throws DocumentException {
		SAXReader reader=new SAXReader();//����SAXReader
		Document doc=reader.read("car.xml");//��ȡָ���ļ�
		Element root=doc.getRootElement();//��ȡ��Ԫ��
		List<Element> cars=root.elements("car");//��ȡָ�����Ƶ�ȫ����Ԫ��
		int i=1;
		for (Element element : cars) {
			String color=element.elementTextTrim("color");//ֱ�Ӷ�ȡָ����Ԫ�����Ե�����
			System.out.println("��"+i+"��color��������"+color);
			i++;
		}
	}
}
