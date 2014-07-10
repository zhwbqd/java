package dsl;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 14-7-4
 * Time: 下午9:53
 *
 * @author jack.zhang
 */
public class StateMachine {
    private State start;
    private List<Event> resetEvents = new ArrayList<Event>();

    public void addResetEvents(Event... events) {
        for (Event event : events) {
            resetEvents.add(event);
        }
    }

    public StateMachine(State start) {
        this.start = start;
    }

    public boolean isResetEvent(String eventCode) {
        return resetEventCodes().contains(eventCode);
    }

    public List<String> resetEventCodes() {
        List<String> results = new ArrayList<String>();
        for (Event event : resetEvents) {
            results.add(event.getCode());
        }
        return results;
    }

    public List<State> getStates() {
        List<State> result = new ArrayList<State>();
        collectStates(result, start);
        return result;
    }

    private void collectStates(List<State> result, State start) {
        if (result.contains(start)) {
            return;
        }
        result.add(start);
        for (State state : start.getAllTargets()) {
            collectStates(result, state);
        }
    }

    private void addResetEvent(Event event) {
        for (State state : getStates()) {
            if (!state.hasTransition(event.getCode())) {
                state.addTransition(event, start);
            }
        }
    }
}
