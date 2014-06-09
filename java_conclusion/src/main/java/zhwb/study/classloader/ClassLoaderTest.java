package zhwb.study.classloader;

/**
 * Date: 14-6-8
 * Time: 下午9:58
 *
 * @author jack.zhang
 */
public class ClassLoaderTest{
    public static void main(String[] args) {
        System.out.println(ClassLoaderTest.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        System.out.println(ClassLoaderTest.class.getResource("").getPath());//同包
        System.out.println(ClassLoaderTest.class.getResource("/").getPath());//classpath
        System.out.println(ClassLoaderTest.class.getClassLoader().getResource("").getPath());//classpath
        System.out.println(ClassLoaderTest.class.getClassLoader().getResource("/"));//null
        System.out.println(System.getProperty("user.dir"));
        System.out.println(Thread.currentThread().getContextClassLoader().getResource("").getPath());
        System.out.println(Thread.currentThread().getContextClassLoader().getResource("test.props"));
        System.out.println(ClassLoader.getSystemClassLoader().getResource("").getPath());
    }
}
