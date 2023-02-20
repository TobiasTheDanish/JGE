package Shapes.Primitives;

import Math.Vector.Vector2D;
import Math.Vector.Vector3D;
import Utils.Color.Color;

public class Triangle extends Shape{
    private Vector2D dimensions;
    public Triangle(Vector2D dimensions, Vector3D position, Vector3D rotation, float scale) {
        super(
            new float[]{
                    position.x - dimensions.x/2, position.y - dimensions.y/2, position.z, //0
                    position.x + dimensions.x/2, position.y - dimensions.y/2, position.z, //1
                    position.x, position.y + dimensions.y/2, position.z, //2
            },
            "./res/shaders/triangle.vert",
            "./res/shaders/triangle.frag"
        );

        this.dimensions = dimensions;
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;

        shader.setUniform("u_Color", Color.RGBA(0.8f, 0.3f, 0.2f, 1.0f));
    }
}
