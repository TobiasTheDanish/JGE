import Math.Matrix.Matrix4;
import Math.Vector.Vector2D;
import Math.Vector.Vector3D;
import Rendering.Transform;
import Shapes.Entities.ScrollingBackground;
import Shapes.Primitives.Quad;
import Shapes.Primitives.Triangle;
import Utils.Input.Input;
import Utils.Input.MouseInput;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11C.*;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;
import static org.lwjgl.glfw.Callbacks.*;

public class Application implements Runnable{
    private long window;

    private final float windowWidth = 1600.0f;
    private final float windowHeight = 900.0f;
    private final String title = "Window";

    private ScrollingBackground bg;
    private Triangle triangle;

    private boolean isRunning = false;

    public void start() {
        isRunning = true;
        Thread thread = new Thread(this, "Display");
        thread.start();
    }

    @Override
    public void run() {
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

    private void init() {
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

        Matrix4 projectionMatrix = Matrix4.orthographic(0.0f, windowWidth, 0.0f, windowHeight, -1.0f, 1.0f);

        bg = new ScrollingBackground(new Transform( new Vector3D(windowWidth / 2, windowHeight / 2, 0.0f), new Vector3D(), new Vector2D(windowWidth, windowHeight), 1.0f), "./res/textures/flappy_bg.jpeg");
        bg.setProjectionMatrix(projectionMatrix);
        triangle = new Triangle(new Vector2D(windowWidth / 4.0f, windowHeight /3.0f), new Vector3D(windowWidth /2.0f, windowHeight /2.0f, 0.1f), new Vector3D(), 1.0f);
        triangle.setProjectionMatrix(projectionMatrix);
    }

    private void update() {
        glfwPollEvents();
        bg.update();
    }

    private void render() {
        glClear(GL_COLOR_BUFFER_BIT);
        glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        bg.render();
        triangle.render();

        glfwSwapBuffers(window);
    }

    private void destroy() {
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }
}
