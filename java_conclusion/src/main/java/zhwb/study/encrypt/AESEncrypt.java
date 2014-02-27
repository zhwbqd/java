package zhwb.study.encrypt;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Date: 14-2-27
 * Time: 下午2:30
 *
 * @author jack.zhang
 */
public class AESEncrypt {
    public static void main(String[] args) throws IOException {
        String password = "12345678";
//        encryptTxt(password);
        encryptImg(password);
//        imageBase64Encrypt(password);
    }

    private static String encryptTxt(String password) throws UnsupportedEncodingException {
        String content = "test";

        //加密
        System.out.println("加密前：" + content);
        byte[] byteContent = content.getBytes("utf-8");
        byte[] encryptResult = encrypt(byteContent, password);
        String encryptResultStr = parseByte2HexStr(encryptResult);
        System.out.println("加密后：" + encryptResultStr);
        //解密
        byte[] decryptFrom = parseHexStr2Byte(encryptResultStr);
        byte[] decryptResult = decrypt(decryptFrom, password);
        System.out.println("解密后：" + new String(decryptResult));
        return password;
    }

    private static void imageBase64Encrypt(String password) throws IOException {
        BufferedInputStream inputStream;
        byte[] bytes;
        FileOutputStream outputStream;
        byte[] afterEncrypt;//原图片base64后并输出
        inputStream = new BufferedInputStream(new FileInputStream("img.jpg"));
        bytes = new byte[inputStream.available()];
        inputStream.read(bytes);
        inputStream.close();
        System.out.println("源文件大小: " + bytes.length);
        bytes = Base64.encodeBase64(bytes); //base64
        outputStream = new FileOutputStream("base64.txt");
        outputStream.write(bytes);
        outputStream.close();
        //加密并写入
        afterEncrypt = encrypt(bytes, password);
        outputStream = new FileOutputStream("img_base64_encrypt.txt");
        outputStream.write(afterEncrypt);
        outputStream.close();
        System.out.println("Base64后: " + bytes.length + "加密后" + afterEncrypt.length);

        //解密
        inputStream = new BufferedInputStream(new FileInputStream("img_base64_encrypt.txt"));
        inputStream.read(afterEncrypt);
        inputStream.close();
        bytes = decrypt(afterEncrypt, password);
        bytes = Base64.decodeBase64(bytes);
        outputStream = new FileOutputStream("img_after_base64.jpg");
        outputStream.write(bytes);
        outputStream.close();
    }

    private static void encryptImg(String password) throws IOException {
        //图片加密
        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream("img.jpg"));
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes);
        inputStream.close();
        byte[] afterEncrypt = encrypt(bytes, password);
        FileOutputStream outputStream = new FileOutputStream("img_encrypt.txt");
        outputStream.write(afterEncrypt);
        outputStream.close();
        System.out.println("原大小: " + bytes.length + ", 现有大小: " + afterEncrypt.length);

        inputStream = new BufferedInputStream(new FileInputStream("img_encrypt.txt"));
        inputStream.read(afterEncrypt);
        inputStream.close();
        //解密并输出
        bytes = decrypt(afterEncrypt, password);
        outputStream = new FileOutputStream("img_after.jpg");
        outputStream.write(bytes);
        outputStream.close();
    }

    /**
     * 加密
     *
     * @param content  需要加密的内容
     * @param password 加密密码
     * @return
     */
    public static byte[] encrypt(byte[] content, String password) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new SecureRandom(password.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(content);
            return result; // 加密
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     *
     * @param content  待解密内容
     * @param password 解密密钥
     * @return
     */
    public static byte[] decrypt(byte[] content, String password) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new SecureRandom(password.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(content);
            return result; // 加密
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将二进制转换成16进制
     *
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制转换为二进制
     *
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
}
