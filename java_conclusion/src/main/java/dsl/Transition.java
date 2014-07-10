package dsl;

/**
 * Date: 14-7-4
 * Time: 下午9:48
 *
 * @author jack.zhang
 */
public class Transition {
    private final State source;
    private final Event trigger;
    private final State target;

    public Transition(State source, Event trigger, State target) {
        this.source = source;
        this.trigger = trigger;
        this.target = target;
    }

    public String getEventCode() {
        return trigger.getCode();
    }

    public State getSource() {
        return source;
    }

    public Event getTrigger() {
        return trigger;
    }

    public State getTarget() {
        return target;
    }
}
