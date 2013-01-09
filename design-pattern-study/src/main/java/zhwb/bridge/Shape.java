
package zhwb.bridge;

public abstract class Shape
{
    abstract void draw();

    protected Drawer draw;

    public Shape(Drawer drawing)
    {
        draw = drawing;
    }

}
