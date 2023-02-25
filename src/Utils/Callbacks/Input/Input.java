package Utils.Callbacks.Input;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;

public class Input extends GLFWKeyCallback {
    private int prevAction;
    public static boolean[] keys = new boolean[65536];

    @Override
    public void invoke(long window, int key, int scancode, int action, int mods) {
        if (action != prevAction) {
            keys[key] = action == GLFW.GLFW_PRESS;
            prevAction = action;
        }
    }
}
