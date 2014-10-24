package tools;

import java.util.concurrent.Exchanger;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ExchangerTest {

	
	
	public static void main(String[] args) {
		final Exchanger changer = new Exchanger();
		Executor execute = Executors.newCachedThreadPool();
		execute.execute(new Runnable(){
			@SuppressWarnings("unchecked")
			@Override
			public void run() {
				String data = "data1";
				try {
					data = (String)changer.exchange(data);
					System.out.println("Thread1:" + data);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		execute.execute(new Runnable(){
			@SuppressWarnings("unchecked")
			@Override
			public void run() {
				String data = "data2";
				try {
					data = (String)changer.exchange(data);
					System.out.println("Thread2:" + data);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
