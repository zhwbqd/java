/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

package zhwb.observerPattern;

import java.util.Observable;
import java.util.Random;

public class EqualsNotification extends Observable
{
    private Random rnd = new Random();

    void testRandom()
    {
        for (int i = 1; i < 100; i++)
        {
            if (rnd.nextInt(i) == rnd.nextInt(i * 2))
            {
                super.setChanged();
                notifyObservers("something random happened: " + i);
            }
        }
    }
}
