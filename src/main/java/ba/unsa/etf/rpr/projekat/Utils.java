package ba.unsa.etf.rpr.projekat;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import javax.imageio.ImageIO;
import javax.swing.plaf.synth.SynthTextAreaUI;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

public class Utils {

    public static byte[] getByteArrayFromImage(Image image) {
        if (image == null)
            return null;
        int h = (int)image.getHeight();
        int w = (int)image.getWidth();
        byte[] buf = new byte[w * h * 4];

        image.getPixelReader().getPixels(0, 0, w, h, PixelFormat.getByteBgraInstance(), buf, 0, w*4);
        return buf;
    }

    public static int[] toIntArray(byte[] buf) {
        int[] intArr = new int[buf.length / 4];
        int offset = 0;
        for (int i = 0; i < intArr.length; i++) {
            intArr[i] = (buf[3 + offset] & 0xFF) | ((buf[2 + offset] & 0xFF) << 8)
                    | ((buf[1 + offset] & 0xFF) << 16) | ((buf[0 + offset] & 0xFF) << 24);
            offset += 4;
        }
        return intArr;
    }

    public static Image getImageFromByteArray(byte[] buf) {
        int width = 150;
        int height = 150;

        int[] temp = toIntArray(buf);
        WritableImage img = new WritableImage(width, height);

        PixelWriter pw = img.getPixelWriter();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (temp[x*width + y] == 0)
                    pw.setColor(x, y, Color.WHITE);
                else
                    pw.setColor(x,y, Color.BLACK);
            }
        }

        return img;
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

    public static void saveToFile(Image image) {
        File outputFile = new File("qrcode.png");
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        try {
            ImageIO.write(bImage, "png", outputFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
