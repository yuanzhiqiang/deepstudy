package aqs;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class CLHLock {
    public static class CLHNode {
        private boolean isLocked = true; // 默认是在等待锁
    }
    
    //http://blog.csdn.net/rommel1/article/details/19132621
    //http://ifeve.com/introduce-abstractqueuedsynchronizer/
    
    
    @SuppressWarnings("unused" )
    private volatile CLHNode tail ;
	private static final AtomicReferenceFieldUpdater<CLHLock, CLHNode> UPDATER = AtomicReferenceFieldUpdater
                  . newUpdater(CLHLock.class, CLHNode .class , "tail" );
					//包含该字段的对象的类   将被更新的对象的类  将被更新的字段的名称 

    
	public void lock(CLHNode currentThread) {
        CLHNode preNode = UPDATER.getAndSet( this, currentThread);
        if(preNode != null) {//已有线程占用了锁，进入自旋
            while(preNode.isLocked ) {
            }
        }
    }

    public void unlock(CLHNode currentThread) {
        // 如果队列里只有当前线程，则释放对当前线程的引用（for GC）。
        if (!UPDATER .compareAndSet(this, currentThread, null)) {
            // 还有后续线程
            currentThread.isLocked = false ;// 改变状态，让后续线程结束自旋
        }
    }
}