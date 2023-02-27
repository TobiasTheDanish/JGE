package JGE.GameWorlds;

import JGE.GameComponents.Collider2D;
import JGE.GameComponents.Transform;
import JGE.GameObjects.Camera2D;
import JGE.GameObjects.GameObject;
import Math.Matrix.Matrix4;
import Math.Vector.Vector2D;
import Utils.Callbacks.Input.*;

import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.*;

public class GameWorld2D extends GameWorld{

    public GameWorld2D(float windowWidth, float windowHeight) {
        super(Matrix4.orthographic(0.0f, windowWidth, 0.0f, windowHeight, -10.0f, 10.0f),
                new ArrayList<>(),
                new Camera2D(new Transform(new Vector2D(windowWidth, windowHeight)))
        );
    }

    @Override
    public void update() {
        if (Input.keys[GLFW_KEY_P]) {
            gamePaused = true;
        }

        if (Input.keys[GLFW_KEY_R]) {
            gamePaused = false;
        }

        if (!gamePaused) {
            for (GameObject gameObject : gameObjects) {
                gameObject.update();
                Collider2D col = gameObject.getComponent(Collider2D.class);
                if (col !=  null) {
                    for (GameObject go : gameObjects) {
                        Collider2D other = go.getComponent(Collider2D.class);
                        if (other != null) {
                            if (col.intersects(other)) {
                                System.out.println("Collision!");
                            }
                        }
                    }
                }
            }
        }

    }

    @Override
    public void render() {
        Matrix4 viewMatrix = Matrix4.transformation(camera.getTransform());
        for (GameObject gameObject : gameObjects) {
            gameObject.setTransformationMatrix(Matrix4.transformation(gameObject.getTransform()));
            gameObject.setViewMatrix(viewMatrix);
            gameObject.render();
        }
    }
}
