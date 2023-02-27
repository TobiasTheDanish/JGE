package JGE.GameObjects;

import JGE.GameComponents.*;
import Math.Matrix.Matrix4;
import Math.Vector.Vector3D;
import Shapes.Primitives.Shape;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class GameObject {
    protected Transform transform;
    protected Shape shape;
    protected Map<Class<? extends GameComponent>, GameComponent> components;
    protected List<GameObject> children;

    public GameObject(Transform transform, Shape shape) {
        this.transform = transform;
        this.shape = shape;
        this.components = new HashMap<>();
        this.children = new ArrayList<>();
    }

    public <T extends GameComponent> void addComponent(T component) {
        if (components.containsKey(component.getClass()) || components.containsKey(component.getHighestSubClass())) {
            return;
        }
        if (component.isHighestSubClass()) {
            component.setGameObject(this);
            components.put(component.getClass(), component);
        } else {
            component.setGameObject(this);
            components.put(component.getHighestSubClass(), component);
        }

        System.out.println(components);
    }

    public <T extends GameComponent> T getComponent(Class<T> type) {
        return (T) components.get(type);
    }

    public Transform getTransform() {
        return transform;
    }

    public Vector3D getPosition() {
        return transform.position;
    }

    public Vector3D getRotation() {
        return transform.rotation;
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

    public void update() {
        if (components != null) {
            for (GameComponent gameComponent : components.values()) {
                gameComponent.update();
            }
        }
    }

    public Shape getShape() {
        return shape;
    }

    public List<GameObject> getChildren() {
        return children;
    }
}
