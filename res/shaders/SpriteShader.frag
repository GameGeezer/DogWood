#version 150 core

uniform sampler2D texture_diffuse;

in vec2 pass_TextureCoord;

out vec4 out_Color;

void main(void) {

	vec4 texel = texture(texture_diffuse, pass_TextureCoord);

	out_Color = texel;
}