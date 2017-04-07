package pool;

import java.io.FileNotFoundException;


interface phone{
	public void call();
	public void message();
}

class Iphone implements phone{
	public void call() {
		System.out.println("打电话");
	}
	public void message(){
		System.out.println("发短信");
	}
}
class RingIphone extends Iphone{
	@Override
	public void call() {
		System.out.println("听彩铃");
	}
}

class IphoneStrong implements phone{
	Iphone iphone=null;
	public IphoneStrong(Iphone iphone){
		this.iphone=iphone;
	}
	public void call() {
		System.out.println("听彩铃");
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
//		将iphone对象中的call方法改成听彩铃
//		改方法的两种方式
//		1.继承
		RingIphone ringIphone=new RingIphone();
		ringIphone.call();
		ringIphone.message();
		System.out.println("===========");
//		可是这样不能讲iphone对象的方法更改
		
//		2.装饰设计模式
//		!!!!装饰设计模式
//		装饰设计模式在IO流中的使用
//		字节流
//		InputStream in=new FileInputStream(new File(""));
//		包装成了字符流，但是底层依然是字节流在读
//		InputStreamReader isr=new InputStreamReader(in);
//		字符流包装成了bufferedReader，底层依然是字节流
//		BufferedReader br= new BufferedReader(isr);
		
//		步骤
//		1.写一个装饰类，要求装饰类和被装饰者所属的类实现相同的接口或继承相同的父类
//		2.在装饰类中提供构造方法，将装饰者传入并保存在类的内部
//		3.对于，不想改造的方法，直接调用原有对象上的方法，对于想要改造的方法直接进行改造即可
		IphoneStrong is=new IphoneStrong(iphone);
		is.call();
		is.message();
		
	}

}
