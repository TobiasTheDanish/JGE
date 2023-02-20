package Rendering;

import Utils.BufferUtils;

import static org.lwjgl.system.MemoryUtil.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class VertexArray extends Component{
    private final int rendererID;

    public VertexArray(float[] vertices) {
        rendererID = glGenVertexArrays();
        glBindVertexArray(rendererID);

        int vertexBuffer = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vertexBuffer);
        glBufferData(GL_ARRAY_BUFFER, BufferUtils.createFlippedFloatBuffer(vertices), GL_STATIC_DRAW);
        glEnableVertexAttribArray(Shader.VERTEX_ATTRIB);
        glVertexAttribPointer(Shader.VERTEX_ATTRIB, 3, GL_FLOAT, false, 0, NULL);

        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
    }

    public VertexArray(float[] vertices, float[] textureCoordinates) {
        rendererID = glGenVertexArrays();
        glBindVertexArray(rendererID);

        int vertexBuffer = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vertexBuffer);
        glBufferData(GL_ARRAY_BUFFER, BufferUtils.createFlippedFloatBuffer(vertices), GL_STATIC_DRAW);
        glEnableVertexAttribArray(Shader.VERTEX_ATTRIB);
        glVertexAttribPointer(Shader.VERTEX_ATTRIB, 3, GL_FLOAT, false, 0, NULL);

        int TextureBuffer = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, TextureBuffer);
        glBufferData(GL_ARRAY_BUFFER, BufferUtils.createFlippedFloatBuffer(textureCoordinates), GL_STATIC_DRAW);
        glEnableVertexAttribArray(Shader.TCOORD_ATTRIB);
        glVertexAttribPointer(Shader.TCOORD_ATTRIB, 2, GL_FLOAT, false, 0, NULL);

        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
    }

    @Override
    public void bind() {
        glBindVertexArray(rendererID);
    }

    @Override
    public void unbind() {
        glBindVertexArray(0);
    }

    public int getRendererID() {
        return rendererID;
    }
}
