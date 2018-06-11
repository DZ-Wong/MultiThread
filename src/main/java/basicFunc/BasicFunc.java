package main.java.basicFunc;

/**
 * 多线程基本技能。
 */
public class BasicFunc {


    // 简单创建
    public static void test1() {
        //extends
        MyThread myThread = new MyThread();
        myThread.start();
        System.out.println("运行结束");
    }

    //1.run调用的随机性
    public static void test2() {
        //展示随机性 1；
        try {
            RandomTest randomTest = new RandomTest();
            Thread thread = new Thread(randomTest);
            thread.start();
            thread.setName("random");
            for (int i = 0; i < 10; i++) {
                int time = (int) (Math.random() * 1000);
                Thread.sleep(time);
                System.out.println("main=" + Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //2.start调用的随机性。
    public static void test3() {
        //2.start调用的随机性。
        RandomTest2 t1 = new RandomTest2(1);
        RandomTest2 t12 = new RandomTest2(2);
        RandomTest2 t13 = new RandomTest2(3);
        RandomTest2 t14 = new RandomTest2(4);
        RandomTest2 t15 = new RandomTest2(5);
        RandomTest2 t16 = new RandomTest2(6);
        RandomTest2 t17 = new RandomTest2(7);
        RandomTest2 t18 = new RandomTest2(8);
        RandomTest2 t19 = new RandomTest2(9);
        RandomTest2 t10 = new RandomTest2(10);
        RandomTest2 t111 = new RandomTest2(11);
        RandomTest2 t112 = new RandomTest2(12);
        t1.start();
        t12.start();
        t13.start();
        t14.start();
        t15.start();
        t16.start();
        t17.start();
        t18.start();
        t19.start();
        t10.start();
        t111.start();
        t112.start();

    }

    //3.不共享数据的情况
    public static void test4() {
        //关键：运行对象都是不同的
        NoShare a = new NoShare("A");
        NoShare b = new NoShare("B");
        NoShare c = new NoShare("c");
        a.start();
        b.start();
        c.start();
    }
    //4.线程共享数据的情况
    public static void test5() {
        Share share = new Share();
        Thread a = new Thread(share, "A");
        Thread b = new Thread(share, "B");
        Thread c = new Thread(share, "C");
        Thread d = new Thread(share, "D");
        Thread e = new Thread(share, "E");
        a.start();
        b.start();
        c.start();
        d.start();
        e.start();
    }

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
//        test1();
//        test2();
//        test3();
//        test4();
        test5();
    }

}

/**
 * 类通过继承Thread实现。
 */
class MyThread extends Thread {
    @Override
    public void run() {
        super.run();
        System.out.println("MyThread");
    }
}
/**
 * 演示随机性。
 * 1.run调用的随机性
 */
class RandomTest implements Runnable {
    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                int time = (int) (Math.random() * 1000);
                Thread.sleep(time);
                System.out.println("run=" + Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
/**
 * 演示随机性。
 * 2.start调用的随机性。
 */
class RandomTest2 extends Thread {
    @Override
    public void run() {
        System.out.println(i);
    }

    private int i;
    public RandomTest2(int i) {
        super();
        this.i = i;
    }
}

/**
 * 数据的共享与不共享 区别
 * 1.不共享的情况
 */
class NoShare extends Thread {
    private int count = 5;
    public NoShare(String name) {
        super();
        this.setName(name);//设置线程名称
    }
    @Override
    public void run() {
        super.run();
        while (count > 0) {
            count--;
            System.out.println("由 " + this.currentThread().getName() +
                    "  计算，count = " + count);
        }
    }
}

/**
 * 2.共享数据的情况
 */
class Share extends Thread {
    private int count = 5;

    @Override
    public void run() {
        super.run();
        //这里不能加for语句，使用同步后其他线程就没有机会运行了
        count--;
        System.out.println("由 " + this.currentThread().getName() +
                "  计算，count = " + count);
    }
}