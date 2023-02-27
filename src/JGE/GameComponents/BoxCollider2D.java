package JGE.GameComponents;

import JGE.GameObjects.GameObject;
import Math.Vector.Vector2D;
import Math.Vector.Vector3D;

public class BoxCollider2D extends Collider2D{
    @Override
    public boolean intersects(Collider2D other) {
        Transform otherTransform = other.gameObject.getTransform();
        Transform transform = gameObject.getTransform();

        return inRange(otherTransform.bottomLeftCorner(), otherTransform.upperRightCorner(), transform.upperLeftCorner()) ||
               inRange(otherTransform.bottomLeftCorner(), otherTransform.upperRightCorner(), transform.upperRightCorner()) ||
               inRange(otherTransform.bottomLeftCorner(), otherTransform.upperRightCorner(), transform.bottomLeftCorner()) ||
               inRange(otherTransform.bottomLeftCorner(), otherTransform.upperRightCorner(), transform.bottomRightCorner());
    }

    private boolean inRange(Vector2D startPoint, Vector2D endPoint, Vector2D point) {
        return (startPoint.x <= point.x && point.x <= endPoint.x) && (startPoint.y <= point.y && point.y <= endPoint.y);
    }

    @Override
    public boolean isHighestSubClass() {
        return false;
    }
}
