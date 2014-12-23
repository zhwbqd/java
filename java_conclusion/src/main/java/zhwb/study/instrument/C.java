package zhwb.study.instrument;

public class C {
    private int x;
    private int y;

    public static void main(String [] args) {
        System.out.println(ObjectSizeFetcher.getObjectSize(new C()));
        System.out.println(ObjectSizeFetcher.getObjectSize(1));
        System.out.println(ObjectSizeFetcher.getObjectSize(Integer.valueOf(1)));
        System.out.println(ObjectSizeFetcher.getObjectSize(Integer.MAX_VALUE));

    }
}