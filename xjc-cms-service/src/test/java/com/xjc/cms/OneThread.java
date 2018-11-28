package com.xjc.cms;

/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * <p>
 * Created by xiangjiangcheng on 2018/11/28 10:34.
 */
public class OneThread implements Runnable{
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println("我是实现Runnable接口");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
