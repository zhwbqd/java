package drools.study;

import org.drools.event.rule.*;

/**
 * Date: 2014/7/28
 * Time: 16:52
 *
 * @author jack.zhang
 */
public class FuckListener extends org.drools.event.rule.DefaultAgendaEventListener {

    public void activationCreated(ActivationCreatedEvent event) {
        System.out.println("fact match"+event.getActivation().getRule().getName());
    }

    public void afterActivationFired(AfterActivationFiredEvent event) {
        System.out.println("after rule fire");
    }

    public void beforeActivationFired(BeforeActivationFiredEvent event) {
        System.out.println("before rule fire");
    }
}
