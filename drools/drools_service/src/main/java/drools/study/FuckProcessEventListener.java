package drools.study;

import org.drools.event.process.*;
import org.drools.event.rule.AgendaEventListener;
import org.drools.event.rule.WorkingMemoryEventListener;

/**
 * Date: 2014/7/28
 * Time: 17:58
 *
 * @author jack.zhang
 */
public class FuckProcessEventListener extends DefaultProcessEventListener {
    public void afterNodeLeft(ProcessNodeLeftEvent event) {
        System.out.println("---");
    }

    public void afterNodeTriggered(ProcessNodeTriggeredEvent event) {
        System.out.println("---");
    }

    public void afterProcessCompleted(ProcessCompletedEvent event) {
        System.out.println("---");
    }

    public void afterProcessStarted(ProcessStartedEvent event) {
        System.out.println("---");
    }

    public void afterVariableChanged(ProcessVariableChangedEvent event) {
        System.out.println("---");
    }

    public void beforeNodeLeft(ProcessNodeLeftEvent event) {
        System.out.println("---");
    }

    public void beforeNodeTriggered(ProcessNodeTriggeredEvent event) {
        System.out.println("---");
    }

    public void beforeProcessCompleted(ProcessCompletedEvent event) {
        System.out.println("---");
    }

    public void beforeProcessStarted(ProcessStartedEvent event) {
        System.out.println("---");
    }

    public void beforeVariableChanged(ProcessVariableChangedEvent event) {
        System.out.println("---");
    }
}
