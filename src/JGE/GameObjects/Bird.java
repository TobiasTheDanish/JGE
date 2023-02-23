package JGE.GameObjects;

import JGE.GameComponents.GameComponent;
import JGE.GameComponents.Transform;
import Math.Matrix.Matrix4;
import Math.Vector.Vector2D;
import Shapes.Primitives.Quad;
import Utils.Input.Input;

import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_UP;

public class Bird extends GameObject{
    private float delta;

    public Bird(Transform t, String texturePath) {
        this.transform = t;

        this.shape = new Quad(new Vector2D(transform.dimensions.x, transform.dimensions.y), texturePath);
    }

    @Override
    public void update() {
        transform.position.y += delta;

        if (Input.keys[GLFW_KEY_UP]) {
            delta = 15.0f;
        } else {
            delta -= 0.9f;
        }

        transform.rotation.z = delta;
    }
}











