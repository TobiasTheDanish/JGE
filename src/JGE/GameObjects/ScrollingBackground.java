package JGE.GameObjects;

import Math.Vector.Vector3D;
import JGE.GameComponents.*;
import Shapes.Primitives.Quad;

public class ScrollingBackground extends GameObject {
    private float speed;
    private int frameCounter=0;
    public ScrollingBackground(float speed, Transform transform, String texturePath) {
        super(transform, new Quad(transform.dimensions, texturePath));

        this.speed = speed;
    }

    public void update() {
        super.update();
        if (frameCounter >= 120 && speed < 6.5f) {
            frameCounter -= 120;
            speed *= 1.05f;
        }

        transform.position.add(Vector3D.left.multiply(speed));

        if (transform.position.x < 0 - transform.dimensions.x/2) {
            transform.position.x = this.transform.dimensions.x * 4.43f;
        }

        frameCounter++;
    }
}