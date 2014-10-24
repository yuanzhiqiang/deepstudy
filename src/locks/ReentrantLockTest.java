package locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

	
	
	public static void main(String[] args) {
		
		ReentrantLockTest rt = new ReentrantLockTest();
		ReentrantLock from = new ReentrantLock();
		ReentrantLock to = new ReentrantLock();
		while(true){
			if(from.tryLock()){
				try{
					if(to.tryLock()){
						try{
							break;
						}finally{
							to.unlock();
						}
					}
				}finally{
					from.unlock();
				}
			}
		}
		
		
		final ReentrantLock lock = new ReentrantLock();
		new Thread(new Runnable(){
			@Override
			public void run() {
				try{
					lock.lockInterruptibly();
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}finally{
					lock.unlock();
				}
			}
		}).start();
	}
}
