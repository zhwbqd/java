package framework.disruptor;

import com.lmax.disruptor.EventHandler;

/**
 */public class LongEventHandler implements EventHandler<LongEvent> {
    @Override 
    public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception { 
        System.out.println(longEvent.getValue()); 
    } 
} 