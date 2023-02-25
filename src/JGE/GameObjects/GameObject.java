package JGE.GameObjects;

import JGE.GameComponents.*;
import Math.Matrix.Matrix4;
import Shapes.Primitives.Shape;

import java.util.ArrayList;

public abstract class GameObject {
    protected Transform transform;
    protected Shape shape;
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

    public Transform getTransform() {
        return transform;
    }

    public void setProjectionMatrix(Matrix4 projectionMatrix) {
        shape.setProjectionMatrix(projectionMatrix);
    }

    public void setViewMatrix(Matrix4 viewMatrix) {
        shape.setViewMatrix(viewMatrix);
    }

    public void setTransformationMatrix(Matrix4 transformationMatrix) {
        shape.setTransformationMatrix(transformationMatrix);
    }

    public void render() {
        if (shape != null) {
            shape.render();
        }
    }

    public abstract void update();
}
