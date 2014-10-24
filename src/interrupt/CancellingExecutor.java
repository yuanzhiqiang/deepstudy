package interrupt;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CancellingExecutor extends ThreadPoolExecutor{

	public CancellingExecutor(int corePoolSize, int maximumPoolSize,
			long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}
	@Override
	protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
		if(callable instanceof CallableTask){
			return ((CallableTask)callable).newTaskFor();
		}
		return super.newTaskFor(callable);
	}
}

interface CallableTask extends Callable{
	
	void cancel();
	RunnableFuture newTaskFor();
}

class SocketUsingTask implements CallableTask{

	Socket socket;
	
	public  synchronized void setSocket(){
		this.socket = socket;
	}
	
	@Override
	public synchronized void cancel() {
		if(null != socket){
			try{
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public RunnableFuture newTaskFor() {
		return new FutureTask(this){
			@Override
			public boolean cancel(boolean mayInterruptIfRunning) {
				try{
					SocketUsingTask.this.cancel();
				}finally{
					return super.cancel(mayInterruptIfRunning);
				}
			}
		};
	}

	@Override
	public Object call() throws Exception {
		System.out.println("call invoked");
		return "call invoked";
	}
}
