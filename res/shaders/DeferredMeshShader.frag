#version 420 core

uniform sampler2D texture_diffuse;

in vec2 pass_TextureCoord;

layout(location = 0) out vec4 outToColorBuffer;

void main(void) {

	vec4 texel = texture(texture_diffuse, pass_TextureCoord);

	outToColorBuffer = texel;
}