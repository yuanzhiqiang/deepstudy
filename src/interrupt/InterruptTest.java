package interrupt;

public class InterruptTest {

	
	static class SleepInterrupt implements Runnable{

		@Override
		public void run(){
				try {
					System.out.println("first:" + Thread.currentThread().isInterrupted());
					System.out.println("second:" + Thread.currentThread().isInterrupted());
					Thread.sleep(500000);
				} catch (InterruptedException e) {
					System.out.println("third:" + Thread.currentThread().isInterrupted());
					Thread.currentThread().interrupt();
					System.out.println("fourth:" + Thread.currentThread().isInterrupted());
					e.printStackTrace();
					while(true){}//线程执行完成之后中断标志将被设置为false
				}
		}
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new SleepInterrupt());
		t1.start();
		t1.interrupt();
		Thread.sleep(5000);
		System.out.println("main:" + t1.isInterrupted());
		System.out.println("main:" + t1.isInterrupted());
		System.out.println("main:" + t1.isInterrupted());
		System.out.println("main:" + t1.isInterrupted());
		System.out.println("main:" + t1.isInterrupted());
	}
	
}
