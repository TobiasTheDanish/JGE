package Utils.Color;

import java.util.Objects;

public class Color {
    public float r, g, b, a;

    private Color() {
        r = 0;
        g = 0;
        b = 0;
        a = 0;
    }

    public static Color RGBA(float r, float g, float b, float a) {
        Color c = new Color();

        c.r = r;
        c.g = g;
        c.b = b;
        c.a = a;

        return c;
    }

    public static Color white() {
        return RGBA(1.0f,1.0f,1.0f,1.0f);
    }

    public static Color black() {
        return new Color();
    }

    @Override
    public String toString() {
        return "(" + r + ", " + g + ", " + b + ", " + a + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Color color = (Color) o;
        return Float.compare(color.r, r) == 0 && Float.compare(color.g, g) == 0 && Float.compare(color.b, b) == 0 && Float.compare(color.a, a) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(r, g, b, a);
    }
}
