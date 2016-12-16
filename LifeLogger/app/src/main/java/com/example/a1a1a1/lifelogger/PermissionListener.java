package com.example.a1a1a1.lifelogger;

import java.util.ArrayList;

/**
 * Created by 1a1a1 on 2016-11-25.
 */
public interface PermissionListener {

    void onPermissionGranted();

    void onPermissionDenied(ArrayList<String> deniedPermissions);

}