package zhwb.test.string;

import java.util.ArrayList;
import java.util.List;

public class MemoryLeak {

    private static final int NUMBER_OF_BUFFERS = 15;
    
    private static final int BUFFER_SIZE = 256 * 1024;

    private static final String DUMMY_TEXT = "Hello world! This is a memory leak!";
    
    private String longString;
    
    private int size;
    
    /** Creates a new instance of MemoryLeak */
    public MemoryLeak() {
        StringBuffer sb = new StringBuffer(BUFFER_SIZE);
        int count = (BUFFER_SIZE / DUMMY_TEXT.length());
        for (int i = 0; i < count; i++)  {
            sb.append(DUMMY_TEXT);
            this.size += DUMMY_TEXT.length();
        }
        this.longString = sb.toString();
    }
    
    public String getSubString()  {
        double rand1 = Math.random();
        int begin = (int)Math.round((this.size - 10) * rand1);
        int end = begin + 8;
        return this.longString.substring(begin, end);
    }
    
    public static void main(final String[] args)  {
        List subStrings = new ArrayList(NUMBER_OF_BUFFERS);
        for (int i = 0; i < NUMBER_OF_BUFFERS; i++)  {
            MemoryLeak leak = new MemoryLeak();

            //This call creates memory leaking
            String subString = leak.getSubString();
            
            //This call avoids memory leaking
            //String subString = new String(leak.getSubString());
            
            System.out.println("Extracted substring: " + subString);
            subStrings.add(subString);
        }
        //No release of buffer objects!!
        System.out.println("Keeping the substrings means keeping the whole buffer!");
        for (int i = 0; i < NUMBER_OF_BUFFERS; i++)  {
            System.out.println("List of subStrings: " + subStrings);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex)  {
                System.exit(1);
            }
            System.gc();
        }
        //Releasing substring triggers release of buffer objects!!
        System.out.println("Only releasing the substrings releases the whole buffer!");
        for (int i = 0; i < NUMBER_OF_BUFFERS; i++)  {
            System.out.println("List of subStrings: " + subStrings);
            subStrings.remove(0);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex)  {
                System.exit(1);
            }
            System.gc();
        }
        System.exit(0);
    }
    
}