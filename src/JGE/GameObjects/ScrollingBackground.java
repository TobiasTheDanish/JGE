package JGE.GameObjects;

import Math.Vector.Vector2D;
import Math.Vector.Vector3D;
import JGE.GameComponents.*;
import Shapes.Primitives.Quad;

public class ScrollingBackground extends GameObject {
    private float speed;
    private int frameCounter=0;
    public ScrollingBackground(float speed, Transform transform, String texturePath) {
        this.transform = transform;
        this.speed = speed;

        shape = new Quad(new Vector2D(this.transform.dimensions.x, this.transform.dimensions.y), texturePath);
    }

    public void update() {
        if (frameCounter >= 120 && speed < 6.5f) {
            frameCounter -= 120;
            speed *= 1.05f;
        }

        transform.position.add(Vector3D.left.multiply(speed));

        if (transform.position.x < 0 - transform.dimensions.x/2) {
            transform.position.x = this.transform.dimensions.x * 4.445f;
        }

        frameCounter++;
    }
}
