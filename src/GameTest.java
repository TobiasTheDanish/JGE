import JGE.GameComponents.Transform;
import JGE.GameWorlds.GameWorld;
import JGE.GameWorlds.GameWorld2D;
import JGE.Game;
import Math.Vector.Vector2D;
import Math.Vector.Vector3D;
import JGE.GameObjects.ScrollingBackground;

import static org.lwjgl.glfw.GLFW.*;

public class GameTest extends Game {
    GameWorld world;
    ScrollingBackground bg;

    @Override
    protected void init() {
        world = new GameWorld2D(windowWidth, windowHeight);

        bg = new ScrollingBackground(new Transform( new Vector3D(windowWidth / 2, windowHeight / 2, 0.0f), new Vector3D(), new Vector2D(windowWidth, windowHeight), 1.0f), "./res/textures/flappy_bg.jpeg");

        world.addGameObject(bg);
    }

    @Override
    protected void update() {
        world.update();
    }

    @Override
    protected void render() {
        super.render();

        world.render();
        glfwSwapBuffers(window);
    }
    @Override
    protected void destroy() {
        super.destroy();
    }
}