package framework.util;

/**
 * @author William Gervasio
 */

import org.lwjgl.Sys;

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
            startTime = getCurrentTimeMS();
        }
    }

    /**
     * Pause the timer
     */
    public void pause() {
        if (running) {
            running = false;
            stopTime = getCurrentTimeMS();
            totalTime += stopTime - startTime;
        }
    }

    /**
     * Resets the timer
     */
    public void reset() {
        long time = getCurrentTimeMS();
        startTime = time;
        stopTime = time;
        totalTime = 0;
    }

    /**
     *
     * @return The current elapsed time in milliseconds
     */
    public long getElapsedTimeMS() {
        if (running) {
            return getCurrentTimeMS() - startTime + totalTime;
        }

        return totalTime;
    }

    /**
     *
     * @return The current elapsed time in seconds
     */
    public long getElapsedTimeSec() {
        return (long) Math.floor(getElapsedTimeMS() / 1000);
    }

    /**
     * Get the current system time in milliseconds
     * @return
     */
    public static long getCurrentTimeMS() {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }
}