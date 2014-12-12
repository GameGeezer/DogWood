package framework.graphics.opengl;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Will on 12/10/2014.
 */
public class Uniform {
    private List<PropertyChangeListener> listeners = new ArrayList<>();


    public void addListener(PropertyChangeListener listener) {
        listeners.add(listener);
    }

    private void notifyListeners() {

    }
}
