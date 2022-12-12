public class Visibility {
    boolean leftVisible, rightVisible, upVisible, downVisible;

    public boolean isVisible() {
        return this.leftVisible || this.rightVisible || this.upVisible || this.downVisible;
    }
}