package interrupt;

import java.util.HashSet;
import java.util.Set;

public class UncaughtExceptionTest {

	
	public static void main(String[] args) throws InterruptedException {
		final Set<Thread> unreachedTask = new HashSet<Thread>();
		Thread myThread = new Thread(new Runnable(){

			@Override
			public void run() {
				throw new RuntimeException("test");
			}
			
		},"Mythread"); 
		myThread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
			
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				System.out.println(t.getName());
				System.out.println(e.getMessage());
				unreachedTask.add(t);
			}
		});
		myThread.start();
		Thread.sleep(10000);
//		for(Thread thread: unreachedTask){
//			thread.start();
//		}
////		Executor e = Executors.newCachedThreadPool();
	}
	
}
