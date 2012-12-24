/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

package zhwb.bridge;

public class Circle extends Shape
{

    public Circle(Drawing drawing)
    {
        super(drawing);
    }

    @Override
    void draw()
    {
        draw.drawCicle();
    }

}
