package ba.unsa.etf.rpr.projekat;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.awt.image.BufferedImage;

public class Utils {

    public static byte[] getByteArrayFromImage(Image image) {
        int h = (int)image.getHeight();
        int w = (int)image.getWidth();
        byte[] buf = new byte[w * h * 4];

        image.getPixelReader().getPixels(0, 0, w, h, PixelFormat.getByteBgraInstance(), buf, 0, w*4);
        return buf;
    }

    public static WritableImage generateQrCode(String name, String flight) throws WriterException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String encode = name + "|" + flight;
        int width = 150;
        int height = 150;
        BitMatrix byteMatrix = qrCodeWriter.encode(encode, BarcodeFormat.QR_CODE, width, height);
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        bufferedImage.createGraphics();

        WritableImage wr = null;
        if (bufferedImage != null) {
            wr = new WritableImage(bufferedImage.getWidth(), bufferedImage.getHeight());
            PixelWriter pw = wr.getPixelWriter();
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    if (byteMatrix.get(x,y))
                        pw.setColor(x, y, Color.BLACK);
                }
            }
        }
        return wr;
    }

}
