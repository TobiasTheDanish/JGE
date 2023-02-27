import JGE.GameComponents.Transform;
import JGE.GameObjects.Bird;
import JGE.GameObjects.PipeSet;
import JGE.GameWorlds.GameWorld;
import JGE.GameWorlds.GameWorld2D;
import JGE.Game;
import Math.Vector.Vector2D;
import Math.Vector.Vector3D;
import JGE.GameObjects.ScrollingBackground;

public class GameTest extends Game {
    GameWorld world;


    @Override
    protected void init() {
        world = new GameWorld2D(windowWidth, windowHeight);

        Bird bird = new Bird(new Transform(new Vector3D(windowWidth/3, windowHeight/2), new Vector2D(85.0f, 85.0f)), "./res/textures/flappy_bird.png");


        ScrollingBackground[] bg = new ScrollingBackground[5];
        bg[0] = new ScrollingBackground(2.0f, new Transform( new Vector3D(windowWidth * 0.0f, windowHeight / 2), new Vector2D(windowWidth/3, windowHeight)), "./res/textures/flappy_bg.jpeg");
        bg[1] = new ScrollingBackground(2.0f, new Transform( new Vector3D(windowWidth * 0.33f, windowHeight / 2), new Vector2D(windowWidth/3, windowHeight)), "./res/textures/flappy_bg.jpeg");
        bg[2] = new ScrollingBackground(2.0f, new Transform( new Vector3D(windowWidth * 0.66f, windowHeight / 2), new Vector2D(windowWidth/3, windowHeight)), "./res/textures/flappy_bg.jpeg");
        bg[3] = new ScrollingBackground(2.0f, new Transform( new Vector3D(windowWidth * 0.99f, windowHeight / 2), new Vector2D(windowWidth/3, windowHeight)), "./res/textures/flappy_bg.jpeg");
        bg[4] = new ScrollingBackground(2.0f, new Transform( new Vector3D(windowWidth * 1.32f, windowHeight / 2), new Vector2D(windowWidth/3, windowHeight)), "./res/textures/flappy_bg.jpeg");

        float pipeDist = 750.0f;
        PipeSet[] pipes = new PipeSet[5];
        pipes[0] = new PipeSet(pipeDist * pipes.length, 2.0f, new Vector2D(windowHeight * 0.75f, windowHeight * 0.25f), new Transform(new Vector3D(windowWidth -pipeDist/2, 0.0f), new Vector2D(150.0f, 1000.0f)), "./res/textures/flappy_pipe.png");
        pipes[1] = new PipeSet(pipeDist * pipes.length, 2.0f, new Vector2D(windowHeight * 0.75f, windowHeight * 0.25f), new Transform(new Vector3D(windowWidth + pipeDist/2, 0.0f), new Vector2D(150.0f, 1000.0f)), "./res/textures/flappy_pipe.png");
        pipes[2] = new PipeSet(pipeDist * pipes.length, 2.0f, new Vector2D(windowHeight * 0.75f, windowHeight * 0.25f), new Transform(new Vector3D(windowWidth + pipeDist * 1.5f, 0.0f), new Vector2D(150.0f, 1000.0f)), "./res/textures/flappy_pipe.png");
        pipes[3] = new PipeSet(pipeDist * pipes.length, 2.0f, new Vector2D(windowHeight * 0.75f, windowHeight * 0.25f), new Transform(new Vector3D(windowWidth + pipeDist * 2.5f, 0.0f), new Vector2D(150.0f, 1000.0f)), "./res/textures/flappy_pipe.png");
        pipes[4] = new PipeSet(pipeDist * pipes.length, 2.0f, new Vector2D(windowHeight * 0.75f, windowHeight * 0.25f), new Transform(new Vector3D(windowWidth + pipeDist * 3.5f, 0.0f), new Vector2D(150.0f, 1000.0f)), "./res/textures/flappy_pipe.png");

        for (ScrollingBackground sbg : bg) {
            world.addGameObject(sbg);
        }

        for (PipeSet p : pipes) {
            world.addGameObject(p);
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