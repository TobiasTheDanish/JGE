package JGE.GameObjects;

import JGE.GameComponents.BoxCollider2D;
import JGE.GameComponents.Collider2D;
import JGE.GameComponents.Transform;
import Shapes.Primitives.Quad;
import Utils.Callbacks.Input.*;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_UP;

public class Bird extends GameObject{
    private float delta;

    public Bird(Transform transform, String texturePath) {
        super(transform, new Quad(transform.dimensions, texturePath));
        Collider2D collider2D = new BoxCollider2D();
        this.addComponent(collider2D);
    }

    @Override
    public void update() {
        super.update();
        transform.position.y += delta;

        if (Input.keys[GLFW_KEY_UP]) {
            delta = 15.0f;
        } else {
            delta -= 0.9f;
        }

        transform.rotation.z = delta;
    }
}