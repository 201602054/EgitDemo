package xml;

import java.io.File;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Dom4jDemo {
	public static void main(String[] args) throws DocumentException {
		/*
		 * ʹ��Dom4j(Java��XML API) ��ȡXML�ļ�
		 * 
		 * SAX��һ��XML���������������.�����DOM,SAX��һ���ٶȸ���,����Ч�ķ���
		 * ������ɨ���ĵ�,һ��ɨ��һ�߽���,�����ڽ����ĵ�������ʱ��ֹͣ����
		 * �ŵ�:��������������ʼ,�ٶȿ�,û���ڴ�ѹ��
		 * ȱ��:���ܶԽڵ����޸�
		 * 
		 * DOM������ʽ
		 * DOM��W3C��֯�Ƽ��Ĵ���XML��һ�ַ�ʽ.�ڽ����ĵ�ʱ���ĵ��е�����Ԫ��
		 * ��������ֵĲ�ι�ϵ,������һ����Node����(�ڵ�)
		 * �ŵ�:��xml�ļ����ڴ��й������νṹ,���Ա������޸Ľڵ�
		 * ȱ��:����ļ�����,�ڴ���ѹ��,������ʱ���Ƚϳ�
		 * 
		 * */
		//����SAXReader
		SAXReader  reader=new SAXReader();//��ȡ�ĵ�  ����
		File file =new File("car.xml");
		//����SAXReader��ȡcar.xml
		//�����ȡ�ɹ��ͻᴴ��һ��dom����,��ṹ�����ε�.�������쳣
		//�쳣:xml��ʽ,�ļ��Ҳ���...
		
			Document doc=reader.read(file);//��ȡָ���ļ�
			System.out.println(doc.asXML());
			
			System.out.println("===============================");
		
			/*
			 * Element getRootElement()���ڻ�ȡ��Ԫ��
			 * ElementҪ����dom4j�İ�(���׵���)
			 * */
			
			//0)��ȡ��Ԫ��,��Ϊ���ʵ����
			Element root=doc.getRootElement();
			System.out.println(root.asXML());
			
			System.out.println("//////////////////////");
			
			
			
			//1)��ȡȫ����Ԫ��API  ���ϴ洢����Ԫ��
			List<Element> list=root.elements();
			System.out.println(list.size());//3 
			/*for (Element element : list) {//��ǿforѭ�������ʾÿһ����Ԫ��
				System.out.println(element.asXML());
			}*/
			
			System.out.println("!!!!!!!!!!!!!!!!!!!");
			
			/*
			 * List elements()
			 **/
			//2)��ȡȫ��ָ�����Ƶ���Ԫ��
			list=root.elements("car");
			for (Element element : list) {
				System.out.println(element.asXML());
			}
			
			System.out.println("*****************");
			
			//��ʾȫ����������ɫ
			list=root.elements("car");//��ȡȫ����������Ԫ��
			for (Element element : list) {
				/*
				 * Element element(String)  
				 * */
				//3)�ҵ�Ԫ���е�һ��������������Ԫ��
				Element n=element.element("color");//��ȡ��Ԫ���е�ĳһ����ǩ,���ܴ�s
				System.out.println(n.asXML());
				
				
				/*
				 * String getText()   ��ȡ��ǰԪ�ص��ı�����
				 * String getTextTrim()��ȡ��ǰԪ�ص��ı����ݲ�ȥ��ǰ��հ�
				 * */
				//4)��ȡԪ���е��ı�
				String color=n.getTextTrim();//ȥ���ı�ǰ��Ŀհ�
				System.out.println(color);
				
				
				System.out.println("^^^^^^^^^^^^^^^");
				
				/*
				 * 5)��ȡ��ǰԪ�ص�Ԫ����
				 * */
				String name=n.getName();
				System.out.println(name);
				System.out.println("###############");
				
				/*
				 * 6)Attribute attribute(int index) ��ȡ��ǰԪ��ָ�������� index�±��0��ʼ
				 * Attribute attribute(String name) ��ȡ��ǰԪ��ָ�����ֵ�����
				 * */
				Attribute a=n.attribute(0);//��ȡ��ǰ��һ��Ԫ�ص�����(����ֵ)
				System.out.println("((((((((((((((((");
				String name1=n.attributeValue("name");//��ȡָ�����Ե�ֵ
				System.out.println(name1);
				
				System.out.println(a.asXML());
				System.out.println(a.getName());//��ȡ���Ե�����
				System.out.println(a.getValue());//��ȡ���Ե�ֵ
			}
					
			
	}

	
}
