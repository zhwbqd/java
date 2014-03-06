package zhwb.study.image;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * The type Image zip utils.
 *
 * @author jack.zhang
 */
public abstract class ImageZipUtilsInterface {
    public static void main(String[] args) throws IOException {
        FileOutputStream outputStream = new FileOutputStream("img_zip.jpg");
        outputStream.write(ImageZipUtilsInterface.createThumbnail(new FileInputStream("img.jpg"), 480, 667));
        outputStream.close();
    }

    /**
     * 创建图片缩略图(等比缩放)
     *
     * @param inputStream the input stream
     * @param width       缩放的宽度
     * @param height      缩放的高度
     * @return the byte [ ]
     * @throws java.io.IOException the iO exception
     */
    public static byte[] createThumbnail(InputStream inputStream, float width, float height) throws IOException {
        if (inputStream == null) {
            throw new IllegalArgumentException("cannot createThumbnail if input is null");
        }
        BufferedImage image = ImageIO.read(inputStream);
        // 获得缩放的比例
        double ratio = 1.0;
        // 判断如果高、宽都不大于设定值，则不处理
        if (image.getHeight() > height || image.getWidth() > width) {
            if (image.getHeight() > image.getWidth()) {
                ratio = height / image.getHeight();
            } else {
                ratio = width / image.getWidth();
            }
        }
        // 计算新的图面宽度和高度
        int newWidth = (int) (image.getWidth() * ratio);
        int newHeight = (int) (image.getHeight() * ratio);
        BufferedImage bfImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
        bfImage.getGraphics().drawImage(
                image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        encoder.encode(bfImage);
        byte[] result = out.toByteArray();
        out.close();
        return result;
    }

}