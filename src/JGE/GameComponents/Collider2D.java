package JGE.GameComponents;

import JGE.GameObjects.GameObject;

public abstract class Collider2D extends GameComponent{
    @Override
    public Class<? extends GameComponent> getHighestSubClass() {
        return Collider2D.class;
    }

    @Override
    public void update() {
    }

    public abstract boolean intersects(Collider2D other);
}
