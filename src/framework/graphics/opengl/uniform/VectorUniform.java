package framework.graphics.opengl.uniform;

/**
 * @author William Gervasio
 */
@SuppressWarnings("WeakerAccess")
public abstract class VectorUniform extends Uniform {

    public enum VectorUniformType {
        VECTOR1(1), VECTOR2(2), VECTOR3(3), VECTOR4(3);

        private final int numberOfUniforms;

        VectorUniformType(int numberOfUniforms) {

            this.numberOfUniforms = numberOfUniforms;
        }

        public int getNumberOfUniforms() {
            return numberOfUniforms;
        }
    }

    protected final VectorUniformType uniformType;

    public VectorUniform(String name, VectorUniformType uniformType) {

        super(name);

        this.uniformType = uniformType;
    }
}
