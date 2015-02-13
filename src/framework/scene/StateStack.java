package framework.scene;

import java.util.LinkedList;

/**
 * Created by Will on 2/2/2015.
 */
public class StateStack<E extends StateStack.State> {

    private LinkedList<E> states = new LinkedList<>();

    public E  pop() {

        E poppedState = states.pop();
        poppedState.onLeaveTop();
        states.peek().onBecomeTop();

        return poppedState;
    }

    public E peek() {

        return states.peek();
    }

    public void push(E state) {

        if(states.peek()!= null)
            states.peek().onLeaveTop();

        states.push(state);
        states.peek().onBecomeTop();
    }

    public void clear() {

        states.clear();
    }

    public abstract static class State {

        protected abstract void onLeaveTop();

        protected abstract void onBecomeTop();
    }
}