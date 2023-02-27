package JGE.GameObjects;

import JGE.GameComponents.Transform;
import Math.Matrix.Matrix4;
import Math.Vector.Vector2D;
import Math.Vector.Vector3D;
import Shapes.Primitives.Quad;

import java.util.Random;

public class PipeSet extends GameObject {
    private int frameCounter = 0;
    private float speed;

    private final Random random;
    private final Vector2D posRange;
    private final float respawnPos;

    public PipeSet(float respawnPos, float speed, Vector2D posRange, Transform transform, String texturePath) {
        super(transform, new Quad(transform.dimensions, texturePath));
        this.random = new Random();
        this.respawnPos = respawnPos;
        this.speed = speed;
        this.posRange = posRange;

        transform.position.y = random.nextFloat() * (posRange.x - posRange.y) + posRange.y;

        float yOffset = 175.0f;
        children.add(new Pipe(new Transform(new Vector3D(0.0f, (yOffset + transform.dimensions.y/2)), new Vector3D(0.0f, 180.0f, 180.0f), transform.dimensions, transform.scale), this.shape));
        children.add(new Pipe(new Transform(new Vector3D(0.0f, -(yOffset + transform.dimensions.y/2)), transform.dimensions), this.shape));
    }

    @Override
    public void update() {
        super.update();

        if (frameCounter >= 120 && speed < 6.5f) {
            frameCounter -= 120;
            speed *= 1.05f;
        }
        frameCounter++;

        transform.position.add(Vector3D.left.multiply(speed));

        if (transform.position.x < 0 - transform.dimensions.x/2) {
            transform.position.x = respawnPos;
            transform.position.y = random.nextFloat() * (posRange.x - posRange.y) + posRange.y;
        }
    }

    @Override
    public void render() {
        for (GameObject child : children) {
            if (child instanceof Pipe) {
                Pipe pipe = (Pipe) child;
                pipe.setTransformationMatrix(Matrix4.transformation(pipe.getTransform()));
                pipe.setViewMatrix(Matrix4.transformation(transform));
                pipe.render();
            }
        }
    }
}
