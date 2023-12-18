import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class QRCodeGenerator {

    public static void main(String[] args) {
        String logoPath = "l";
        String url = "hmx/";
        String outputPath = "gng";

        generateQRCode(url, logoPath, outputPath);
        System.out.println("QR code generated!");
    }

    private static void generateQRCode(String url, String logoPath, String outputPath) {
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, 300, 300);

            MatrixToImageConfig matrixConfig = new MatrixToImageConfig(0xFF000001, 0xFFFFFFFF);
            BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix, matrixConfig);

            addLogo(qrImage, logoPath);

            ImageIO.write(qrImage, "png", new File(outputPath));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addLogo(BufferedImage qrImage, String logoPath) throws IOException {
        BufferedImage logo = ImageIO.read(new File(logoPath));
        Graphics2D graphics = qrImage.createGraphics();

        int logoWidth = qrImage.getWidth() / 5;
        int logoHeight = qrImage.getHeight() / 5;
        int logoX = (qrImage.getWidth() - logoWidth) / 2;
        int logoY = (qrImage.getHeight() - logoHeight) / 2;

        graphics.drawImage(logo, logoX, logoY, logoWidth, logoHeight, null);
        graphics.dispose();
    }
}
