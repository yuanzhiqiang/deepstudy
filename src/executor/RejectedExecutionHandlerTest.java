package executor;

import java.util.concurrent.ThreadPoolExecutor;


public class RejectedExecutionHandlerTest {

	
	public static void main(String[] args) {
//		Executor executor = new ThreadPoolExecutor(0,
//                0,
//                1000,
//                TimeUnit.MILLISECONDS,
//                new SynchronousQueue<Runnable>(),
//                new Executors.ThreadFactory(),
//                new ThreadPoolExecutor.CallerRunsPolicy());
		new ThreadPoolExecutor.CallerRunsPolicy();
	}
}
