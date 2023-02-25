package Utils.Callbacks.Window;

import org.lwjgl.glfw.GLFWWindowSizeCallback;

import static org.lwjgl.opengl.GL11.glViewport;

public class SizeCallback extends GLFWWindowSizeCallback {
    @Override
    public void invoke(long window, int width, int height) {
        glViewport(0, 0, width, height);
    }
}
