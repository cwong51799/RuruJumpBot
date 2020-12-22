import java.awt.*;

public class ColorHelper {
    public static void main(String[] args) {
        int r, g,b, rgb;
        Color c;

        r = 49;
        g = 157;
        b = 130;
        c = new Color(r,g,b);
        rgb = c.hashCode();
        System.out.println(rgb);

    }
}
