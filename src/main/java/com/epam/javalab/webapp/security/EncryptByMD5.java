package com.epam.javalab.webapp.security;

import org.apache.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptByMD5 {
    private static MessageDigest md;
    private static final Logger LOGGER = Logger.getLogger(EncryptByMD5.class);

    public static String encrypt(String pass, String name){

        try {
            md = MessageDigest.getInstance("MD5");
            byte[] passBytes = pass.getBytes();
            byte[] nameSaltBytes = name.getBytes();
            md.reset();
            md.update(nameSaltBytes);
            byte[] digested = md.digest(passBytes);
            StringBuffer sb = new StringBuffer();
            for(int i=0;i<digested.length;i++){
                sb.append(Integer.toHexString(0xff & digested[i]));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            LOGGER.error("No such Algorithm",ex);
        }
        return null;


    }
}
