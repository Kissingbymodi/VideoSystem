package com.hu.video.util;

import java.io.File;

public class FileUtil {


    public void deleteAllFilesOfDir(File file) {
        if (null != file) {
            if (!file.exists())
                return;
            if (file.isFile()) {
                boolean result = file.delete();
                int tryCount = 0;
                while (!result && tryCount++ < 10) {
                    System.gc(); // 回收资源
                    result = file.delete();
                }
            }
            File[] files = file.listFiles();
            if (null != files) {
                for (int i = 0; i < files.length; i++) {
                    deleteAllFilesOfDir(files[i]);
                }
            }
            file.delete();
        }
    }
    public File getFile(String path) {
        return new File(path);
    }
}
