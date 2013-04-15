package zhwb.stufy.javabase;

import java.util.NavigableMap;
import java.util.TreeMap;

/**
 *
 * Java program to demonstrate What is NavigableMap in Java and How to use NavigableMap
 * in Java. NavigableMap provides two important features navigation methods
 * like lowerKey(), floorKey, ceilingKey() and higherKey().
 * There Entry counterpart and methods to create subMap e.g. headMap(), tailMap()
 * and subMap().
 *
 * @author Javin Paul
 */
public class NavigableMapExample {

    public static void main(final String args[]) {
        //NavigableMap extends SortedMap to provide useful navigation methods
        NavigableMap<String, String> navigableMap = new TreeMap<String, String>();
      
        navigableMap.put("C++", "Good programming language");
        navigableMap.put("Java", "Another good programming language");
        navigableMap.put("Scala", "Another JVM language");
        navigableMap.put("Python", "Language which Google use");
      
        System.out.println("SorteMap : " + navigableMap);
      
        System.out.println("lowerKey from Java : " + navigableMap.lowerKey("Java"));
      
        System.out.println("floorKey from Java: " + navigableMap.floorKey("Java"));
      
        System.out.println("ceilingKey from Java: " + navigableMap.ceilingKey("Java"));
      
        System.out.println("higherKey from Java: " + navigableMap.higherKey("Java"));
      
      
        //Apart from navigagtion methodk, it also provides useful method
        //to create subMap from existing Map e.g. tailMap, headMap and subMap
      
        //an example of headMap - returns NavigableMap whose key is less than specified
        NavigableMap<String, String> headMap = navigableMap.headMap("Python", false);
        System.out.println("headMap created form navigableMap : " + headMap);
              
        //an example of tailMap - returns NavigableMap whose key is greater than specified
        NavigableMap<String, String> tailMap = navigableMap.tailMap("Scala", true);
        System.out.println("tailMap created form navigableMap : " + tailMap);
      
        //an example of subMap - return NavigableMap from toKey to fromKey
        NavigableMap<String, String> subMap = navigableMap.subMap("C++", false, "Python", false);
        System.out.println("subMap created form navigableMap : " + subMap);
    }
}


