package framework.graphics.uniform;

import java.nio.Buffer;

/**
 * Created by Will on 12/12/2014.
 */
public abstract class VectorUniform extends Uniform{

    public enum VectorUniformType {
        VECTOR1(1), VECTOR2(2), VECTOR3(3), VECTOR4(3);

        private int numberOfUniforms;

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

    public int getNumberOfUniforms() {
        return uniformType.getNumberOfUniforms();
    }
}
