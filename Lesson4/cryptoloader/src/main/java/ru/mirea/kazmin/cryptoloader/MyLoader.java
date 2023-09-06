package ru.mirea.kazmin.cryptoloader;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.loader.content.AsyncTaskLoader;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class MyLoader extends AsyncTaskLoader<String> {
    public static final String ARG_WORD = "word";
    private String decrMess;


    public MyLoader(@NonNull Context context, Bundle args)
    {
        super(context);
        if (args != null)
        {
            byte[] cryptText = args.getByteArray(ARG_WORD);
            byte[] key = args.getByteArray("key");
            SecretKey originalKey = new SecretKeySpec(key, 0, key.length, "AES");
            decrMess = decryptMsg(cryptText, originalKey);
            Log.d("Loader:", "desryptText"+decrMess);
        }

    }
    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
    @Override
    public String loadInBackground() {
        SystemClock.sleep(5000);
        return decrMess;
    }
    public  String decryptMsg(byte[] cipherText, SecretKey secret){
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secret);
            return new String(cipher.doFinal(cipherText));
        }
        catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException |
               BadPaddingException | InvalidKeyException e) {
            throw new RuntimeException(e); }
    }
}