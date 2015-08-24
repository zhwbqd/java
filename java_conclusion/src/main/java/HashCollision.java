import java.util.ArrayList;
import java.util.List;

public class HashCollision {
    public void sayHello() { 
        System.out.println("hello world! (version two)");
    }

    public static void main(String[] args) {
        List<String> seeds = new ArrayList<>();
        seeds.add("Aa");
        seeds.add("BB");

        List<String> result = new ArrayList<>();
        for (String seed : seeds) {
            for (String seed1 : seeds) {
                for (String seed2 : seeds) {
                    for (String seed3 : seeds) {
                        result.add(seed+seed1+seed2+seed3);
                    }
                }
            }
        }
        System.out.println(result);

        for (String s : result) {
            System.out.println(s.hashCode());
        }
    }
}