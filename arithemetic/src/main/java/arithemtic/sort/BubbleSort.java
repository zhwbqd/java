/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2012 All rights reserved. =============================
 */

package arithemtic.sort;



public class BubbleSort
{
    public static void main(final String[] args)
    {
        int maxSize = 100; // array size
        ArrayBub arr; // reference to array
        arr = new ArrayBub(maxSize); // create the array

        arr.insert(99); // insert 10 items
        arr.insert(88);
        arr.insert(77);
        arr.insert(66);
        arr.insert(55);
        arr.insert(44);
        arr.insert(33);
        arr.insert(22);
        arr.insert(11);
        arr.insert(0);

        arr.display(); // display items

        arr.bubbleSort(); // bubble sort them

        arr.display(); // display them again
    } // end main()

    private static class ArrayBub
    {
        private long[] a; // ref to array a

        private int nElems; // number of data items

        //--------------------------------------------------------------

        public ArrayBub(final int max) // constructor
        {
            a = new long[max]; // create the array
            nElems = 0; // no items yet
        }

        //--------------------------------------------------------------
        public void insert(final long value) // put element into array
        {
            a[nElems] = value; // insert it
            nElems++; // increment size
        }

        //--------------------------------------------------------------
        public void display() // displays array contents
        {
            for (int j = 0; j < nElems; j++)
            {
                System.out.print(a[j] + " "); // display it
            }
            System.out.println("");
        }

        //--------------------------------------------------------------
        public void bubbleSort()
        {
            int out, in;
            int exchangeCount = 0;

            for (out = nElems - 1; out > 0; out--)
            {
                for (in = 0; in < out; in++)
                {
                    if (a[in] > a[in + 1])
                    {
                        exchangeCount++;
                        swap(in, in + 1); // swap them
                    }
                }
            }
            System.out.println("Exchange Count: " + exchangeCount);
        } // end bubbleSort()
          //--------------------------------------------------------------

        private void swap(final int one, final int two)
        {
            long temp = a[one];
            a[one] = a[two];
            a[two] = temp;
        }
        //--------------------------------------------------------------
    } // end class ArrayBub
      ////////////////////////////////////////////////////////////////
} // end class BubbleSortApp
////////////////////////////////////////////////////////////////
