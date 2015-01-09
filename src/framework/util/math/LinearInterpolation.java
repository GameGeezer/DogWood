package framework.util.math;

/**
 * Created by Will on 1/8/2015.
 */
public class LinearInterpolation {

    public enum InterpolationBehavior {

        REPEAT_SAME_DIRECTION, REPEATE_REVERSE_DIRECTION,
    }

    private float start, end, step, time, incrementSize;

    public LinearInterpolation(float start, float end, float step, float time) {

        this.start = start;
        this.end = end;
        this.step = step;
        this.time = time;
        this.incrementSize = step / time;
    }

    /**
     * TODO
     * @param delta
     * @return
     */
    public float getNextStep(int delta) {
        float valueChange = incrementSize * delta;
        return 0;
    }
}
