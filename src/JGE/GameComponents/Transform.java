package JGE.GameComponents;

import Math.Vector.Vector2D;
import Math.Vector.Vector3D;

public class Transform {
    public Vector3D position;
    public Vector3D rotation;
    public Vector2D dimensions;
    public float scale;

    public Transform() {
        super();
        this.position = new Vector3D();
        this.rotation = new Vector3D();
        this.dimensions = new Vector2D();
        this.scale = 1.0f;
    }

    public Transform(Transform t) {
        this.position = t.position;
        this.rotation = t.rotation;
        this.dimensions = t.dimensions;
        this.scale = t.scale;
    }

    public Transform(Vector2D dimensions) {
        this.dimensions = dimensions;
        this.position = new Vector3D();
        this.rotation = new Vector3D();
        this.scale = 1.0f;
    }

    public Transform(Vector3D position, Vector2D dimensions) {
        this.position = position;
        this.dimensions = dimensions;
        this.rotation = new Vector3D();
        this.scale = 1.0f;
    }

    public Transform(Vector3D position, Vector3D rotation, Vector2D dimensions) {
        this.position = position;
        this.rotation = rotation;
        this.dimensions = dimensions;
        this.scale = 1.0f;
    }

    public Transform(Vector3D position, Vector3D rotation, Vector2D dimensions, float scale) {
        this.position = position;
        this.rotation = rotation;
        this.dimensions = dimensions;
        this.scale = scale;
    }

    public Vector2D upperLeftCorner() {
        return new Vector2D(position.x - dimensions.x/2, position.y + dimensions.y/2);
    }

    public Vector2D upperRightCorner() {
        return new Vector2D(position.x + dimensions.x/2, position.y + dimensions.y/2);
    }

    public Vector2D bottomLeftCorner() {
        return new Vector2D(position.x - dimensions.x/2, position.y - dimensions.y/2);
    }

    public Vector2D bottomRightCorner() {
        return new Vector2D(position.x + dimensions.x/2, position.y - dimensions.y/2);
    }
}
