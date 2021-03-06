package org.jbox2d.particle;

import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.contacts.common.Vec2;

public class ParticleBodyContact {
  /** Index of the particle making contact. */
  public int index;
  /** The body making contact. */
  public Body body;
  /** Weight of the contact. A value between 0.0f and 1.0f. */
  float weight;
  /** The normalized direction from the particle to the body. */
  public final Vec2 normal = new Vec2();
  /** The effective mass used in calculating force. */
  float mass;
}
