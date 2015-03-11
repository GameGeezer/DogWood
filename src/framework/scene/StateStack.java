package framework.scene;

import framework.util.Timer;

import java.util.LinkedList;

/**
 * @author William Gervasio
 */
public class StateStack<E extends StateStack.State> {

    private final LinkedList<E> states = new LinkedList<>();

    public E pop() {

        E poppedState = states.pop();
        poppedState.setParentStack(null);
        poppedState.onLeaveTop();
        states.peek().onBecomeTop();

        return poppedState;
    }

    public E peek() {

        return states.peek();
    }

    public void push(final E state) {

        if (states.peek() != null)
            states.peek().onLeaveTop();

        state.setParentStack(this);
        states.push(state);
        states.peek().onBecomeTop();
    }

    public void clear() {

        states.clear();
    }

    public abstract static class State {

        private StateStack parentStack;
        private Timer timeSinceTopTimer = new Timer();

        public State() {

            timeSinceTopTimer.start();
            timeSinceTopTimer.reset();
        }

        protected void popStack() {

            if(parentStack.peek().equals(this)) {

                parentStack.pop();
                timeSinceTopTimer.start();
                timeSinceTopTimer.reset();
            }

        }

        protected abstract void onLeaveTop();

        protected abstract void onBecomeTop();

        protected void setParentStack(final StateStack parentStack) {

            this.parentStack = parentStack;
        }

        public long getTimeSinceTop() {

            return timeSinceTopTimer.getElapsedTimeMS();
        }
    }
}
