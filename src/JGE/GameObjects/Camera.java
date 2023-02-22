package JGE.GameObjects;

import JGE.GameComponents.Transform;

public abstract class Camera {
    Transform transform;

    public Camera(Transform transform) {
        this.transform = transform;
    }

    public Transform getTransform() {
        return transform;
    }
}
