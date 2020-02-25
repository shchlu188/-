package com.oom;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: chenglu
 * Date: 2020/2/25
 * Description: StackOverflowError: 栈溢出错误
 *
 *      java.lang.StackOverflowError
 */
public class StackOverflowErrorDemo {
    public static void main(String[] args){
        stackOverFlow();
    }

    private static void stackOverFlow() {
        stackOverFlow();
    }
}
