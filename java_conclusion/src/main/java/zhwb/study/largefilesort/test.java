package zhwb.study.largefilesort;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class test
{
    public test()
    {
    }

    public static void main(final String[] args)
    {
        try {
            long lo = System.currentTimeMillis();
            java.io.RandomAccessFile raf = new java.io.RandomAccessFile("C:\\LargeFileSort\\DataFile.txt", "rw");
            raf.setLength(1024 * 1024 * 100);
            raf.close();
            System.out.println(System.currentTimeMillis() - lo);

            lo = System.currentTimeMillis();
            File file = new File("C:\\LargeFileSort\\DataFile.txt");

            System.out.println(System.currentTimeMillis() - lo + "    " + file.length());

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}