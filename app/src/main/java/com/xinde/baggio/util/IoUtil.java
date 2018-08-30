package com.xinde.baggio.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;

/**
 * author: Shawn
 * time  : 2018/6/25 15:24
 * desc  :
 * update: Shawn 2018/6/25 15:24
 */
public class IoUtil {
    private static final String TAG = "IoUtil";

    public static void writeFile(String absPath, String content) {
        try {
            File file = new File(absPath);
            // 要先将已有文件删除、避免干扰。
            if (file.exists()) file.delete();

            file.getParentFile().mkdirs();
            file.createNewFile();

            RandomAccessFile raf = new RandomAccessFile(file, "rwd");
            raf.seek(file.length());
            raf.write(content.getBytes());
            raf.close();
        } catch (Exception e) {
            Log.e(TAG, "writeFile: ", e);
        }
    }


    public static String readFile(String fileName) {
        String encoding = "UTF-8";
        File file = new File(fileName);
        Long fileLength = file.length();
        byte[] fileContent = new byte[fileLength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(fileContent);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            return new String(fileContent, encoding);
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, "readFile: ", e);
            return "";
        }
    }


    public static void copyFileFromAssert(Context context, String srcFile, String destPath) {
        AssetManager asset = context.getAssets();
        FileOutputStream out = null;
        InputStream in = null;
        try {
            File path = new File(destPath);
            if (!path.exists()) path.mkdirs();

            File f = new File(destPath, srcFile);
            if (f.exists()) f.delete();

            f.createNewFile();
            // 将内容写入到文件中
            in = asset.open(srcFile);
            out = new FileOutputStream(f);

            byte[] buffer = new byte[1024];
            int byteCount;
            while ((byteCount = in.read(buffer)) != -1) {
                out.write(buffer, 0, byteCount);
            }
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) out.close();
                if (in != null) in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
