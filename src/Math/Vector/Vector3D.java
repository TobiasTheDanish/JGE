package Math.Vector;

public class Vector3D implements Cloneable {
    public float x,y,z;

    public static Vector3D left = new Vector3D(-1.0f, 0.0f, 0.0f);
    public static Vector3D right = new Vector3D(1.0f, 0.0f, 0.0f);
    public static Vector3D down = new Vector3D(0.0f, -1.0f, 0.0f);
    public static Vector3D up = new Vector3D(0.0f, 1.0f, 0.0f);
    public static Vector3D back = new Vector3D(0.0f, 0.0f, -1.0f);
    public static Vector3D forward = new Vector3D(0.0f, 0.0f, 1.0f);

    public Vector3D() {
        this.x = 0.0f;
        this.y = 0.0f;
        this.z = 0.0f;
    }

    public Vector3D(float x, float y) {
        this.x = x;
        this.y = y;
        this.z = 0.0f;
    }

    public Vector3D(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3D(Vector2D vec2) {
        this.x = vec2.x;
        this.y = vec2.y;
        this.z = 1.0f;
    }

    public Vector3D add(Vector3D other) {
        this.x += other.x;
        this.y += other.y;
        this.z += other.z;
        return this;
    }

    public Vector3D multiply(Vector3D other) {
        try {
            Vector3D res = (Vector3D) this.clone();
            res.x *= other.x;
            res.y *= other.y;
            res.z *= other.z;
            return res;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Vector3D multiply(float value) {
        try {
            Vector3D res = (Vector3D) this.clone();
            res.x *= value;
            res.y *= value;
            res.z *= value;
            return res;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        return "x: " + x + ", y: " + y + ", z: " + z;
    }
}
