package zhwb.study.classloader.dynamic;

import org.xeustechnologies.jcl.JarClassLoader;

/**
 * @author jack.zhang
 * @since 2015/6/22 0022
 */
public class JclLoderTest {

    static JarClassLoader jarClassLoader = new JarClassLoader();

    public static void main(String[] args) throws Exception {

        //load a storm jar
        jarClassLoader.getCurrentLoader().setEnabled(false);
        jarClassLoader.getParentLoader().setEnabled(false);
//        jarClassLoader.getSystemLoader().setEnabled(false);
        jarClassLoader.getThreadLoader().setEnabled(false);


        jarClassLoader.add("D:\\test\\origin.jar");

        String className = "zhwb.study.classloader.dynamic.A";
        Class aClass = jarClassLoader.loadClass(className);
        System.out.println(aClass.getClassLoader());
        aClass.getMethod("print", null).invoke(aClass.newInstance(), null);//origin


        jarClassLoader.unloadClass(className);//unload

        jarClassLoader.add("D:\\test\\changed.jar");
        aClass = jarClassLoader.loadClass(className);
        System.out.println(aClass == null);
        aClass.getMethod("print", null).invoke(aClass.newInstance(), null);//changed

    }
}
