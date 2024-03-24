package com.d205.foorrng.article;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class CryptoUtil {
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private static final byte[] KEY = "MySuperSecretKey".getBytes(); // Use a secure way to generate/store key

    public static String encrypt(String value) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM); //AES 알고리즘을 사용하고 사이즈는 128 로 들어간다.
        keyGenerator.init(128);

        SecretKey secretKey = new SecretKeySpec(KEY, ALGORITHM); //시크릿 키에 대해 생성자를 생성한다.
        //이 키는 보안되어야 한다.

        Cipher cipher = Cipher.getInstance(TRANSFORMATION); //
        byte[] iv = new byte[cipher.getBlockSize()];
        new SecureRandom().nextBytes(iv); //iv 배열을 채우는 임의이ㅢ 바이트 시퀀스를 생성하낟.
        IvParameterSpec ivParams = new IvParameterSpec(iv); //동일 텍스트에 대해 동일한 암호문으로 암호화되지 않는다.
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParams);

        byte[] encryptedByteValue = cipher.doFinal(value.getBytes("utf-8"));
        byte[] encryptedValueWithIv = new byte[iv.length + encryptedByteValue.length];
        System.arraycopy(iv, 0, encryptedValueWithIv, 0, iv.length);//앞쪽엔 IV를
        System.arraycopy(encryptedByteValue, 0, encryptedValueWithIv, iv.length, encryptedByteValue.length);//뒤쪽엔 안호화된 value를 붙인디.
        //최종결과인 encryptedValueWithIv를 리턴한다.
        return Base64.getEncoder().encodeToString(encryptedValueWithIv);
    }

    public static String decrypt(String value) throws Exception {

        byte[] decodedValue = Base64.getDecoder().decode(value);
        SecretKey secretKey = new SecretKeySpec(KEY, ALGORITHM);

        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        byte[] iv = new byte[cipher.getBlockSize()];
        System.arraycopy(decodedValue, 0, iv, 0, iv.length);
        IvParameterSpec ivParams = new IvParameterSpec(iv);

        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParams);
        byte[] decryptedValue = cipher.doFinal(decodedValue, iv.length, decodedValue.length - iv.length);

        return new String(decryptedValue, "utf-8");
    }
//    private static final String ALGORITHM = "AES";
//    private static final byte[] KEY = "MySuperSecretKey".getBytes(); // Use a secure way to generate/store key
//
//    public static String encrypt(String value) throws Exception {
//        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
//        keyGenerator.init(128);
//        SecretKey secretKey = new SecretKeySpec(KEY, ALGORITHM);
//
//        Cipher cipher = Cipher.getInstance(ALGORITHM);
//        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
//
//        byte[] encryptedByteValue = cipher.doFinal(value.getBytes("utf-8"));
//        return Base64.getEncoder().encodeToString(encryptedByteValue);
//    }
//
//    public static String decrypt(String value) throws Exception {
//        SecretKey secretKey = new SecretKeySpec(KEY, ALGORITHM);
//
//        Cipher cipher = Cipher.getInstance(ALGORITHM);
//        cipher.init(Cipher.DECRYPT_MODE, secretKey);
//
//        byte[] decryptedValue64 = Base64.getDecoder().decode(value);
//        byte[] decryptedByteValue = cipher.doFinal(decryptedValue64);
//        return new String(decryptedByteValue, "utf-8");
//    }
}
