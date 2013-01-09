
package zhwb.bridge;

public class Circle extends Shape
{

    public Circle(Drawer drawing)
    {
        super(drawing);
    }

    @Override
    void draw()
    {
        draw.drawCicle();
    }

}
