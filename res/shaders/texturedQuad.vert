#version 330 core
layout(location = 0) in vec4 position;
layout(location = 1) in vec2 tCoord;

uniform mat4 pr_matrix;
uniform mat4 vw_matrix;
uniform mat4 transformation_matrix;

out DATA {
    vec2 tc;
} vs_out;

void main() {
    vec4 worldPosition = transformation_matrix * position;
    gl_Position = pr_matrix * vw_matrix * worldPosition;
    vs_out.tc = tCoord;
}