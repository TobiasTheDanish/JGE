package JGE.GameObjects;

import JGE.GameComponents.*;
import Math.Matrix.Matrix4;

import java.util.ArrayList;

public abstract class GameObject {
    protected Transform transform;
    private ArrayList<GameComponent> components;

    public GameObject() {
        transform = new Transform();
        this.components = new ArrayList<>();
    }

    public GameObject(ArrayList<GameComponent> components) {
        transform = new Transform();
        this.components = components;
    }

    public void addComponent(GameComponent component) {
        components.add(component);
    }

    public abstract void setProjectionMatrix(Matrix4 projectionMatrix);

    public abstract void update();

    public abstract void render();
}
