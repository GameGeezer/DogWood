#version 330 core

uniform sampler2D texture_diffuse;

in vec4 pass_Position;
in vec4 pass_Normal;
in vec2 pass_TextureCoord;


void main(void) {

	vec4 texel = texture(texture_diffuse, pass_TextureCoord);

	gl_FragData[0] = pass_Position;
	gl_FragData[1] = pass_Normal;
	gl_FragData[2] = texel;
}