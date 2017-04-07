package pool;

import java.io.FileNotFoundException;


interface phone{
	public void call();
	public void message();
}

class Iphone implements phone{
	public void call() {
		System.out.println("��绰");
	}
	public void message(){
		System.out.println("������");
	}
}
class RingIphone extends Iphone{
	@Override
	public void call() {
		System.out.println("������");
	}
}

class IphoneStrong implements phone{
	Iphone iphone=null;
	public IphoneStrong(Iphone iphone){
		this.iphone=iphone;
	}
	public void call() {
		System.out.println("������");
	}
	public void message(){
		iphone.message();
	}
	
}

public class DecoratorTest {
	public static void main(String[] args) throws FileNotFoundException {
		Iphone iphone=new Iphone();
		iphone.call();
		iphone.message();
		
		System.out.println("===========");
//		��iphone�����е�call�����ĳ�������
//		�ķ��������ַ�ʽ
//		1.�̳�
		RingIphone ringIphone=new RingIphone();
		ringIphone.call();
		ringIphone.message();
		System.out.println("===========");
//		�����������ܽ�iphone����ķ�������
		
//		2.װ�����ģʽ
//		!!!!װ�����ģʽ
//		װ�����ģʽ��IO���е�ʹ��
//		�ֽ���
//		InputStream in=new FileInputStream(new File(""));
//		��װ�����ַ��������ǵײ���Ȼ���ֽ����ڶ�
//		InputStreamReader isr=new InputStreamReader(in);
//		�ַ�����װ����bufferedReader���ײ���Ȼ���ֽ���
//		BufferedReader br= new BufferedReader(isr);
		
//		����
//		1.дһ��װ���࣬Ҫ��װ����ͱ�װ������������ʵ����ͬ�Ľӿڻ�̳���ͬ�ĸ���
//		2.��װ�������ṩ���췽������װ���ߴ��벢����������ڲ�
//		3.���ڣ��������ķ�����ֱ�ӵ���ԭ�ж����ϵķ�����������Ҫ����ķ���ֱ�ӽ��и��켴��
		IphoneStrong is=new IphoneStrong(iphone);
		is.call();
		is.message();
		
	}

}
