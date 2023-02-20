package JGE.GameWorlds;

import JGE.GameObjects.GameObject;
import Math.Matrix.Matrix4;

import java.util.ArrayList;

public abstract class GameWorld {
    private Matrix4 projectionMatrix;
    protected ArrayList<GameObject> gameObjects;

    public abstract void update();
    public abstract void render();

    public GameWorld(Matrix4 projectionMatrix, ArrayList<GameObject> gameObjects) {
        this.projectionMatrix = projectionMatrix;
        this.gameObjects = gameObjects;
    }

    public void addGameObject(GameObject gameObject) {
        gameObject.setProjectionMatrix(projectionMatrix);
        gameObjects.add(gameObject);
    }
}
