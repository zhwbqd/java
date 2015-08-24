package util;

import java.util.*;

/**
 * @author jack.zhang
 * @since 2015/8/18
 */
public class MapSort {

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValues(Map<K, V> map, final boolean asc) {
        List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            @Override
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                return asc ? o1.getValue()
                        .compareTo(o2.getValue()) : o2.getValue()
                        .compareTo(o1.getValue());
            }
        });

        Map<K, V> sortedHashMap = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap;
    }

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();

    }

}
