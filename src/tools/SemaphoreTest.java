package tools;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

public class SemaphoreTest<T> {

	private final Set<T> set;
	
	private final Semaphore semaphore;
	
	public SemaphoreTest(int bound){
		set = Collections.synchronizedSet(new HashSet());
		semaphore = new Semaphore(bound);
	}
	
	public boolean add(T t){
		boolean result = false;
		try {
			semaphore.acquire();
			result = set.add(t);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			semaphore.release();
		}
		return result;
	}
	
	public boolean remove(T t){
		boolean result = false;
		try {
			semaphore.acquire();
			set.remove(t);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			semaphore.release();
		}
		return result;
	}
}
