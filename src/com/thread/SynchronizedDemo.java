package com.thread;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: chenglu
 * Date: 2020/2/23
 * Description:
 */

public class SynchronizedDemo {
    private Object object;
    public void demo(){
        synchronized (object){

        }
        System.out.println("test.......");
    }
    public static void main(String[] args){
    }
}
