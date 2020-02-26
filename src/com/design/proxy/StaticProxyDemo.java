package com.design.proxy;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: chenglu
 * Date: 2020/2/26
 * Description: 静态代理
 *
 */
public class StaticProxyDemo {

    public static void main(String[] args){
        // target Object
        TeacherDao teacherDao = new TeacherDaoImp();
        // proxy Object
        TeacherDao teacherDaoProxy = new TeacherDaoProxy(teacherDao);
        // execute proxy method
        teacherDaoProxy.teach();

    }
}


interface TeacherDao{
    void teach();
}


class TeacherDaoImp implements TeacherDao{
    @Override
    public void teach() {
        System.out.println(" teaching ............");
    }
}


class TeacherDaoProxy implements TeacherDao{
    private TeacherDao target;

    public TeacherDaoProxy(TeacherDao target){
        this.target = target;
    }
    @Override
    public void teach() {
        System.out.println("proxy before.....");
        target.teach();
        System.out.println("proxy after......");

    }
}