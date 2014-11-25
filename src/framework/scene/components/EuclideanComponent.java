package framework.scene.components;

import framework.scene.Entity;
import framework.util.Node;
import framework.util.math.Matrix4;
import framework.util.math.Vector3;

/**
 * Created by Will on 11/25/2014.
 */
public class EuclideanComponent implements DynamicEntityComponent {
    private Matrix4 modelMatrix, translationMatrix, rotationMatrix;;
    private Entity partner;

    public EuclideanComponent(Entity partner) {
        this.partner = partner;
    }

    public void update(int delta) {

    }

    public void translate(float x, float y, float z) {
        translationMatrix.data[Matrix4.M30] += x;
        translationMatrix.data[Matrix4.M31] += y;
        translationMatrix.data[Matrix4.M32] += z;
    }

    public void translate(Vector3 translation) {
        translationMatrix.data[Matrix4.M30] += translation.getX();
        translationMatrix.data[Matrix4.M31] += translation.getY();
        translationMatrix.data[Matrix4.M32] += translation.getZ();
    }

    public void setX(float value) {
        translationMatrix.data[Matrix4.M30] = value;
    }

    public float getX() {
        return translationMatrix.data[Matrix4.M30];
    }

    public void setY(float value) {
        translationMatrix.data[Matrix4.M31] = value;
    }

    public float getY() {
        return translationMatrix.data[Matrix4.M31];
    }

    public void setZ(float value) {
        translationMatrix.data[Matrix4.M32] = value;
    }

    public float getZ() {
        return translationMatrix.data[Matrix4.M32];
    }

    /**
     * Recursively walks to the top of the tree and then back down
     */
    public void createModelsRecursive() {
        if(this.partner.getParent() == null) {
            modelMatrix.setIdentity();
            modelMatrix.multiply(translationMatrix);
            modelMatrix.multiply(rotationMatrix);

            for(Node<Entity> node : partner.getSubnodes()) {
                Entity entity = (Entity) node;
                ((EuclideanComponent)entity.getComponentsOfType(this.getClass())).createModelHelper(modelMatrix);
            }
        }

        Entity parent = (Entity)this.partner.getParent();
        ((EuclideanComponent)parent.getComponentsOfType(this.getClass())).createModelsRecursive();

    }

    private void createModelHelper(Matrix4 parent) {
        modelMatrix.setIdentity();
        modelMatrix.multiply(translationMatrix);
        modelMatrix.multiply(rotationMatrix);
        modelMatrix = parent.clone().multiply(parent);

        for(Node<Entity> node : partner.getSubnodes()) {
            Entity entity = (Entity) node;
            ((EuclideanComponent)entity.getComponentsOfType(this.getClass())).createModelHelper(modelMatrix);
        }
    }
}
