import JGE.GameComponents.Transform;
import JGE.GameObjects.Bird;
import JGE.GameWorlds.GameWorld;
import JGE.GameWorlds.GameWorld2D;
import JGE.Game;
import Math.Vector.Vector2D;
import Math.Vector.Vector3D;
import JGE.GameObjects.ScrollingBackground;
import Utils.Input.Input;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_P;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_R;

public class GameTest extends Game {
    GameWorld world;

    ScrollingBackground[] bg;
    private boolean gamePaused = true;

    @Override
    protected void init() {
        world = new GameWorld2D(windowWidth, windowHeight);

        bg = new ScrollingBackground[5];
        bg[0] = new ScrollingBackground(new Transform( new Vector3D(windowWidth * 0.165f, windowHeight / 2, 0.0f), new Vector3D(), new Vector2D(windowWidth/3, windowHeight), 1.0f), "./res/textures/flappy_bg.jpeg");
        bg[1] = new ScrollingBackground(new Transform( new Vector3D(windowWidth * 0.495f, windowHeight / 2, 0.0f), new Vector3D(), new Vector2D(windowWidth/3, windowHeight), 1.0f), "./res/textures/flappy_bg.jpeg");
        bg[2] = new ScrollingBackground(new Transform( new Vector3D(windowWidth * 0.825f, windowHeight / 2, 0.0f), new Vector3D(), new Vector2D(windowWidth/3, windowHeight), 1.0f), "./res/textures/flappy_bg.jpeg");
        bg[3] = new ScrollingBackground(new Transform( new Vector3D(windowWidth * 1.155f, windowHeight / 2, 0.0f), new Vector3D(), new Vector2D(windowWidth/3, windowHeight), 1.0f), "./res/textures/flappy_bg.jpeg");
        bg[4] = new ScrollingBackground(new Transform( new Vector3D(windowWidth * 1.485f, windowHeight / 2, 0.0f), new Vector3D(), new Vector2D(windowWidth/3, windowHeight), 1.0f), "./res/textures/flappy_bg.jpeg");

        for (ScrollingBackground sbg : bg) {
            world.addGameObject(sbg);
        }

        Bird bird = new Bird(new Transform(new Vector3D(windowWidth/3, windowHeight/2,-0.9f), new Vector2D(100.0f, 100.0f)), "./res/textures/flappy_bird.png");
        world.addGameObject(bird);
    }

    @Override
    protected void update() {
        super.update();

        if (Input.keys[GLFW_KEY_P]) {
            gamePaused = true;
        }

        if (Input.keys[GLFW_KEY_R]) {
            gamePaused = false;
        }

        if (!gamePaused) {
            world.update();
        }
    }

    @Override
    protected void render() {
        super.render();

        world.render();
    }
}