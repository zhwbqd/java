/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

package zhwb.bridge;

public abstract class Shape
{
    abstract void draw();

    protected Drawing draw;

    public Shape(Drawing drawing)
    {
        draw = drawing;
    }

}
