/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

package zhwb.bridge;

public class Rectangle extends Shape{

    public Rectangle(Drawing drawing)
    {
        super(drawing);
    }

    @Override
    void draw()
    {
        draw.drawLine();
    }
}
