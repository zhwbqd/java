package util;

import com.google.common.collect.Maps;
import com.google.common.io.Closeables;
import com.google.common.io.Files;

import java.io.*;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

public class JarFileArchiver {
    public static int BUFFER_SIZE = 4096;

    public static void createJarArchive(File archiveFile, File[] tobeJared) throws IOException {
        FileOutputStream stream = null;
        JarOutputStream out = null;
        try {
            byte buffer[] = new byte[BUFFER_SIZE];
            // Open archive file
            stream = new FileOutputStream(archiveFile);
            out = new JarOutputStream(stream, new Manifest());

            for (File aTobeJared : tobeJared) {
                if (aTobeJared == null || !aTobeJared.exists()
                        || aTobeJared.isDirectory()) {
                    continue; // Just in case no directory...
                }
                String fileName = aTobeJared.getName();

                // Add archive entry
                JarEntry jarAdd = new JarEntry(fileName);
                jarAdd.setTime(aTobeJared.lastModified());
                out.putNextEntry(jarAdd);

                // Write file to archive
                FileInputStream in = new FileInputStream(aTobeJared);
                while (true) {
                    int nRead = in.read(buffer, 0, buffer.length);
                    if (nRead <= 0) {
                        break;
                    }
                    out.write(buffer, 0, nRead);
                }
                Closeables.closeQuietly(in);
            }
        } catch (Exception ex) {
            throw new IllegalStateException("Archive occur exception", ex);
        } finally {
            Closeables.close(out, true);
            Closeables.close(stream, true);
        }
    }

    public static byte[] createJarArchive(Map<String, byte[]> tobeJared) throws IOException {
        ByteArrayOutputStream stream = null;
        JarOutputStream out = null;
        try {
            // Open archive file
            stream = new ByteArrayOutputStream(BUFFER_SIZE);
            out = new JarOutputStream(stream, new Manifest());

            long modifiedTime = System.currentTimeMillis();
            for (Map.Entry<String, byte[]> aTobeJared : tobeJared.entrySet()) {
                String fileName = aTobeJared.getKey();

                // Add archive entry
                JarEntry jarAdd = new JarEntry(fileName);
                jarAdd.setTime(modifiedTime);
                out.putNextEntry(jarAdd);
                out.write(aTobeJared.getValue());
            }
            //must close here
            Closeables.close(out, true);
            Closeables.close(stream, true);
            return stream.toByteArray();
        } catch (Exception ex) {
            throw new IllegalStateException("Archive occur exception", ex);
        }
    }

    public static void main(String[] args) throws IOException {
        File file = new File(System.getProperty("user.dir"), "a.jar");
        File[] tobeJared = new File(Thread.currentThread().getContextClassLoader().getResource("").getFile()).listFiles();
        createJarArchive(file, tobeJared);
        System.out.println(file.length());

        Map<String, byte[]> jars = Maps.newHashMap();
        for (File aFile : tobeJared) {
            if (aFile == null || !aFile.exists()
                    || aFile.isDirectory()) {
                continue; // Just in case no directory...
            }
            jars.put(aFile.getName(), Files.toByteArray(aFile));
        }
        byte[] jarArchive = createJarArchive(jars);
        System.out.println(jarArchive.length);

        File fileb = new File(System.getProperty("user.dir"), "b.jar");
        Files.write(jarArchive, fileb);
        System.out.println(fileb.length());
    }
}