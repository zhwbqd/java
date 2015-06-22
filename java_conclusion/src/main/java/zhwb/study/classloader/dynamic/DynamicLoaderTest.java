package zhwb.study.classloader.dynamic;

import com.google.common.collect.Maps;
import zhwb.study.classloader.ByteClassLoader;
import zhwb.study.compiler.CachedCompiler;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import static java.lang.Thread.sleep;

/**
 * @author Administrator
 * @since 2015/6/22 0022
 */
public class DynamicLoaderTest {

    public static final String phone = "public interface Phone {\n" +
            "    void print();" +
            "}";
    public static final String android = "public class Android implements Phone{\n" +
            "    public void print(){\n" +
            "        System.out.println(\"I am Android\");\n" +
            "    }\n" +
            "}";
    public static final String iphone = "public class iPhone implements Phone{\n" +
            "    public void print(){\n" +
            "        System.out.println(\"I am iPhone\");\n" +
            "    }\n" +
            "}\n";

    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, InterruptedException {
        CachedCompiler compiler = new CachedCompiler(null, null);
        Map<String, byte[]> bytes = compiler.compileFromJava("Phone", DynamicLoaderTest.phone);
        System.out.println(bytes.size());
        bytes = compiler.compileFromJava("Android", DynamicLoaderTest.android);
        System.out.println(bytes.size());
        bytes = compiler.compileFromJava("iPhone", DynamicLoaderTest.iphone);
        System.out.println(bytes.size());

        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        ByteClassLoader classLoader = new ByteClassLoader(contextClassLoader, Maps.<String, byte[]>newHashMap());
        classLoader.addClassDef("Phone", bytes.get("Phone"));
        classLoader.addClassDef("Android", bytes.get("Android"));
        classLoader.addClassDef("iPhone", bytes.get("iPhone"));

        Class<?> phone = classLoader.loadClass("Phone");
        System.out.println(phone == null);

        Class<?> android = classLoader.loadClass("Android");
        System.out.println(android == null);

        Class<?> iPhone = classLoader.loadClass("iPhone");
        System.out.println(iPhone == null);

        classLoader = null;

        System.gc();

        sleep(100);

        //jmap -histro, cannot find the class


    }

}
