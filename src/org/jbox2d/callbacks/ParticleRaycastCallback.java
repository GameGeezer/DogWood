package org.jbox2d.callbacks;

import org.jbox2d.dynamics.contacts.common.Vec2;

public interface ParticleRaycastCallback {
  /**
   * Called for each particle found in the query. See
   * {@link org.jbox2d.callbacks.RayCastCallback#reportFixture(org.jbox2d.dynamics.Fixture, org.jbox2d.dynamics.contacts.common.Vec2, org.jbox2d.dynamics.contacts.common.Vec2, float)} for
   * argument info.
   * 
   * @param index
   * @param point
   * @param normal
   * @param fraction
   * @return
   */
  float reportParticle(int index, Vec2 point, Vec2 normal, float fraction);

}
