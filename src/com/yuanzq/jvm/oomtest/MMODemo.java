package com.yuanzq.jvm.oomtest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class MMODemo {
	

	
	StackOverFlowMMO stackmmo;
	DirectMMO directmmo;
	HeapMMO heapmmo;
	ArrayLimitMMO almmo;
	ThreadLimitMMO threadmmo;
	PermGenMMO pergemmmo;
	MMODemo(){
		stackmmo = new StackOverFlowMMO();
		directmmo = new DirectMMO();
		heapmmo = new HeapMMO();
		almmo = new ArrayLimitMMO();
		threadmmo = new ThreadLimitMMO();
		pergemmmo = new PermGenMMO();
	}
	
	public void displayDirect(){
		directmmo.display();
	}
	public void displayStack(){
		stackmmo.display();
	}
	public void displayHEAP(){
		heapmmo.display();
	}
	public void displayArryaLimit(){
		almmo.display();
	}
	public void displayThreadLimit() throws InterruptedException{
		threadmmo.display();
	}
	public void displayPermGen() throws IllegalArgumentException, 
											Exception, 
											Throwable{
		pergemmmo.display();
	}
	class DirectMMO{
		public void display(){
			List<ByteBuffer> list=new ArrayList();		
			int size=1000;
			int count=0;
			while(true){			
				ByteBuffer buf=ByteBuffer.allocateDirect(size);
				for(int i=0;i<size;i++){
					int j=1;
					buf.put((byte)j);
				}
				list.add(buf);
				count++;
				System.out.println("buffer direct allocate is "+count*size);
			}			
		}		
	}

	
	
	class StackOverFlowMMO{
		int lvl;
		StackOverFlowMMO(){
			lvl=0;
		}
		public void display(){
			lvl=0;
			func();
		}
		
		public void func(){
			++lvl;
			System.out.println("lvl "+lvl);
			func();
		}
	}
	
	class HeapMMO{
		
		public void display(){
			Integer a[] = new Integer[100000000];		
		}
	}
	
	class ArrayLimitMMO{	
		public void display(){			
			System.out.println("Integer.MAX_VALUE is "+Integer.MAX_VALUE +"\r\n");
			int[] pos=new int[Integer.MAX_VALUE-1];
		}
	}
	
	class PermGenMMO{
		Helloworld loader = new Helloworld();
		//List<Object> list = new ArrayList();
		public void display() throws IllegalArgumentException,
										Exception, 
										Throwable{
			
			int index=0;
			int count=0;
        	while(true){
            		
				try {
					loader.createClass(index);
					loader.loadClass(index);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				//System.out.println(System.getProperty(null));
				count++;
				index++;
				if(count>10)
					break;
				
				  
            } 
			
		}	
		
		// 动态创建一个类
		class Helloworld extends ClassLoader implements Opcodes{
			public void createClass(int index) throws IOException, 
													IllegalAccessException, 
													IllegalArgumentException, 
													Exception, 
													Throwable{
				
				String name = "Example"+index;
		        //System.out.println(name);
				ClassWriter cw = new ClassWriter(0);
				MethodVisitor mv;
				cw.visit(V1_1, ACC_PUBLIC, name, null, "java/lang/Object", null);
				
				for(int i=0; i<10000;i++){
					String fun ="fun"+i;
					mv = cw.visitMethod(ACC_PUBLIC, fun, "()V", null, null);
					mv.visitCode();
					mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
					mv.visitLdcInsn("hello");
					mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
					mv.visitInsn(RETURN);
					mv.visitMaxs(2, 1);
					mv.visitEnd();					
				}
		

		        // gets the bytecode of the Example class, and loads it dynamically
		        byte[] code = cw.toByteArray();

		        File directory = new File("");
		        FileOutputStream fos = new FileOutputStream(name+".class");
		        fos.write(code);
		        fos.close();
		        
		        
					//Class<?> exampleClass = loader.defineClass("Example"+index, code, 0, code.length);
	
				    // uses the dynamically generated class to print 'Helloworld'
				    //exampleClass.getMethods()[0].invoke(null, new Object[] { null });
				    
		       
				
			}
			
			public void loadClass(int index) throws ClassNotFoundException, InstantiationException, IllegalAccessException{				
				String name="Example"+index;
				
				Class a=Class.forName(name,true,this.getClass().getClassLoader());
				//System.out.println("load "+name+".class");
				
			}
		}
		
	}
	
	class ThreadLimitMMO{
		List<Thread> list = new ArrayList();
		int count=0;
		Long cur;
		Object lock = new Object();		
		public void display() throws InterruptedException{
			System.out.println("-Xss = 128m");
			while(true){
				Long cur = new Long(System.currentTimeMillis());				
				
					Thread t = new Thread(cur.toString()){  
			            @Override  
			            public void run() {  
			                int index=0;
			                synchronized(lock){
			                	index = count;
			                	count++;
			                }
			                
			                System.out.println(Thread.currentThread().getName()+"create,index"+index);
			                try {
								Thread.currentThread().sleep(60000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
			            }  
					}; 
					t.start();
					list.add(t);				
				Thread.currentThread().sleep(100);				
			}	
		}
	}	
}