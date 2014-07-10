package dsl;

import java.util.*;

/**
 * Date: 14-7-4
 * Time: 下午9:46
 *
 * @author jack.zhang
 */
public class State {
    private String name;
    private List<Command> actions = new ArrayList<Command>();
    private Map<String, Transition> transitionMap = new HashMap<String, Transition>();

    public void addTransition(Event event,State targetState) {
        assert null != targetState;
        transitionMap.put(event.getCode(), new Transition(this, event, targetState));
    }

    public Collection<State> getAllTargets() {
        List<State> result = new ArrayList<State>();
        for (Transition transition : transitionMap.values()) {
            result.add(transition.getTarget());
        }
        return result;
    }

    public boolean hasTransition(String code) {
        return transitionMap.containsKey(code);
    }

    public State targetState(String eventCode) {
        return transitionMap.get(eventCode).getTarget();
    }
}
