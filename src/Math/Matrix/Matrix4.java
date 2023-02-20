package Math.Matrix;

import Math.Vector.Vector3D;
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
