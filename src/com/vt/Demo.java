package com.vt;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: chenglu
 * Date: 2020/2/21
 * Description:
 */
public class Demo {
    volatile int num = 0;

    public void add() {
        num++;
    }

    public void mySort() {
        int x = 11;
        int y = 12;
        x = x + 5;
        y = x * x;
    }

    public static void main(String[] args) {

    }
}
