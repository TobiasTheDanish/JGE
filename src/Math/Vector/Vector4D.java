package Math.Vector;

public class Vector4D {
    public float x,y,z,w;

    public Vector4D() {
        this.x = 0.0f;
        this.y = 0.0f;
        this.z = 0.0f;
        this.w = 0.0f;
    }

    public Vector4D(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Vector4D(Vector2D vec2) {
        this.x = vec2.x;
        this.y = vec2.y;
        this.z = 1.0f;
        this.w = 1.0f;
    }

    public Vector4D(Vector3D vec3) {
        this.x = vec3.x;
        this.y = vec3.y;
        this.z = vec3.z;
        this.w = 1.0f;
    }
}
