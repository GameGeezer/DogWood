#version 330 core

uniform mat4 u_projectionMatrix;
uniform mat4 u_viewMatrix;
uniform mat4 u_modelMatrix;

in vec4 in_Position;
in vec3 in_Normal;
in vec4 in_Color;

out vec4 pass_Position;
out vec4 pass_Normal;
out vec4 pass_Color;


void main(void) {

    mat4 mvp = u_projectionMatrix * u_viewMatrix * u_modelMatrix;

    pass_Position = mvp * in_Position;
    pass_Normal = vec4(normalize(mat3(mvp) * in_Normal), 1);

	pass_Color = in_Color;

	gl_Position = mvp * in_Position;
}