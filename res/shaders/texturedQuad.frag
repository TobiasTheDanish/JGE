#version 330 core
layout(location = 0) out vec4 color;

in DATA {
    vec2 tc;
} vs_in;

uniform sampler2D u_Texture;

void main() {
    color = texture(u_Texture, vs_in.tc);
}