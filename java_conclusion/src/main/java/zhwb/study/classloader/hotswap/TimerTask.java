package zhwb.study.classloader.hotswap;

import java.lang.reflect.Method;

import static java.lang.Thread.sleep;

/**
 * Date: 2014/7/11
 * Time: 8:54
 *
 * @author jack.zhang
 */
public class TimerTask {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        sleep(1 * 1000);
                        try {
                            // 每次都创建出一个新的类加载器
                            HotswapCL cl = new HotswapCL("target/classes/swap", new String[]{"Foo"});
                            Class cls = cl.loadClass("Foo");
                            Object foo = cls.newInstance();

                            Method m = foo.getClass().getMethod("sayHello", new Class[]{});
                            m.invoke(foo, new Object[]{});

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
