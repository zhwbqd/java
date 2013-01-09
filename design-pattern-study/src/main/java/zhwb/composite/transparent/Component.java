/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

package zhwb.composite.transparent;

import java.util.Enumeration;

public interface Component
{
    void sampleOperation();

    Composite getComposite();

    void add(Component component);

    void remove(Component component);

    Enumeration<Component> conponents();
}
