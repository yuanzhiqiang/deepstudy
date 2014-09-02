package com.yuanzq.thread.concurretinpractice.sector3;

/**
 * NoVisibility
 * <p/>
 * Sharing variables without synchronization
 *@desc 线程可能看不到ready值的改变，一直等下去，同时“重排序”可能导致输出结果为0
 *
 *冲排序：在没有同步的条件下，编译器、处理器、以及运行时都有可能对操作的执行顺序进行意想不到的调整
 * @author Brian Goetz and Tim Peierls
 */

public class NoVisibility {
    private static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread {
        public void run() {
            while (!ready)
                Thread.yield();
            System.out.println(number);
        }
    }

    public static void main(String[] args) {
        new ReaderThread().start();
        number = 42;
        ready = true;
    }
}
