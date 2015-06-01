package zhwb.study.classloader;

import java.security.SecureClassLoader;
import java.util.HashMap;
import java.util.Map;

/**
 * 动态类加载
 *
 * @author jack.zhang
 * @since 2015/6/1
 */
public class ByteClassLoader extends SecureClassLoader {

    private final Map<String, byte[]> extraClassDefs;

    public ByteClassLoader(ClassLoader parent, Map<String, byte[]> extraClassDefs) {
        super(parent);
        this.extraClassDefs = new HashMap<String, byte[]>(extraClassDefs);//shallow copy input map
    }

    public void addClassDef(String name, byte[] bytes) {
        this.extraClassDefs.put(name, bytes);
    }

    @Override
    protected Class<?> findClass(final String name) throws ClassNotFoundException {
        byte[] classBytes = this.extraClassDefs.remove(name);
        if (classBytes != null) {
            return defineClass(name, classBytes, 0, classBytes.length);
        }
        return super.findClass(name);
    }

}