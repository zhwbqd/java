
package zhwb.bridge;

public class Test
{

    /**
     * Description goes here.
     *
     * @param args
     */
    public static void main(String[] args)
    {
        Drawer d1 = new DrawMac();
        Shape shape1 = new Circle(d1);
        shape1.draw();

        Drawer d2 = new DrawWindows();
        Shape shape2 = new Circle(d2);
        shape2.draw();

    }

}
