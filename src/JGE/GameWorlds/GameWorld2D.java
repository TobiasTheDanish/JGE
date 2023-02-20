package JGE.GameWorlds;

import JGE.GameObjects.GameObject;
import Math.Matrix.Matrix4;

import java.util.ArrayList;

public class GameWorld2D extends GameWorld{

    public GameWorld2D(float windowWidth, float windowHeight) {
        super(Matrix4.orthographic(0.0f, windowWidth, 0.0f, windowHeight, -1.0f, 1.0f), new ArrayList<>());
    }

    @Override
    public void update() {
        for (GameObject gameObject : gameObjects) {
            gameObject.update();
        }
    }

    @Override
    public void render() {
        for (GameObject gameObject : gameObjects) {
            gameObject.render();
        }
    }
}
