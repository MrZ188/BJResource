package com.example.zhupan.myresource.utils;

import android.content.Context;
import android.os.Environment;

import com.example.zhupan.myresource.config.Constants;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class SdUtil {
    public static void saveToLocal(Context context,String content) {
        try {
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {     //sd卡挂载
                File dirs = new File(Constants.SD_CARD_ROOT + "zhupan");
                if (!dirs.exists()) {
                    dirs.mkdir();
                }
                File saveFile = new File(dirs, "tenBeauties");
                if(!saveFile.exists()){
                    saveFile.createNewFile();
                }
                FileOutputStream outputStream =new  FileOutputStream(saveFile);
                OutputStreamWriter writer = new OutputStreamWriter(outputStream,"gb2312");
                writer.write(content);
                writer.write("/n");
                writer.flush();
                writer.close();
                outputStream.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
