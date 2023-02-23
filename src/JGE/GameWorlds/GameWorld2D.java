package JGE.GameWorlds;

import JGE.GameComponents.Transform;
import JGE.GameObjects.Camera2D;
import JGE.GameObjects.GameObject;
import Math.Matrix.Matrix4;
import Math.Vector.Vector2D;
import Math.Vector.Vector3D;

import java.util.ArrayList;

public class GameWorld2D extends GameWorld{

    public GameWorld2D(float windowWidth, float windowHeight) {
        super(Matrix4.orthographic(0.0f, windowWidth, 0.0f, windowHeight, -1.0f, 1.0f), new ArrayList<>(), new Camera2D(new Transform(
                new Vector3D(), new Vector3D(), new Vector2D(windowWidth, windowHeight), 1.0f
        )));
    }

    @Override
    public void update() {
        for (GameObject gameObject : gameObjects) {
            gameObject.update();
        }
    }

    @Override
    public void render() {
        Matrix4 viewMatrix = Matrix4.transformation2D(camera.getTransform());
        for (GameObject gameObject : gameObjects) {
            gameObject.setTransformationMatrix(Matrix4.transformation(gameObject.getTransform()));
            gameObject.setViewMatrix(viewMatrix);
            gameObject.render();
        }
    }
}
