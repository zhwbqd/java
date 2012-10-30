package zhwb.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompareSameList
{

    public static void main(String[] args)
    {
        List<String> list1 = new ArrayList<String>();
        List<String> list2 = new ArrayList<String>();
        for (int i = 0; i < 10; i++)
        {
            list1.add("test" + i);
            list2.add("test" + i * 2);
        }
        System.out.println(getSame(list1, list2));
        System.out.println(getSame2(list1, list2));
        System.out.println(getDiffrent3(list1, list2));
        System.out.println(getDiffrent4(list1, list2));

        //        getDiffrent total times 2789492240
        //        getDiffrent2 total times 3324502695
        //        getDiffrent3 total times 24710682
        //        getDiffrent4 total times 15627685
    }

    /**
     * 获取两个List的不同元素
     * @param list1
     * @param list2
     * @return
     */
    private static List<String> getDiffrent4(List<String> list1, List<String> list2)
    {
        long st = System.nanoTime();
        Map<String, Integer> map = new HashMap<String, Integer>(list1.size() + list2.size());
        List<String> same = new ArrayList<String>();
        List<String> maxList = list1;
        List<String> minList = list2;
        if (list2.size() > list1.size())
        {
            maxList = list2;
            minList = list1;
        }
        for (String string : maxList)
        {
            map.put(string, 1);
        }
        for (String string : minList)
        {
            Integer cc = map.get(string);
            if (cc != null)
            {
                map.put(string, ++cc);
                continue;
            }
            map.put(string, 1);
        }
        for (Map.Entry<String, Integer> entry : map.entrySet())
        {
            if (entry.getValue() != 1)
            {
                same.add(entry.getKey());
            }
        }
        System.out.println("getSame4 total times " + (System.nanoTime() - st));
        return same;

    }

    /**
     * 获取两个List的不同元素
     * @param list1
     * @param list2
     * @return
     */
    private static List<String> getDiffrent3(List<String> list1, List<String> list2)
    {
        long st = System.nanoTime();
        Map<String, Integer> map = new HashMap<String, Integer>(list1.size() + list2.size());
        List<String> same = new ArrayList<String>();
        for (String string : list1)
        {
            map.put(string, 1);
        }
        for (String string : list2)
        {
            Integer cc = map.get(string);
            if (cc != null)
            {
                map.put(string, ++cc);
                continue;
            }
            map.put(string, 1);
        }
        for (Map.Entry<String, Integer> entry : map.entrySet())
        {
            if (entry.getValue() != 1)
            {
                same.add(entry.getKey());
            }
        }
        System.out.println("getSame3 total times " + (System.nanoTime() - st));
        return same;
    }

    /**
     * 获取连个List的不同元素  retainAll 会将本身元素remove掉
     * @param list1
     * @param list2
     * @return
     */
    private static List<String> getSame2(List<String> list1, List<String> list2)
    {
        long st = System.nanoTime();
        List<String> temp = new ArrayList(Arrays.asList(new Object[list1.size()]));
        Collections.copy(temp, list1);
        temp.retainAll(list2);
        System.out.println("getSame2 total times " + (System.nanoTime() - st));
        return temp;
    }

    /**
     * 获取两个List的不同元素
     * @param list1
     * @param list2
     * @return
     */
    private static List<String> getSame(List<String> list1, List<String> list2)
    {
        long st = System.nanoTime();
        List<String> same = new ArrayList<String>();
        for (String str : list1)
        {
            if (list2.contains(str))
            {
                same.add(str);
            }
        }
        System.out.println("getSame total times " + (System.nanoTime() - st));
        return same;
    }
}