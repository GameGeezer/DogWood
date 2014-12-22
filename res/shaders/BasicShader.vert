#version 150 core
uniform mat4 u_projectionMatrix;
uniform mat4 u_viewMatrix;
uniform mat4 u_modelMatrix;

uniform vec2 u_flipTextureCoordinates;

in vec4 in_Position;
in vec4 in_Color;
in vec2 in_TextureCoord;

out vec4 pass_Color;
out vec2 pass_TextureCoord;

void main(void) {
	gl_Position = u_projectionMatrix * u_viewMatrix* u_modelMatrix * in_Position;

	pass_Color = in_Color;
	if(u_flipTextureCoordinates.x == 0)
	    pass_TextureCoord = in_TextureCoord;
	else
	    pass_TextureCoord = in_TextureCoord.st * vec2(-1.0, 1);
}