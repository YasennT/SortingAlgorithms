package bg.smg;

public class Element {
    private String image;
    private int size;

    public Element(String image, int size) {
        this.image = image;
        this.size = size;
    }

    public String getImage() {
        return image;
    }

    public int getSize() {
        return size;
    }
}
