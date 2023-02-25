package Utils.Callbacks.Input;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

public class MouseInput extends GLFWMouseButtonCallback {
    public static boolean[] buttons = new boolean[8];

    @Override
    public void invoke(long window, int button, int action, int mods) {
        buttons[button] = action != GLFW.GLFW_RELEASE;
    }
}
