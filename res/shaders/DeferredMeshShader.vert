#version 150 core
uniform mat4 u_projectionMatrix;
uniform mat4 u_viewMatrix;
uniform mat4 u_modelMatrix;

in vec4 in_Position;
in vec2 in_TextureCoord;

out vec4 pass_Position;
out vec4 pass_Normal;
out vec2 pass_TextureCoord;


void main(void) {

    mat4 mvp = u_projectionMatrix * u_viewMatrix * u_modelMatrix;

    pass_Position = mvp * in_Position;
    //pass_Normal = mat3(mvp) * in_Normal;

	pass_TextureCoord = in_TextureCoord;

	gl_Position = mvp * in_Position;
}