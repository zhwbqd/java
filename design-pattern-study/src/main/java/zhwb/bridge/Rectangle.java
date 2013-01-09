
package zhwb.bridge;

public class Rectangle extends Shape{

    public Rectangle(Drawer drawing)
    {
        super(drawing);
    }

    @Override
    void draw()
    {
        draw.drawLine();
    }
}
