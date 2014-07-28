package drools.study;

import org.drools.event.rule.*;

/**
 * Date: 2014/7/28
 * Time: 16:52
 *
 * @author jack.zhang
 */
public class FuckListener extends org.drools.event.rule.DefaultAgendaEventListener {

    public void activationCancelled(ActivationCancelledEvent event) {
        System.out.println("activationCancelled");
    }

    public void activationCreated(ActivationCreatedEvent event) {
        System.out.println("activationCreated");
    }

    public void afterActivationFired(AfterActivationFiredEvent event) {
        System.out.println("afterActivationFired");
    }

    public void agendaGroupPopped(AgendaGroupPoppedEvent event) {
        System.out.println("agendaGroupPopped");
    }

    public void agendaGroupPushed(AgendaGroupPushedEvent event) {
        System.out.println("agendaGroupPushed");
    }

    public void beforeActivationFired(BeforeActivationFiredEvent event) {
        System.out.println("beforeActivationFired");
    }

    public void beforeRuleFlowGroupActivated(RuleFlowGroupActivatedEvent event) {
        System.out.println("beforeRuleFlowGroupActivated");
    }

    public void afterRuleFlowGroupActivated(RuleFlowGroupActivatedEvent event) {
        System.out.println("afterRuleFlowGroupActivated");
    }

    public void beforeRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent event) {
        System.out.println("beforeRuleFlowGroupDeactivated");
    }

    public void afterRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent event) {
        System.out.println("afterRuleFlowGroupDeactivated");
    }
   
}
