package Rendering;

import Utils.BufferUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;

public class Texture extends Component {
    private final int rendererID, slot;
    private int height, width;

    public Texture(String imagePath, int textureSlot) {
        slot = textureSlot;
        rendererID = load(imagePath);
    }

    private int load(String imagePath) {
        int[] pixels = null;

        try {
            BufferedImage image = ImageIO.read(new FileInputStream(imagePath));
            height = image.getHeight();
            width = image.getWidth();
            pixels = new int[height * width];
            image.getRGB(0, 0, width, height, pixels, 0, width);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (pixels == null) return -1;

        int[] data = new int[height * width];
        for (int i = 0; i < height * width; i++) {
            int a = (pixels[i] & 0xff000000) >> 24;
            int r = (pixels[i] & 0xff0000) >> 16;
            int g = (pixels[i] & 0xff00) >> 8;
            int b = (pixels[i] & 0xff);

            data[i] = a << 24 | b << 16 | g << 8 | r;
        }

        int tex = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, tex);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, BufferUtils.createFlippedIntBuffer(data));

        unbind();

        return tex;
    }

    @Override
    public void bind() {
        glActiveTexture(GL_TEXTURE0 + slot);
        glBindTexture(GL_TEXTURE_2D, rendererID);
    }

    @Override
    public void unbind() {
        glBindTexture(GL_TEXTURE_2D, 0);
    }

    public int getRendererID() {
        return rendererID;
    }
}
