package executor;

import java.net.URLEncoder;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

public class ThreadFactoryTest implements ThreadFactory{

	@Override
	public Thread newThread(Runnable r) {
		return null;
	}
	
	public static void main(String[] args) {
		Object o = new Object();
		System.out.println(System.identityHashCode(o) == o.hashCode());
	}
}


class MyThread extends Thread{
	
	private Runnable r;
	private static final String DEFAULTNAME = "MyAppThread"; 
	private static volatile boolean debugLifeCycle = false; 
	private static final AtomicLong create = new AtomicLong();
	private static final AtomicLong alived = new AtomicLong();
	
	public MyThread(Runnable r){
		this.r = r;
		setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				System.out.println(t.getName());
			}
		});
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
	}
	
}
