package zhwb.study.unsafe;

import java.io.File;
import java.io.FileInputStream;

/**
 * 可以进行动态类装载
 *
 * @author jack.zhang
 * @since 2015/2/26
 */
public class DynamicClassLoader {


    public static void main(String[] args) throws Exception {
        ObjSize object = (ObjSize) getObject("D:\\ObjSize.class");
        System.out.println(object.a);
    }

    private static Object getObject(String file) throws Exception {
        byte[] classContents = getClassContent(file);
        Class c = UnSafeFactory.getInstance().defineClass(
                null, classContents, 0, classContents.length);
        return c.newInstance();
    }

    private static byte[] getClassContent(String file) throws Exception {
        File f = new File(file);
        FileInputStream input = new FileInputStream(f);
        byte[] content = new byte[(int)f.length()];
        input.read(content);
        input.close();
        return content;
    }
}
