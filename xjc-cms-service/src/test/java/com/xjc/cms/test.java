package com.xjc.cms;


import java.util.concurrent.*;

class MyThread extends Thread {

    @Override
    public void run() {
        try {
            Thread.sleep(500);
            Thread t = Thread.currentThread();
            System.out.println("当前线程名字：" + t.getName() + " 当前线程的优先级别为："+ t.getPriority() + " ID:" + t.getId());
            // System.out.println("当前线程名字：" + this.getName() + " 当前线程的优先级别为：" + this.getPriority() + " ID:"+ this.getId());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}




public class test {

    public static void main(String[] args) {

        System.out.println("java.io.tmpdir 是默认的临时文件路径:" + System.getProperty("java.io.tmpdir"));
        testThread();

        MyThread oneThread = new MyThread();
        new Thread(oneThread, "test").start();
        new Thread(oneThread, "test1").start();

        // callable


    }

    public static void testThread() {


        // ExecutorService fixedPool = Executors.newFixedThreadPool(15);
        // 创建一个线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 1, TimeUnit.HOURS, new LinkedBlockingQueue<Runnable>(5));
        for (int i = 0; i < 15; i++) {


            int finalI = i;
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(4000);
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("开始执行"+ finalI + "线程");
                }
            });
            System.out.println("线程池中线程数目："+threadPoolExecutor.getPoolSize()+"，队列中等待执行的任务数目："+
                    threadPoolExecutor.getQueue().size()+"，已执行完毕的任务数目："+threadPoolExecutor.getCompletedTaskCount());
        }

    }




    // 测试线程池
    private static test testThreadPool;

    public static test testThreadPool() {
        if (testThreadPool == null) {
            getTestThreadPool();
        }
        return testThreadPool;

    }

    private ExecutorService executorService;

    public static synchronized void getTestThreadPool() {
        if (testThreadPool == null) {
            testThreadPool = new test();
        }
        testThreadPool.executorService = Executors.newFixedThreadPool(1);
    }

    //
    public void execute(Runnable runnable) {
        if (this.executorService != null) {
            executorService.execute(runnable);
        }
    }

}


