package framework.util;

/**
 * @author William Gervasio
 */

public class Timer {

    public long startTime, stopTime, totalTime;
    public boolean running;

    public Timer() {

        startTime = 0;
        stopTime = 0;
        totalTime = 0;
        running = false;
    }

    /**
     * Start the timer
     */
    public void start() {

        if (!running) {

            running = true;

            startTime = System.currentTimeMillis();
        }
    }

    /**
     * Pause the timer
     */
    public void pause() {

        if (running) {

            running = false;
            stopTime = System.currentTimeMillis();
            totalTime += stopTime - startTime;
        }
    }

    /**
     * Resets the timer
     */
    public void reset() {

        final long time = System.currentTimeMillis();

        startTime = time;
        stopTime = time;
        totalTime = 0;
    }

    /**
     * @return The current elapsed time in milliseconds
     */
    public long getElapsedTimeMS() {

        if (running) {

            return System.currentTimeMillis() - startTime + totalTime;
        }

        return totalTime;
    }

    /**
     * @return The current elapsed time in seconds
     */
    public double getElapsedTimeSec() {

        return (long) Math.floor(getElapsedTimeMS() / 1000.0d);
    }

}