#version 330 core

uniform sampler2D texture_diffuse;

out vec4 out_Color;

void main(void) {

	vec4 texel = texture(texture_diffuse, pass_TextureCoord);

	out_Color = texel;
}