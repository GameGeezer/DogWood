#version 150 core
uniform mat4 u_projectionMatrix;
uniform mat4 u_viewMatrix;
uniform mat4 u_modelMatrix;

in vec4 in_Position;
in vec2 in_TextureCoord;

out vec4 pass_Color;
out vec2 pass_TextureCoord;


void main(void) {

    gl_Position = u_projectionMatrix * u_viewMatrix* u_modelMatrix * in_Position;

	pass_TextureCoord = in_TextureCoord;
}