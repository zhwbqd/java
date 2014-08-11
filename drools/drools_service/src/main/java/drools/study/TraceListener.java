package drools.study;

import org.drools.event.rule.*;

/**
 * @author sam.yang
 * @since 7/28/14 4:53 PM.
 */
public class TraceListener extends DefaultAgendaEventListener {

    public void activationCancelled(ActivationCancelledEvent event) {
        System.out.println("cancel:" + event.getActivation().getRule().getName());
    }

    public void activationCreated(ActivationCreatedEvent event) {
        System.out.println("create:" + event.getActivation().getRule().getName());
    }

    public void afterActivationFired(AfterActivationFiredEvent event) {
        System.out.println("after:" + event.getActivation().getRule().getName());
    }

    public void beforeActivationFired(BeforeActivationFiredEvent event) {
        System.out.println("before:" + event.getActivation().getRule().getName());
    }
    public void beforeRuleFlowGroupActivated(RuleFlowGroupActivatedEvent event) {
    }
}
