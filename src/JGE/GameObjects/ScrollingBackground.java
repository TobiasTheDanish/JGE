package JGE.GameObjects;

import Math.Matrix.Matrix4;
import Math.Vector.Vector2D;
import Math.Vector.Vector3D;
import JGE.GameComponents.*;
import Shapes.Primitives.Quad;

public class ScrollingBackground extends GameObject {
    private final Quad quad;
    private final Transform[] quadTransforms = new Transform[4];

    public ScrollingBackground(Transform transform, String texturePath) {
        this.transform = transform;

        this.quadTransforms[0] = new Transform(new Vector3D(0.0f, transform.position.y, transform.position.z), transform.rotation, new Vector2D(transform.dimensions.x/2, transform.dimensions.y),transform.scale);

        transform = this.quadTransforms[0];

        for (int i = 0; i < quadTransforms.length; i++) {
            this.quadTransforms[i] = new Transform(new Vector3D(transform.position.x + transform.dimensions.x * i, transform.position.y, transform.position.z), transform.rotation, transform.dimensions, transform.scale);
        }

        quad = new Quad(this.quadTransforms[0], texturePath);
    }

    public void update() {
        for (int i = 0; i < quadTransforms.length; i++) {
            Transform t = quadTransforms[i];
            t.position.add(Vector3D.left.multiply(2.0f));

            if (t.position.x < 0 - t.dimensions.x/2) {
                t.position.x = this.transform.dimensions.x + t.dimensions.x/2 - 10;
            }

            quadTransforms[i] = t;
        }
    }

    public void render() {
        for (int i = 0; i < 3; i++) {
            quad.getShader().setUniform("vw_matrix", Matrix4.translate(new Vector3D(quadTransforms[i].position.x, 0.0f, 0.0f)));
            quad.render();
        }
    }

    public void setProjectionMatrix(Matrix4 projectionMatrix) {
        quad.setProjectionMatrix(projectionMatrix);
    }
}
