package zhwb.study.encrypt;

import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

public class trial {
   public static void main(String[] arg) throws Exception {

   // Scanner to read the user's password. The Java cryptography
   // architecture points out that strong passwords in strings is a
   // bad idea, but we'll let it go for this assignment.
   Scanner scanner = new Scanner(System.in);
   // Arbitrary salt data, used to make guessing attacks against the
   // password more difficult to pull off.
   byte[] salt = { (byte) 0xc7, (byte) 0x73, (byte) 0x21, (byte) 0x8c,
           (byte) 0x7e, (byte) 0xc8, (byte) 0xee, (byte) 0x99 };

   {
     File inputFile = new File("pic.jpg");
      BufferedImage input = ImageIO.read(inputFile);
//      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
      SecretKeyFactory keyFac = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
     // Get a password from the user.
     System.out.print("Password: ");
     System.out.flush();
     PBEKeySpec pbeKeySpec = new PBEKeySpec(scanner.nextLine().toCharArray());
     // Set up other parameters to be used by the password-based
     // encryption.
     PBEParameterSpec pbeParamSpec = new PBEParameterSpec(salt, 20);
     SecretKey pbeKey = keyFac.generateSecret(pbeKeySpec);
     // Make a PBE Cyhper object and initialize it to encrypt using
     // the given password.
     Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
     pbeCipher.init(Cipher.ENCRYPT_MODE, pbeKey, pbeParamSpec);
     FileOutputStream output = new FileOutputStream("sheepTest.png");
     CipherOutputStream cos = new CipherOutputStream(
            output, pbeCipher);
       //File outputFile = new File("image.png");
         ImageIO.write(input,"PNG",cos);
      cos.close();          

   }
   // Now, create a Cipher object to decrypt for us. We are repeating
   // some of the same code here to illustrate how java applications on
   // two different hosts could set up compatible encryption/decryption
   // mechanisms.
  {
       File inputFile = new File("sheepTest.png");
         BufferedImage input = ImageIO.read(inputFile);
       // Get another (hopefully the same) password from the user.
      System.out.print("Decryption Password: ");
       System.out.flush();
       PBEKeySpec pbeKeySpec = new PBEKeySpec(scanner.next().toCharArray());
       // Set up other parameters to be used by the password-based
       // encryption.
       PBEParameterSpec pbeParamSpec = new PBEParameterSpec(salt, 20);
       SecretKeyFactory keyFac = SecretKeyFactory
               .getInstance("PBEWithMD5AndDES");
       SecretKey pbeKey = keyFac.generateSecret(pbeKeySpec);
       // Make a PBE Cyper object and initialize it to decrypt using
       // the given password.
       Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
       pbeCipher.init(Cipher.DECRYPT_MODE, pbeKey, pbeParamSpec);
       // Decrypt the ciphertext and then print it out.
       /*byte[] cleartext = pbeCipher.doFinal(ciphertext);
       System.out.println(new String(cleartext));*/
      FileOutputStream output = new FileOutputStream("sheepTest.png");
      CipherOutputStream cos = new CipherOutputStream(output, pbeCipher);
      ImageIO.write(input, "PNG", cos);
      cos.close();

   }
   }
}