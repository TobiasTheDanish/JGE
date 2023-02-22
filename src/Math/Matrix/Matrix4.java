package Math.Matrix;

import JGE.GameComponents.Transform;
import Math.Vector.Vector3D;
import Math.Vector.Vector4D;
import Utils.BufferUtils;

import java.nio.FloatBuffer;
import java.util.Arrays;

public class Matrix4 {
    private float[] elements = new float[4 * 4];

    public Matrix4() {
        for (int i = 0; i < 4*4; i++) {
            this.elements[i] = 0;
        }
    }

    public Matrix4(float[] elements) {
        this.elements = elements;
    }

    public static Matrix4 identity() {
        Matrix4 res = new Matrix4();

        res.elements[0 + 0 * 4] = 1;
        res.elements[1 + 1 * 4] = 1;
        res.elements[2 + 2 * 4] = 1;
        res.elements[3 + 3 * 4] = 1;

        return res;
    }

    public static Matrix4 orthographic(float left, float right, float bottom, float top, float near, float far) {
        Matrix4 res = new Matrix4();

        res.elements[0 + 0 * 4] = (2 / (right-left));
        res.elements[1 + 1 * 4] = (2 / (top - bottom));
        res.elements[2 + 2 * 4] = (2 / (far - near));
        res.elements[3 + 3 * 4] = 1;

        res.elements[0 + 3 * 4] = -((right + left) / (right - left));
        res.elements[1 + 3 * 4] = -((top + bottom) / (top - bottom));
        res.elements[2 + 3 * 4] = -((far + near) / (far - near));

        return res;
    }

    public static Matrix4 translate(Vector3D vector) {
        Matrix4 res = identity();

        res.elements[0 + 3 * 4] = vector.x;
        res.elements[1 + 3 * 4] = vector.y;
        res.elements[2 + 3 * 4] = vector.z;

        return res;
    }

    public static Matrix4 rotateX(float angle) {
        Matrix4 res = Matrix4.identity();

        float r = (float)Math.toRadians(angle);
        float cos = (float) Math.cos(r);
        float sin = (float) Math.sin(r);

        res.elements[1 + 1 * 4] = cos;
        res.elements[2 + 1 * 4] = -sin;
        res.elements[1 + 2 * 4] = sin;
        res.elements[2 + 2 * 4] = cos;

        return res;
    }

    public static Matrix4 rotateY(float angle) {
        Matrix4 res = Matrix4.identity();

        float r = (float)Math.toRadians(angle);
        float cos = (float) Math.cos(r);
        float sin = (float) Math.sin(r);

        res.elements[0 + 0 * 4] = cos;
        res.elements[2 + 0 * 4] = sin;
        res.elements[0 + 2 * 4] = -sin;
        res.elements[2 + 2 * 4] = cos;

        return res;
    }

    public static Matrix4 rotateZ(float angle) {
        Matrix4 res = Matrix4.identity();

        float r = (float)Math.toRadians(angle);
        float cos = (float) Math.cos(r);
        float sin = (float) Math.sin(r);

        res.elements[0 + 0 * 4] = cos;
        res.elements[1 + 0 * 4] = -sin;
        res.elements[0 + 1 * 4] = sin;
        res.elements[1 + 1 * 4] = cos;

        return res;
    }

    public Matrix4 multiply(Matrix4 other) {
        Matrix4 res = new Matrix4();

        for (int rows = 0; rows < 4; rows++) {
            for (int col = 0; col < 4; col++) {
                float sum = 0;
                for (int e = 0; e < 4; e++) {
                    sum += this.elements[e + rows * 4] * other.elements[col + e * 4];
                }
                res.elements[col + rows * 4] = sum;
            }
        }

        return res;
    }

    private Matrix4 multiply(float value) {
        Matrix4 res = new Matrix4();

        for (int i = 0; i < this.elements.length; i++) {
            res.elements[i] = this.elements[i] * value;
        }

        return res;
    }

    public static Matrix4 transformation(Transform t) {
        Matrix4 res = Matrix4.translate(t.position);

        res = res.multiply(Matrix4.rotateX(t.rotation.x));
        res = res.multiply(Matrix4.rotateY(t.rotation.y));
        res = res.multiply(Matrix4.rotateZ(t.rotation.z));

        res = res.multiply(t.scale);

        return res;
    }

    public FloatBuffer toFloatBuffer() {
        return BufferUtils.createFlippedFloatBuffer(elements);
    }

    @Override
    public String toString() {
        return "Matrix4{" +
                "elements=" + Arrays.toString(elements) +
                '}';
    }
}
