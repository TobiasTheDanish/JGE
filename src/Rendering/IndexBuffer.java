package Rendering;

import Utils.BufferUtils;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;

public class IndexBuffer extends Component {
    private final int rendererID;
    private final int count;

    public IndexBuffer(byte[] data, int count) {
        this.count = count;

        rendererID = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, rendererID);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, BufferUtils.createByteBuffer(data), GL_STATIC_DRAW);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
    }

    @Override
    public void bind() {
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, rendererID);
    }

    @Override
    public void unbind() {
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
    }

    public int getCount() {
        return count;
    }
}
