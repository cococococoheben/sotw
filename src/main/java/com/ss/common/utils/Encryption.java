package com.ss.common.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * 加密
 */
public class Encryption {


    private final static String SALT ="8d78869f470951332959580424d4bf4f";


    /**
     * 密码加密
     * 密码 盐
     * @param password
     * @param salt
     * @return
     */
    public static String md5Hash(String password,String salt){
        Md5Hash md5Hash = new Md5Hash(password,salt,2);
        return md5Hash.toString();
    }

    /**
     * 密码加密 使用默认盐
     * 密码
     * @param password
     * @return
     */
    public static String md5Hash(String password){
        return md5Hash(password,SALT);
    }

    public static void main(String [] ss){

        String password= "123456";
        System.out.println("加密前"+password);
        String s = md5Hash("123456");
        System.out.println("加密后"+s);

    }

    /**
     *  密码校验 使用默认盐
     *  明文 密文
     * @param plaintext 明文
     * @param ciphertext 密文
     * @return
     */
    public static boolean verificationPassword(String plaintext,String ciphertext){
        String s = md5Hash(plaintext, SALT);

        if(s.equals(ciphertext)){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 密码校验
     * 明文 密文 盐
     * @param plaintext
     * @param ciphertext
     * @param salt
     * @return
     */
    public static boolean verificationPassword(String plaintext,String ciphertext,String salt){
        String s = md5Hash(plaintext, salt);

        if(s.equals(ciphertext)){
            return true;
        }else {
            return false;
        }
    }

}
