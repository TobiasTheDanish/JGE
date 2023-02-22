package JGE.GameWorlds;

import JGE.GameObjects.Camera;
import JGE.GameObjects.GameObject;
import Math.Matrix.Matrix4;

import java.util.ArrayList;

public abstract class GameWorld {
    private final Matrix4 projectionMatrix;
    protected Camera camera;
    protected ArrayList<GameObject> gameObjects;

    public abstract void update();
    public abstract void render();

    public GameWorld(Matrix4 projectionMatrix, ArrayList<GameObject> gameObjects, Camera camera) {
        this.projectionMatrix = projectionMatrix;
        this.gameObjects = gameObjects;
        this.camera = camera;
    }

    public void addGameObject(GameObject gameObject) {
        if (gameObjects == null) {
            gameObjects = new ArrayList<>();
        }

        gameObject.setProjectionMatrix(projectionMatrix);
        gameObjects.add(gameObject);
    }
}
