package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

public class CreateJarFile {
    public static int BUFFER_SIZE = 10240;

    public static void createJarArchive(File archiveFile, File[] tobeJared) {
        try {
            byte buffer[] = new byte[BUFFER_SIZE];
            // Open archive file
            FileOutputStream stream = new FileOutputStream(archiveFile);
            JarOutputStream out = new JarOutputStream(stream, new Manifest());

            for (File aTobeJared : tobeJared) {
                if (aTobeJared == null || !aTobeJared.exists()
                        || aTobeJared.isDirectory())
                    continue; // Just in case...
                System.out.println("Adding " + aTobeJared.getName());

                // Add archive entry
                JarEntry jarAdd = new JarEntry(aTobeJared.getName());
                jarAdd.setTime(aTobeJared.lastModified());
                out.putNextEntry(jarAdd);

                // Write file to archive
                FileInputStream in = new FileInputStream(aTobeJared);
                while (true) {
                    int nRead = in.read(buffer, 0, buffer.length);
                    if (nRead <= 0)
                        break;
                    out.write(buffer, 0, nRead);
                }
                in.close();
            }

            out.close();
            stream.close();
            System.out.println("Adding completed OK");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) throws IOException {
        File arch = new File("D:/a.jar");
        File classpath = new File(Thread.currentThread().getContextClassLoader().getResource("").getFile());
        createJarArchive(arch, classpath.listFiles());

    }
}