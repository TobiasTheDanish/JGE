package JGE.GameObjects;

import JGE.GameComponents.Transform;
import Math.Vector.Vector3D;

public abstract class Camera {
    Transform transform;

    public Camera(Transform transform) {
        this.transform = transform;
    }

    public Transform getTransform() {
        return transform;
    }

    public void move(Vector3D vecToMove) {
        transform.position.add(vecToMove);
    }

    public void scale(float amount) {
        transform.scale += amount;
    }
}
