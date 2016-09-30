package com.android.lf.lroid.p;

import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.android.lf.lroid.m.bean.PhotoBean;
import com.android.lf.lroid.m.bean.PhotoFolderBean;
import com.android.lf.lroid.utils.ImageUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.Adler32;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by feng on 2016/9/27.
 */

public class LoadPhotoPresenter extends BasePresenter {

    public static final String ALL_PICTURE = "all_picture";

    @Override
    protected <T, R> R doSomething(int requestID,T[] ts) {
        if (requestID == 0x1) {
            return (R) ImageUtils.getSaveImage(getContext(), (String) ts[0]);
        }else if (requestID == 0x0){
            try {
                Map<String, PhotoFolderBean> folderBeanMap = new HashMap<String, PhotoFolderBean>();
                String allPhotosKey = ALL_PICTURE;
                PhotoFolderBean allFolder = new PhotoFolderBean();
                allFolder.setName(allPhotosKey);
                allFolder.setDirPath(allPhotosKey);
                allFolder.setPhotoList(new ArrayList<PhotoBean>());
                folderBeanMap.put(allPhotosKey, allFolder);
                Cursor mCursor = getContext().getContentResolver().query((Uri) ts[0], null,
                        MediaStore.Images.Media.MIME_TYPE + " in(?, ?)",
                        new String[]{"image/jpeg", "image/png"},
                        MediaStore.Images.Media.DATE_MODIFIED + " desc");
                if (mCursor != null) {
                    int pathIndex = mCursor.getColumnIndex(MediaStore.Images.Media.DATA);
                    for (mCursor.moveToFirst(); !mCursor.isAfterLast(); mCursor.moveToNext()) {
                        String path = mCursor.getString(pathIndex);
                        File parentFile = new File(path).getParentFile();
                        if (parentFile == null) {
                            continue;
                        }
                        String dirPath = parentFile.getAbsolutePath();
                        if (folderBeanMap.containsKey(dirPath)) {
                            PhotoBean photo = new PhotoBean(path);
                            PhotoFolderBean photoFolderBean = folderBeanMap.get(dirPath);
                            photoFolderBean.getPhotoList().add(photo);
                            folderBeanMap.get(allPhotosKey).getPhotoList().add(photo);
                            continue;
                        }
                        PhotoFolderBean photoFolder = new PhotoFolderBean();
                        ArrayList<PhotoBean> photoList = new ArrayList<PhotoBean>();
                        PhotoBean photo = new PhotoBean(path);
                        photoList.add(photo);
                        photoFolder.setPhotoList(photoList);
                        photoFolder.setDirPath(dirPath);
                        photoFolder.setName(dirPath.substring(dirPath.lastIndexOf(File.separator) + 1, dirPath.length()));
                        folderBeanMap.put(dirPath, photoFolder);
                        folderBeanMap.get(allPhotosKey).getPhotoList().add(photo);
                    }
                    mCursor.close();
                    return (R) folderBeanMap;
                }
            } catch (Exception e) {
                return null;
            }
            return null;
        }
        return null;
    }
}
