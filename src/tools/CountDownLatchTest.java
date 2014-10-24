package tools;

import java.util.concurrent.CountDownLatch;


/**
 * 闭锁
 * @author Administrator
 *
 */
public class CountDownLatchTest {

	
	public static void main(String[] args) throws InterruptedException {
		int threadCount = 10;
		final CountDownLatch starGate = new CountDownLatch(1);
		
		final CountDownLatch endGate = new CountDownLatch(threadCount);
		
		for(int i = 0; i < threadCount; i++){
			new Thread(new Runnable(){

				@Override
				public void run() {
					try {
						starGate.await();
						Thread.sleep(5000);//do something
					} catch (InterruptedException e) {
						e.printStackTrace();
					}finally{
						endGate.countDown();
					}
				}
				
			}).start();
		}
		long startTime = System.currentTimeMillis();
		starGate.countDown();
		endGate.await();
		long endtTime = System.currentTimeMillis();
		System.out.println("消耗的时间为:" + (endtTime - startTime) / 1000 + "s");
	}
	
	
}
