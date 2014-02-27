package zhwb.study.image;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * utils for zip image files
 *
 * @author dan.shan
 * @since 2014-02-25 2:37 PM
 */
public class ImageZipUtils {

    public static void main(String[] args) throws IOException {
        InputStream inputStream = new FileInputStream("img.jpg");
        byte[] afterZip = zipImageFile(inputStream, 460, 320, 1.0F);
        OutputStream outputStream = new FileOutputStream("img_zip.jpg");
        outputStream.write(afterZip);
        outputStream.close();
    }

    /**
     * 等比例压缩图片文件<br> 先保存原文件，再压缩、上传
     *
     * @param inputStream 要进行压缩的文件
     * @param width       宽度 //设置宽度时（高度传入0，等比例缩放）
     * @param height      高度 //设置高度时（宽度传入0，等比例缩放）
     * @param quality     质量 0.0~1.0, 1.0质量最好
     * @return 返回压缩后的文件的全路径
     */
    public static byte[] zipImageFile(InputStream inputStream, int width, int height, float quality) {
        if (inputStream == null) {
            return null;
        }
        byte[] result = null;
        try {
            /** 对服务器上的临时文件进行处理 */
            Image srcFile = ImageIO.read(inputStream);
            int w = srcFile.getWidth(null);
            int h = srcFile.getHeight(null);
            double bili;
            if (width > 0) {
                bili = width / (double) w;
                height = (int) (h * bili);
            } else {
                if (height > 0) {
                    bili = height / (double) h;
                    width = (int) (w * bili);
                }
            }
            /** 宽,高设定 */
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            tag.getGraphics().drawImage(srcFile, 0, 0, width, height, null);

            /** 压缩之后临时存放位置 */
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);
            /** 压缩质量 */
            jep.setQuality(quality, true);
            encoder.encode(tag, jep);
            result = out.toByteArray();
            out.close();

        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }

        return result;
    }

}
