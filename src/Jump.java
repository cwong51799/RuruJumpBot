import javax.imageio.ImageIO;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.awt.AWTException;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;
import java.awt.image.WritableRaster;
import java.io.File;

public class Jump {
    public Rectangle rect;

    public Jump(int x, int y, int width, int height) {
        rect = new Rectangle(x, y, width, height);
    }

    static int WHITE = -1118482;
    static int MINT_GREEN = -13525630;

    public void run() {
        try {
            Robot robot = new Robot();
            robot.delay(2000);
            BufferedImage image = robot.createScreenCapture(rect);
            ImageIO.write(image, "png", new File("C:/Users/cwong/IdeaProjects/RuruBot/src/screenies", "screen.png"));

            long endTime = System.currentTimeMillis() + 30000;


            while (true) {
                // Break out after timer.
                if (System.currentTimeMillis() > endTime) {
                    break;
                }
                BufferedImage img = robot.createScreenCapture(rect);
                WritableRaster raster = img.getRaster();
                DataBuffer db = raster.getDataBuffer();
                DataBufferInt dbi = (DataBufferInt)db;
                int [] data = dbi.getData();
                for (int x_scale = 0; x_scale < rect.width; x_scale += 3) {
                    for (int y_scale = 0; y_scale < rect.height; y_scale += 3) {
                        int rgb = data[x_scale + rect.width * y_scale];
                        // Found a box
                        if (rgb == WHITE) {
                            robot.mouseMove(rect.x + x_scale, rect.y + y_scale);
                            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



//    static int gameX = 532;
//    static int gameY = 265;
    static int gameX = 1280;
    static int gameY = 575;
    static int gameWidth = 90;
    static int gameHeight = 69;


    public static void main(String[] args) {
        new Jump(gameX, gameY, gameWidth, gameHeight).run();
    }
}


