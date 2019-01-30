package ba.unsa.etf.rpr.projekat;

import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;

public class Utils {

    public static byte[] getByteArrayFromImage(Image image) {
        int h = (int)image.getHeight();
        int w = (int)image.getWidth();
        byte[] buf = new byte[w * h * 4];

        image.getPixelReader().getPixels(0, 0, w, h, PixelFormat.getByteBgraInstance(), buf, 0, w*4);
        return buf;
    }

}
