package com.jvm;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: chenglu
 * Date: 2020/2/24
 * Description: jvm体系结构
 *      类加载器：(Class Loader)
 *          类的加载器是什么？
 *
 *          双亲委派机制
 *
 *          沙箱安全机制
 *     运行时数据区(Runtime Data Area)
 *          方法区：(Method Area)  线程共享
 *          堆区 (Heap)   线程共享
 *          Java栈 (Stack)
 *          本地方法栈 (Native Method Stack)
 *          程序计数器：(Program Counter Register)
 *     执行引擎(Execution Engine)
 *
 *     本地方法接口(Native Interface)
 *
 *
 *      常见的垃圾算法：
 *          引用计数法
 *          复制算法
 *          标记清除算法
 *          标记整理
 *
 *          垃圾： 简单来说就是内存已经不再使用到的空间被称为垃圾
 *     GC Root:
 *     (虚拟机栈《栈帧中的局部变量区-->也叫局部变量表》中的引用对象、
 *      方法区中的类静态属性引用的对象、
 *      方法区中常量引用的对象、
 *      本地方法栈中JNI(Native方法)引用的对象)
 *          引用计数法：(很难解决循环引用对象)
 *          枚举根节点做可达性分析(根搜索路径)
 *
 *     JVM的参数类型
 *          jinfo 查看当前运行的程序的配置
 *          标配参数：
 *              -version
 *              -help
 *              -showversion
 *          X参数：
 *              -Xint 解释执行
 *              -Xcomp 第一次使用就编译成本地代码
 *              -Xmixed 混合模式
 *
 *          XX参数：
 *              boolean类型
 *                  公式：-XX: +或者-某个属性值
 *                          +：表示开启
 *                          -：表示关闭
 *                  case：
 *                      是否打印GC收集细节
 *                      是否使用串行垃圾回收器
 *              KV设值类型
 *                  公式： -XX: 属性key=属性值value
 *                  case：
 *                      -XX:MetaspaceSize=128m
 *                      -XX:MaxTenuringThreshold=15
 *
 *             -Xms 等价于 -XX:InitialHeapSize 初始内存大小 默认为物理内存的1/64
 *             -Xmx 等价于 -XX:MaxHeapSize 最大分配内存 默认为物理内存的1/4
 *             -Xss 等价于 -XX:ThreadStackSize 设置单个线程的栈的大小， 一般默认为512K~1024K
 *             -Xmn 设置年轻代大小
 *             -XX:+PrintGCDetails: 打印GC详细信息  === -Xlog:gc*
 *             -XX:SurvivorRatio 设置新生代中eden和s0/s1空间的比列,默认为 -XX:SurvivorRatio=8, Eden:S0:S1=8:1:1
 *             -XX:NewRatio 配置新生代和老年代在堆结构的占比，默认 -XX:NewRatio=2 new:old=1:2,年轻代占堆内存的1/3
 *             -XX:MaxTenuringThreshold 设置分代年龄
 *             -XX:MetaspaceSize 设置元空间大小
 *             -XX:+PrintFlagsInitial 主要查看初始默认 （java -XX:+PrintFlagsInitial）
 *             -XX:+PrintFlagsFinal 主要查看修改更新 （java -XX:+PrintFlagsFinal -version）
 *             -XX:+PrintCommandLineFlags 打印命令行参数 java -XX:+PrintCommandLineFlags -version
 *
 *         默认配置：-Xms128m -Xmx4096m -Xss1024k -XX:MetaspaceSize=512m -XX:+PrintCommandLineFlags -Xlog:gc*
 */
public class JvmDemo {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("GC...............");
        /*
            ava.lang.OutOfMemoryError: Java heap space
         */
//        byte[] bytes = new byte[50*1024*1024];
        Thread.sleep(Integer.MAX_VALUE);
    }
    /*
            -XX:G1ConcRefinementThreads=2
            -XX:GCDrainStackTargetSize=64
            -XX:InitialHeapSize=57479808
            -XX:MaxHeapSize=919676928
            -XX:+PrintCommandLineFlags
            -XX:ReservedCodeCacheSize=251658240
            -XX:+SegmentedCodeCache
            -XX:+UseCompressedClassPointers
            -XX:+UseCompressedOops
            -XX:+UseG1GC
            -XX:-UseLargePagesIndividualAllocation
     */
    /*
        -XX:G1ConcRefinementThreads=2
        -XX:GCDrainStackTargetSize=64
        -XX:InitialHeapSize=134217728
        -XX:MaxHeapSize=4294967296
        -XX:MetaspaceSize=536870912
        -XX:+PrintCommandLineFlags
        -XX:+PrintGCDetails
        -XX:ReservedCodeCacheSize=251658240
        -XX:+SegmentedCodeCache
        -XX:ThreadStackSize=1024
        -XX:+UseCompressedClassPointers
        -XX:+UseCompressedOops
        -XX:+UseG1GC
        -XX:-UseLargePagesIndividualAllocation
     */
}
