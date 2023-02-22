package JGE.GameObjects;

import Math.Matrix.Matrix4;
import Math.Vector.Vector2D;
import Math.Vector.Vector3D;
import JGE.GameComponents.*;
import Shapes.Primitives.Quad;

public class ScrollingBackground extends GameObject {
    public ScrollingBackground(Transform transform, String texturePath) {
        this.transform = transform;

        shape = new Quad(new Vector2D(this.transform.dimensions.x, this.transform.dimensions.y), texturePath);
    }

    public void update() {
        transform.position.add(Vector3D.left.multiply(2.0f));

        if (transform.position.x < 0 - transform.dimensions.x/2) {
            transform.position.x = this.transform.dimensions.x * 3f;
        }
    }

    public void render() {
        for (int i = 0; i < 3; i++) {
            shape.render();
        }
    }
}
