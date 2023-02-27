package JGE.GameComponents;

import JGE.GameObjects.GameObject;

public abstract class GameComponent {
    protected GameObject gameObject;

    public abstract void update();
    public abstract boolean isHighestSubClass();
    public abstract Class<? extends GameComponent> getHighestSubClass();

    public GameObject getGameObject() {
        return gameObject;
    }

    public void setGameObject(GameObject gameObject) {
        this.gameObject = gameObject;
    }
}
