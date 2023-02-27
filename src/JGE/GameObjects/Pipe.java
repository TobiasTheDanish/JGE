package JGE.GameObjects;

import JGE.GameComponents.BoxCollider2D;
import JGE.GameComponents.Transform;
import Shapes.Primitives.Shape;

public class Pipe extends GameObject {

    public Pipe(Transform transform, Shape shape) {
        super(transform, shape);
        addComponent(new BoxCollider2D());
    }
}
