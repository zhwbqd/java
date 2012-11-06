/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of Hewlett-Packard Co.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2009   All rights reserved. ======================
 */

package zhwb.observerPattern;

import java.util.Observable;
import java.util.Observer;

public class HappyObserver implements Observer
{

    @Override
    public void update(Observable o, Object arg)
    {
        System.out.printf("Observer count is %d \n", o.countObservers());
        System.out.printf("Observerable say that %s \n", arg);
    }

}
