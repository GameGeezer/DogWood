package game.components;

import framework.graphics.uniform.MatrixUniform;
import framework.util.math.Matrix4;

/**
 * Created by Will on 12/28/2014.
 */
public class TextureMatrixUniformComponent {

    private Matrix4 matrix = new Matrix4();
    private MatrixUniform textureMatrixUniform = new MatrixUniform("u_textureMatrix", MatrixUniform.MatrixUniformType.MATRIX4);
}
