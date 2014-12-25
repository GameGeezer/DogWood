#version 150 core
uniform mat4 u_projectionMatrix;
uniform mat4 u_viewMatrix;
uniform mat4 u_modelMatrix;

uniform vec2 u_flipTextureCoordinates;
uniform mat4 u_textureMatrix;

in vec4 in_Position;
in vec2 in_TextureCoord;

out vec2 pass_TextureCoord;

void main(void) {

	pass_TextureCoord = (u_textureMatrix * vec4(in_TextureCoord, 0.0f, 1.0f)).xy * u_flipTextureCoordinates;

	gl_Position = u_projectionMatrix * u_viewMatrix* u_modelMatrix * in_Position;
}