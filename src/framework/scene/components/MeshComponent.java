package framework.scene.components;

import framework.graphics.Mesh;
import framework.graphics.opengl.ShaderProgram;

/**
 * Created by Will on 12/8/2014.
 */
public class MeshComponent  implements IDynamicEntityComponent {
    private Mesh mesh;
    private ShaderProgram shader;
    public void update(int delta) {
        shader.bind();
        mesh.draw();
        shader.unbind();
    }
}
