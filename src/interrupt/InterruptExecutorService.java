package interrupt;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class InterruptExecutorService {

	private ExecutorService executor = Executors.newSingleThreadExecutor();
	
	
	
	public void stop() throws InterruptedException {
		executor.shutdown();
		executor.awaitTermination(10, TimeUnit.MINUTES);
	}
	
	
	public  void product(final String msg) throws InterruptedException{
		executor.execute(new Runnable(){
			@Override
			public void run() {
				System.out.println(msg);
			}
		});
	}
}
