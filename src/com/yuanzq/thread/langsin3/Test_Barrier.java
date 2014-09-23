package com.yuanzq.thread.langsin3;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test_Barrier {

	private static int[] timeWalk = { 5, 8, 15, 15, 10 };
	private static int[] timeSelf = { 1, 3, 4, 4, 5 };
	private static int[] timeBus = { 2, 4, 6, 6, 7 };

	static class Tour implements Runnable {
		private int[] times;
		private CyclicBarrier barrier;
		private String tourName;

		public Tour(CyclicBarrier barrier, String tourName, int[] times) {
			this.times = times;
			this.tourName = tourName;
			this.barrier = barrier;
		}

		public void run() {
			try {
				Thread.sleep(times[0] * 1000);
				System.out.println("We " + tourName + " reachd Shenzhen");
				barrier.await();

				Thread.sleep(times[1] * 1000);
				System.out.println("We " + tourName + " reachd Beijing");
				barrier.await();

				Thread.sleep(times[2] * 1000);
				System.out.println("We " + tourName + " reachd Shaghai");
				barrier.await();

				Thread.sleep(times[3] * 1000);
				System.out.println("We " + tourName + " reachd Haerbin");
				barrier.await();

				Thread.sleep(times[4] * 1000);
				System.out.println("We " + tourName + " reachd Daqing");
				barrier.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	
	
	public static void main(String[] args) {
		
		CyclicBarrier barrier = new CyclicBarrier(3);
		
		ExecutorService service = Executors.newFixedThreadPool(3);
		
		service.submit(new Tour(barrier, "Walking", timeWalk));
		service.submit(new Tour(barrier, "Self", timeSelf));
		service.submit(new Tour(barrier, "Bus", timeBus));
		service.shutdown();
	}
}
