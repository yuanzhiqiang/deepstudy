package tools;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierTest {

	public static void main(String[] args) {
		int threadCount = 5;
		final CyclicBarrier cyclicbarrier = new CyclicBarrier(threadCount);
		for(int i = 0; i < threadCount; i++){
			new Thread(new Runnable(){
				@Override
				public void run() {
					try {
						System.out.println(Thread.currentThread().getName() + "到达");
						cyclicbarrier.await();
						System.out.println("finished");
					} catch (InterruptedException | BrokenBarrierException e) {
						e.printStackTrace();
					}
				}
			},"线程" + i).start();
		}
	}
}