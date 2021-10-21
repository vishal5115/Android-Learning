package com.securitymyapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Base64;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class MainActivity extends AppCompatActivity {

    String AES = "AES";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private String decrypt(String outputString, String  password) throws Exception{
        SecretKeySpec key = generateKey(password);
        Cipher c = Cipher.getInstance(AES);
        c.init(Cipher.DECRYPT_MODE ,key);
        byte[] decodeValue = Base64.decode(outputString,android.util.Base64.DEFAULT);
        byte[] decValue = c.doFinal(decodeValue);
        String decrpteValue = new String(decValue);
        return decrpteValue;

    }


    private String encrypt(String data, String password) throws Exception{
        SecretKeySpec key = generateKey(password);
        Cipher c = Cipher.getInstance(AES);
        c.init(Cipher.ENCRYPT_MODE,key);
        byte[] encValue = c.doFinal(data.getBytes());
        String encryptedValue = Base64.encodeToString(encValue, android.util.Base64.DEFAULT);
        return encryptedValue;
    }

    private SecretKeySpec generateKey(String password) throws Exception{
        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = password.getBytes("UTF-8");
        digest.update(bytes,0,bytes.length);
        byte[] key = digest.digest();
        SecretKeySpec secretKeySpec = new SecretKeySpec(key,"AES");
        return secretKeySpec;
    }
}