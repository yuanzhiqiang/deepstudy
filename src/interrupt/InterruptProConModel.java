package interrupt;

import java.util.concurrent.BlockingQueue;



public class InterruptProConModel {

	private final BlockingQueue<String> queue;
    private final CunstomerThread customerThread;
    private boolean isShutdown;
    private int reservations;
    
    public InterruptProConModel(BlockingQueue<String> queue, CunstomerThread customerThread){
    	this.queue = queue;
    	this.customerThread = customerThread;
    }
    
    public void start() {
    	customerThread.start();
    }
    
    public void stop() {
    	synchronized(this){
    		isShutdown = true;
    	}
    	customerThread.interrupt();
    }
    
    public  void product(String msg) throws InterruptedException{
    	synchronized(this){
    		if(isShutdown){
    			 throw new IllegalStateException(/*...*/);
    		}
    		reservations++;
    	}
    	queue.put(msg);
    }
    
    private class CunstomerThread extends Thread{
    	@Override
    	public void run() {
    		while(true){
    			synchronized(InterruptProConModel.this){
	    			if(isShutdown && 0 == reservations){
	    				break;
	    			}
    			}
    			try {
					System.out.println(queue.take());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			synchronized(InterruptProConModel.this){
    				reservations--;
    			}
    		}
    	}
    }
}
