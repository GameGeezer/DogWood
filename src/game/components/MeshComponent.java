package game.components;

import framework.graphics.Camera;
import framework.graphics.Mesh;
import framework.graphics.opengl.ShaderProgram;
import framework.scene.Entity;
import framework.scene.components.IEntityComponent;
import framework.scene.components.IUpdateEntityComponent;
import framework.util.dataTypes.DatatypeUtil;
import framework.util.exceptions.RequiredComponentsException;
import framework.util.math.Matrix4;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.List;

/**
 * Created by Will on 12/8/2014.
 */
public class MeshComponent extends IUpdateEntityComponent {

    private Mesh mesh;
    private ShaderProgram shader;

    private TransformComponent transformComponent;

    private FloatBuffer projectionViewModelBuffer = BufferUtils.createFloatBuffer(DatatypeUtil.FLOAT_SIZE_BYTES * Matrix4.NUMBER_OF_CELLS);
    private Matrix4 projectionViewModelMatrix = new Matrix4();


    public MeshComponent(Entity parent, Mesh mesh, ShaderProgram shader) throws RequiredComponentsException {
        super(parent);

        this.mesh = mesh;
        this.shader = shader;

        List<IEntityComponent> transformComponents = getComponentsOfType(TransformComponent.class);
        if(transformComponents == null)
            throw new RequiredComponentsException("This component requires a TransformComponent to be added to the entity before it");

        transformComponent = (TransformComponent)transformComponents.get(0);
    }

    @Override
    public void update(int delta) {
        shader.bind();
        int projectionViewModelHandle = shader.getUniformLocation("u_projectionViewModel");
        GL20.glUniformMatrix4(projectionViewModelHandle, false, createProjectionViewModelBuffer());

        mesh.draw();
        shader.unbind();
    }

    private FloatBuffer createProjectionViewModelBuffer() {

        projectionViewModelBuffer = BufferUtils.createFloatBuffer(Matrix4.NUMBER_OF_CELLS);
        projectionViewModelBuffer.put(transformComponent.createModel().data);
        projectionViewModelBuffer.flip();
        /*
        projectionViewModelMatrix.set(camera.getProjection());
        Matrix4.multiply(projectionViewModelMatrix, camera.getView(), projectionViewModelMatrix);
        Matrix4.multiply(projectionViewModelMatrix, transformComponent.createModel(), projectionViewModelMatrix);
        projectionViewModelBuffer.reset();
        projectionViewModelBuffer.put(camera.getProjection().data);
        */

        return projectionViewModelBuffer;
    }
}
