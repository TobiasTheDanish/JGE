package JGE.GameObjects;

import JGE.GameComponents.GameComponent;
import JGE.GameComponents.Transform;
import Math.Matrix.Matrix4;
import Math.Vector.Vector2D;
import Shapes.Primitives.Quad;
import Utils.Input.Input;

import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;

public class Bird extends GameObject{
    private float delta;

    public Bird(Transform t, String texturePath) {
        this.transform = t;

        this.shape = new Quad(new Vector2D(transform.dimensions.x, transform.dimensions.y), texturePath);
    }

    @Override
    public void update() {
//        transform.position.y -= delta;

        if (Input.keys[GLFW_KEY_SPACE]) {
            delta = -0.15f;
        } else {
            delta += 0.01f;
        }

//        transform.rotation.z = -delta * 90.0f;
    }

    @Override
    public void render() {
        shape.render();
    }
}
