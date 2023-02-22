package JGE;

import Utils.Input.Input;
import Utils.Input.MouseInput;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.opengl.GL11C.*;
import static org.lwjgl.opengl.GL11C.glClearColor;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

public abstract class Game implements Runnable {
    protected long window;

    protected float windowWidth = 1600.0f;
    protected float windowHeight = 900.0f;
    protected final String title = "Window";

    protected boolean isRunning = false;

    protected abstract void init();

    public void start() {
        isRunning = true;
        Thread thread = new Thread(this, "Game");
        thread.start();
    }

    @Override
    public void run() {
        initWindow();

        init();

        long lastTime = System.nanoTime();
        double delta = 0.0;
        double ns = 1000000000.0 / 60.0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while (isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1.0) {
                update();
                updates++;
                delta--;
            }
            render();
            glfwSwapBuffers(window);

            frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.print("\r" + updates + " ups, " + frames + " fps");
                updates = 0;
                frames = 0;
            }
            if (glfwWindowShouldClose(window)) {
                isRunning = false;
            }
        }

        destroy();
    }

    private void initWindow() {
        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit()) {
            isRunning = false;
            return;
        }

        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        window = glfwCreateWindow((int) windowWidth, (int) windowHeight, title, NULL, NULL);
        if (window == NULL) {
            isRunning = false;
            return;
        }

        glfwSetKeyCallback(window, new Input());
        glfwSetMouseButtonCallback(window, new MouseInput());

        try (MemoryStack stack = stackPush() ) {
            IntBuffer wWidth = stack.mallocInt(1);
            IntBuffer wHeight = stack.mallocInt(1);

            glfwGetWindowSize(window, wWidth, wHeight);

            GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            glfwSetWindowPos(window, (vidMode.width() - wWidth.get(0)) / 2, (vidMode.height() - wHeight.get(0)) / 2 );
        }

        glfwMakeContextCurrent(window);
        glfwShowWindow(window);

        isRunning = true;

        GL.createCapabilities();

        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        System.out.println("OpenGL: " + glGetString(GL_VERSION));
    }

    protected void update() {
        glfwPollEvents();
    }

    protected void render() {
        glClear(GL_COLOR_BUFFER_BIT);
        glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
    }

    protected void destroy() {
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }
}
