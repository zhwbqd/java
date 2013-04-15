package zhwb.test.string;

import java.util.Arrays;

public class MemoryLeak2 {

    private static final int BUFFER_SIZE = 10 * 1024 * 1024;

    private static final char DUMMY_CHAR = 'a';   
   
    public static void main(final String[] args)  {
       
        //Create dummy char array
        char[] bigCharArray = new char[BUFFER_SIZE];
        Arrays.fill(bigCharArray, DUMMY_CHAR);

        //Create String from char array, release dummy char array
        String longString = new String(bigCharArray);
        bigCharArray = null;
       
        //Extract first char of long String and release long String
        String shortString = longString.substring(0,1);
        longString = null;

        //Perform GC and wait for further automatic GCs
        System.gc();
        try {
            Thread.sleep(30000);
            System.gc();
        } catch (InterruptedException ignored) {           
        }       

        //Memory of longString is not released!!
        System.out.println("Memory of long string is not released!");
       
        //Release the shot String reference and perform GC
        shortString = null;
        System.gc();

        //Memory of longString will be released!!       
        System.out.println("Memory of long string will be released!");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException ignored) {           
        }       
        System.out.println("Memory of long string should be released!");
       
        System.exit(0);
    }
   
}