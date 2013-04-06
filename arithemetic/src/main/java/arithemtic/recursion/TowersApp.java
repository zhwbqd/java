package arithemtic.recursion;

// towers.java
// solves Towers of Hanoi puzzle
// to run this program: C>java TowersApp
////////////////////////////////////////////////////////////////
public class TowersApp
   {
    static int nDisks = 6;

    static int count;

   public static void main(final String[] args)
      {
      doTowers(nDisks, 'A', 'B', 'C');
        System.out.println("Count:" + count);
      }
   //-----------------------------------------------------------
   public static void doTowers(final int topN,
                               final char src, final char inter, final char dest)
      {
      if(topN==1)
    {
            count++;
        System.out.println("Disk 1 from " + src + " to "+ dest);
    }
    else
         {
         doTowers(topN-1, src, dest, inter);   // src to inter

            count++;
         System.out.println("Disk " + topN +   // move bottom
                            " from " + src + " to "+ dest);
         doTowers(topN-1, inter, src, dest);   // inter to dest
         }
      }
//-------------------------------------------------------------
   }  // end class TowersApp
////////////////////////////////////////////////////////////////
