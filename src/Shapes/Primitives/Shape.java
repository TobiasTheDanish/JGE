package Shapes.Primitives;

import Math.Matrix.Matrix4;
import Math.Vector.Vector3D;
import Rendering.IndexBuffer;
import Rendering.Shader;
import Rendering.Texture;
import Rendering.VertexArray;
import Utils.Color.Color;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public abstract class Shape {
    private final int vertexCount;
    protected final Shader shader;
    protected final VertexArray mesh;
    protected IndexBuffer indexBuffer;
    protected Texture texture = null;

    protected Vector3D position;
    protected Vector3D rotation;
    protected float scale;

    protected Shape(float[] vertices, String vertexPath, String fragmentPath) {
        vertexCount = vertices.length;
        mesh = new VertexArray(vertices);
        shader = new Shader(vertexPath, fragmentPath);
    }

    protected Shape(byte[] indices, float[] vertices, String vertexPath, String fragmentPath) {
        vertexCount = indices.length;
        indexBuffer = new IndexBuffer(indices, vertexCount);
        mesh = new VertexArray(vertices);
        shader = new Shader(vertexPath, fragmentPath);
    }

    protected Shape(byte[] indices, float[] vertices, float[] textureCoords, String vertexPath, String fragmentPath, String texturePath) {
        vertexCount = indices.length;
        indexBuffer = new IndexBuffer(indices, vertexCount);
        mesh = new VertexArray(vertices, textureCoords);
        texture = new Texture(texturePath, 1);
        shader = new Shader(vertexPath, fragmentPath);
    }

    public void render() {
        mesh.bind();
        shader.bind();
        if (indexBuffer != null) indexBuffer.bind();
        if (texture != null) texture.bind();

        if (indexBuffer != null) {
            glDrawElements(GL_TRIANGLES, vertexCount, GL_UNSIGNED_BYTE, NULL);
        } else {
            glDrawArrays(GL_TRIANGLES, 0, vertexCount);
        }

        shader.unbind();
        mesh.unbind();
        if (indexBuffer != null) indexBuffer.unbind();
        if (texture != null) texture.unbind();
    }

    public void move(Vector3D movement) {
        this.position.add(movement);
    }

    public void rotate(Vector3D movement) {
        this.rotation.add(movement);
    }

    public void scale(float scale) {
        this.scale += scale;
    }

    public void setProjectionMatrix(Matrix4 matrix) {
        shader.bind();
        shader.setUniform("pr_matrix", matrix);
        shader.unbind();
    }

    public Shader getShader() {
        return shader;
    }

    public VertexArray getMesh() {
        return mesh;
    }

    public IndexBuffer getIndexBuffer() {
        return indexBuffer;
    }

    public Texture getTexture() {
        return texture;
    }

    public Vector3D getPosition() {
        return position;
    }

    public Vector3D getRotation() {
        return rotation;
    }

    public float getScale() {
        return scale;
    }
}
