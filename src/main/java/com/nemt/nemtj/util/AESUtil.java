package com.nemt.nemtj.util;


import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.Provider;
import java.security.spec.AlgorithmParameterSpec;


/**
 * AES对数据加密后 再base64 加密成字符串
 */
@Service
public class AESUtil {

    private static final Provider provider = new BouncyCastleProvider();

    private static final String AES = "AES";

    private static final String ALGORITHM = "AES/ECB/PKCS5Padding";

    private static final String CHARSET = "UTF-8";

    private static byte[] key = "1234567890123456".getBytes();

    private static byte[] iv = null;


    private AESUtil() {
    }

    private static Key toKey() {
        return new SecretKeySpec(key, AES);
    }

    private static AlgorithmParameterSpec toIV() {
        return new IvParameterSpec(iv);
    }

    public static void main(String[] args) {
        String content1 = "{\"license\":{\"issued_to\":\"a\",\"signature\":\"AAAAAwAAAA2jcRBX42JRmsUAfM87AAABmC9ZN0hjZDBGYnVyRXpCOW5Bb3FjZDAxOWpSbTVoMVZwUzRxVk1PSmkxaks3QTRDL3pRRDU3WHRJeHUvbElOakpzK3FqNXcra0FPc0lnMzY2UWhTK01iR25xTTRGQW03Zm4rT2NGdWVEQXd2TjFCZTR0bVpvanNxWTg4MmtaVHZaVE9wUWk3OVBrVXZ3Nnhla3BianpDWXY1Qmo5dThXMmlKNzIyNWRHU1dSN0NHRVluN2VMS3pFdUl3QXdZeU5DZlRBZSt5RkxVRytMN0NwK2VkdENraE9jNHNGRTZTcDk2TWYwRFM4TXFqQ2NKTFlJU2ovbXgwVVhsQVo3bTMxVmdmTFk4UVh6OXJTRmp1U1FiS1hSNWxLZ0IvYTJvcFpkU3VkYjh5L0dDdzV4dWxQbG1GQ1MxSUNodFRaY1cxM056dnZ0YURFallKNS9nZkZWTEIvT2ExYlFleXNjcll1djJ0Rmp6VVdVeUVoRVBUTElZQ1l0VG5LTzZxUEp1MzBkV25ObUZJU3VXYWFlcXBkS2ZLNkx1Y3c9PQAAAQBT0RIB2Vl2dCVDFPMOgCI/glVIOMxXESk2sERkb0QtLGRfFBtXAdLIMp8KFfi+ckN8EiWqX/bTN21HXXemKSwRQwwUfU4H+BbPPuh2cPDQCRYxOXZXp2lwinJ/kgVZ0fXQQjsCZP5VvMBjOu4hZJPJvNibr3MM1kVdPjT23QjfuKr9nP0NCTYtUEKts6bkiuxyONYFu5R80sICGDmYQAGi8ZQhjsHAScE2ktcRG6gXvB4MJC9/wdZQHX4R0m440Nn9K1OHvHAnZycLMWX+s/SpgkdhIQWO07R3BHw3/VYw+22yhfSTfOt2LdIQ7Hx7Myx1bkq0KDbgURoztxEl6Nvt\",\"expiry_date\":1582268768000,\"max_nodes\":100,\"type\":\"platinum\",\"issuer\":\"a\",\"uid\":\"1f742aa9-068b-48b6-8b36-c36ff3a39e17\",\"number\":150,\"issue_date\":1526860800,\"subscription_type\":\"platinum\",\"customer_name\":\"inspur\",\"product_serial\":\"inspur\",\"start_date\":1526860800,\"product_model\":\"inspur\"}}";
        String content  = "{\"license\":{\"issued_to\":\"a\",\"signature\":\"AAAAAwAAAA2xgH9lXD/Al2BX+fpHAAABmC9ZN0hjZDBGYnVyRXpCOW5Bb3FjZDAxOWpSbTVoMVZwUzRxVk1PSmkxaks3QTRDL3pRRDU3WHRJeHUvbElOakpzK3FqNXcra0FPc0lnMzY2UWhTK01iR25xTTRGQW03Zm4rT2NGdWVEQXd2TjFCZTR0bVpvanNxWTg4MmtaVHZaVE9wUWk3OVBrVXZ3Nnhla3BianpDWXY1Qmo5dThXMmlKNzIyNWRHU1dSN0NHRVluN2VMS3pFdUl3QXdZeU5DZlRBZSt5RkxVRytMN0NwK2VkdENraE9jNHNGRTZTcDk2TWYwRFM4TXFqQ2NKTFlJU2ovbXgwVVhsQVo3bTMxVmdmTFk4UVh6OXJTRmp1U1FiS1hSNWxLZ0IvYTJvcFpkU3VkYjh5L0dDdzV4dWxQbG1GQ1MxSUNodFRaY1cxM056dnZ0YURFallKNS9nZkZWTEIvT2ExYlFleXNjcll1djJ0Rmp6VVdVeUVoRVBUTElZQ1l0VG5LTzZxUEp1MzBkV25ObUZJU3VXYWFlcXBkS2ZLNkx1Y3c9PQAAAQBdXApCd3g5uAG/6Q6TrHj/5VsLC0hFcT+8Xojmp0D/4dI8CeoPc8eLoWsXqLP2k2fyBi41dZhjVs5NJg2y4id8BDgmmCiGPYcZhqixWpjpgmc7hRBiQbz3MqOpOso0uYQelnuOzk55nhQjaYoLh1kFWYA3PJ+QkwwWrl3HdJDVN17Ub7LECPwHq62bbXdeRYZr8u4Z/ZGJe9czw0J0tZS5zz+nrElOYU2B1qZa3tUzCEpDmlSyp8SAxmv6OmGElmBT5ptxfMrcMnyaLuCUrmsQP6Mn65Nkg9AQd4od3ursivCx3HxIvT7TgDqYfMmKrg0X+PxIpeDTnFfhJw1qbriA\",\"expiry_date\":4100731932000,\"max_nodes\":100,\"type\":\"platinum\",\"issuer\":\"a\",\"uid\":\"1f742aa9-068b-48b6-8b36-c36ff3a39e17\",\"number\":300,\"issue_date\":1526860800,\"subscription_type\":\"platinum\",\"customer_name\":\"inspur\",\"product_serial\":\"inspur\",\"start_date\":1526860800,\"product_model\":\"inspur\"}}";
        String key="g23a56l8b012345x";
        System.out.print("Original content:");
        System.out.println(content);
        try {
            System.out.print("Encrypted Content:");
            String ret = AESUtil.encrypt(key,content);
            System.out.println(ret);

            System.out.print("Decrypted Content:");
            String ret1 = AESUtil.decrypt(key,ret);
            System.out.println(ret1);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * AES加密，再使用Base64编码
     *
     * @param
     * @return
     * @throws Exception
     */
    public static String encrypt(String key,String content) throws Exception {
        Cipher encryptCipher = Cipher.getInstance(ALGORITHM, provider);
        SecretKeySpec secretKeySpec= new SecretKeySpec(key.getBytes(), AES);
        encryptCipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] enc = encryptCipher.doFinal(content.getBytes(CHARSET));
        return new String(Base64.encode(enc));
    }

    /**
     * Base64解码后，再使用AES解密
     *
     * @param
     * @return
     * @throws Exception
     */
    public static String decrypt(String key,String content) throws Exception {
        Cipher decryptCipher = Cipher.getInstance(ALGORITHM, provider);
        SecretKeySpec secretKeySpec= new SecretKeySpec(key.getBytes(), AES);
        decryptCipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] dec = decryptCipher.doFinal(Base64.decode(content));
        return new String(dec, CHARSET);
    }

//    @Resource
//    public void setSecurityConfig(SecurityConfig securityConfig) {
//        key = securityConfig.getConfigValue("AESKEY").getBytes();
//        iv = securityConfig.getConfigValue("AESIV").getBytes();
//    }
}
