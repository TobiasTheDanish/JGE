import JGE.GameComponents.Transform;
import JGE.GameObjects.Bird;
import JGE.GameWorlds.GameWorld;
import JGE.GameWorlds.GameWorld2D;
import JGE.Game;
import Math.Vector.Vector2D;
import Math.Vector.Vector3D;
import JGE.GameObjects.ScrollingBackground;

public class GameTest extends Game {
    GameWorld world;

    ScrollingBackground[] bg;

    @Override
    protected void init() {
        world = new GameWorld2D(windowWidth, windowHeight);

        Bird bird = new Bird(new Transform(new Vector3D(windowWidth/3, windowHeight/2), new Vector2D(100.0f, 100.0f)), "./res/textures/flappy_bird.png");

        bg = new ScrollingBackground[5];
        bg[0] = new ScrollingBackground(2.0f, new Transform( new Vector3D(windowWidth * 0.0f, windowHeight / 2), new Vector2D(windowWidth/3, windowHeight)), "./res/textures/flappy_bg.jpeg");
        bg[1] = new ScrollingBackground(2.0f, new Transform( new Vector3D(windowWidth * 0.33f, windowHeight / 2), new Vector2D(windowWidth/3, windowHeight)), "./res/textures/flappy_bg.jpeg");
        bg[2] = new ScrollingBackground(2.0f, new Transform( new Vector3D(windowWidth * 0.66f, windowHeight / 2), new Vector2D(windowWidth/3, windowHeight)), "./res/textures/flappy_bg.jpeg");
        bg[3] = new ScrollingBackground(2.0f, new Transform( new Vector3D(windowWidth * 0.99f, windowHeight / 2), new Vector2D(windowWidth/3, windowHeight)), "./res/textures/flappy_bg.jpeg");
        bg[4] = new ScrollingBackground(2.0f, new Transform( new Vector3D(windowWidth * 1.32f, windowHeight / 2), new Vector2D(windowWidth/3, windowHeight)), "./res/textures/flappy_bg.jpeg");

        for (ScrollingBackground sbg : bg) {
            world.addGameObject(sbg);
        }

        world.addGameObject(bird);
    }

    @Override
    protected void update() {
        super.update();

        world.update();
    }

    @Override
    protected void render() {
        super.render();

        world.render();
    }
}