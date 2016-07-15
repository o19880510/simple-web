package woo.study.web.common.util;


/**
 * 
 * 线程工具类
 * 
 * Thread.sleep、Object.wait 方法会抛出异常，本类方法将异常简单包裹
 * 
 * @author tianjp
 */
public class ThreadUtils {

	public static void sleep(long time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void wait(Object obj, long time){
		try {
			obj.wait(time);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void wait(Object obj){
		try {
			obj.wait();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
