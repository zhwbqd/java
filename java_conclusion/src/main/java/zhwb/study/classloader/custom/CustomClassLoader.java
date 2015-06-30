package zhwb.study.classloader.custom;

import com.google.common.collect.Maps;
import com.google.common.io.Closeables;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.SecureClassLoader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

/**
 * 动态类加载
 *
 * @author jack.zhang
 * @since 2015/6/1
 */
public class CustomClassLoader extends SecureClassLoader {

    private final Map<String, byte[]> classAsBytes;

    private final Map<String, Class> loadedClass;


    public CustomClassLoader(ClassLoader parent) {
        super(parent);
        this.loadedClass = Maps.newConcurrentMap();
        this.classAsBytes = Maps.newConcurrentMap();
    }

    public Map<String, byte[]> getAddedResources() {
        Map<String, byte[]> maps = new HashMap<>(classAsBytes.size());
        for (Map.Entry<String, byte[]> entry : classAsBytes.entrySet()) {
            byte[] bytes = entry.getValue();
            maps.put(entry.getKey(), Arrays.copyOf(bytes, bytes.length));
        }
        return maps;
    }

    /**
     * 将流进行解析并保存
     *
     * @param jarStream
     */
    public void add(InputStream jarStream) {
        BufferedInputStream bis = null;
        JarInputStream jis = null;
        try {
            bis = new BufferedInputStream(jarStream);
            jis = new JarInputStream(bis);

            JarEntry jarEntry = null;
            while ((jarEntry = jis.getNextJarEntry()) != null) {
                if (jarEntry.isDirectory()) {
                    continue;
                }

                byte[] b = new byte[2048];
                ByteArrayOutputStream out = new ByteArrayOutputStream();

                int len = 0;
                while ((len = jis.read(b)) > 0) {
                    out.write(b, 0, len);
                }
                String className = jarEntry.getName().substring(0, jarEntry.getName().indexOf(".class")).replace("/", ".");
                classAsBytes.put(className, out.toByteArray());

                out.close();
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            Closeables.closeQuietly(bis);
            Closeables.closeQuietly(jis);
        }
    }

    @Override
    protected Class<?> findClass(final String name) throws ClassNotFoundException {
        Class aClass = loadedClass.get(name);
        if (aClass != null) {
            return aClass;
        }
        byte[] classBytes = this.classAsBytes.get(name);
        if (classBytes != null) {
            Class<?> defineClass = defineClass(name, classBytes, 0, classBytes.length);
            loadedClass.put(name, defineClass);
            return defineClass;
        }
        return super.findClass(name);
    }

}