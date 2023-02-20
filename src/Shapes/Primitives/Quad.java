package Shapes.Primitives;

import Math.Vector.Vector2D;
import Math.Vector.Vector3D;
import Rendering.Transform;
import Utils.Color.Color;

public class Quad extends Shape{
    private final Vector2D dimensions;
    public Quad(Vector2D dimensions, Vector3D position, Vector3D rotation, float scale) {
        super(
            new byte[] {
                    0, 1, 2,
                    2, 3, 0
            },
            new float[] {
                    position.x - dimensions.x/2, position.y - dimensions.y/2, position.z, //0
                    position.x + dimensions.x/2, position.y - dimensions.y/2, position.z, //1
                    position.x + dimensions.x/2, position.y + dimensions.y/2, position.z, //2
                    position.x - dimensions.x/2, position.y + dimensions.y/2, position.z, //3
            },
            "./res/shaders/Quad.vert",
            "./res/shaders/Quad.frag"
        );

        this.dimensions = dimensions;
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;

        shader.setUniform("u_Color", Color.RGBA(0.2f, 0.3f, 0.8f, 1.0f));
    }

    public Quad(Vector2D dimensions, Vector3D position, Vector3D rotation, float scale, String texturePath) {
        super(
            new byte[] {
                    0, 1, 2,
                    2, 3, 0
            },
            new float[] {
                    position.x - dimensions.x/2, position.y - dimensions.y/2, position.z, //0
                    position.x + dimensions.x/2, position.y - dimensions.y/2, position.z, //1
                    position.x + dimensions.x/2, position.y + dimensions.y/2, position.z, //2
                    position.x - dimensions.x/2, position.y + dimensions.y/2, position.z, //3
            },
            new float[] {
                    0.0f, 1.0f, //3
                    1.0f, 1.0f, //2
                    1.0f, 0.0f, //1
                    0.0f, 0.0f, //0
            },
            "./res/shaders/texturedQuad.vert",
            "./res/shaders/texturedQuad.frag",
            texturePath
        );

        this.dimensions = dimensions;
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;

        shader.setUniform("u_Texture", 1);
    }

    public Quad(Transform transform, String texturePath) {
        super(
                new byte[] {
                        0, 1, 2,
                        2, 3, 0
                },
                new float[] {
                        transform.position.x - transform.dimensions.x/2, transform.position.y - transform.dimensions.y/2, transform.position.z, //0
                        transform.position.x + transform.dimensions.x/2, transform.position.y - transform.dimensions.y/2, transform.position.z, //1
                        transform.position.x + transform.dimensions.x/2, transform.position.y + transform.dimensions.y/2, transform.position.z, //2
                        transform.position.x - transform.dimensions.x/2, transform.position.y + transform.dimensions.y/2, transform.position.z, //3
                },
                new float[] {
                        0.0f, 1.0f, //3
                        1.0f, 1.0f, //2
                        1.0f, 0.0f, //1
                        0.0f, 0.0f, //0
                },
                "./res/shaders/texturedQuad.vert",
                "./res/shaders/texturedQuad.frag",
                texturePath
        );

        this.dimensions = transform.dimensions;
        this.position = transform.position;
        this.rotation = transform.rotation;
        this.scale = transform.scale;

        shader.setUniform("u_Texture", 1);
    }

    public float getWidth() {
        return this.dimensions.x;
    }

    public float getHeight() {
        return this.dimensions.y;
    }

    @Override
    public String toString() {
        return "Quad: " + "\nDimensions: " + dimensions + "\nPosition: " + position + "\nRotation: " + rotation;
    }
}
