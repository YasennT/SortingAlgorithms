package bg.smg;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Element implements Comparable<Element> {
    private Image image;
    private final int width;
    private final int height;
    public Element(String imagePath, int width, int height) {
        try {
            this.image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.width = width;
        this.height = height;
    }
    public Image getImage() {
        return image;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    @Override
    public int compareTo(Element o) {
        return Integer.compare(this.width, o.width);
    }
}