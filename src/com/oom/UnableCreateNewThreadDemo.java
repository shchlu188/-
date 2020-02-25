package com.oom;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: chenglu
 * Date: 2020/2/25
 * Description:
 *      java.lang.OutOfMemoryError: unable to create native thread
 *
 *
 *
 */
public class UnableCreateNewThreadDemo {
    public static void main(String[] args){
      for(int i = 0; ; i++) {
          System.out.println(i);
          new Thread(() -> {
                      try {
                          Thread.sleep(Integer.MAX_VALUE);
                      } catch (InterruptedException e) {
                          e.printStackTrace();
                      }
                  }, String.valueOf(i)).start();
      }
    }
}
