package com.oom;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: chenglu
 * Date: 2020/2/25
 * Description:
 */
public class MetaspaceOOMDemo {
    static class OOMTest {
    }

    public static void main(String[] args) {
        int i = 0;

        try {
            while (true) {
                i++;
//                Enhancer enhancer = new Enhancer();
//                enhancer.setSuperClass(OOMTest.class);
//                enhancer.setUseCache(false);
//                enhancer.setCallback(new MethodInterceptor(){
//                    @Override
//                    public Object intercept(Object o,Method method,Object[] objects,MethodProxy methodProxy) throws Throwable{
//                        return methodProxy.invokeSuper(o,args);
//                    }
//                });
//                enhancer.create();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }
}
