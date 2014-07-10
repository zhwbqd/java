package zhwb.study.classloader;

import javax.sound.midi.Soundbank;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;

import static java.lang.Thread.sleep;

/**
 * Date: 2014/7/10
 * Time: 17:45
 *
 * @author jack.zhang
 */
public class PermGenRelease {
    public static void main(String[] args) throws ClassNotFoundException, MalformedURLException, InterruptedException, IllegalAccessException, InstantiationException {
        File xFile=new File("D:\\Gitworkspace\\Java\\drools\\drools_service\\target\\test-classes");
        URL url= xFile.toURI().toURL() ;
        URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{url});
        Class<?> c = urlClassLoader.loadClass("LoginServiceImplTest");
        System.out.println(c.getClassLoader().equals(urlClassLoader));
        Object fuck = c.newInstance();
        sleep(1000 * 60);
urlClassLoader=null;
        sleep(60*1000);


    }
}
