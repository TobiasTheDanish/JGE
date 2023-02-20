package Rendering;

import Math.Vector.Vector2D;
import Math.Vector.Vector3D;

public class Transform {
    public Vector3D position;
    public Vector3D rotation;
    public Vector2D dimensions;
    public float scale;

    public Transform(Transform t) {
        this.position = t.position;
        this.rotation = t.rotation;
        this.dimensions = t.dimensions;
        this.scale = t.scale;
    }

    public Transform(Vector2D dimensions) {
        this.dimensions = dimensions;
    }

    public Transform(Vector3D position, Vector2D dimensions) {
        this.position = position;
        this.dimensions = dimensions;
    }

    public Transform(Vector3D position, Vector3D rotation, Vector2D dimensions) {
        this.position = position;
        this.rotation = rotation;
        this.dimensions = dimensions;
    }

    public Transform(Vector3D position, Vector3D rotation, Vector2D dimensions, float scale) {
        this.position = position;
        this.rotation = rotation;
        this.dimensions = dimensions;
        this.scale = scale;
    }
}
