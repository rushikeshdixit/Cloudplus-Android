package com.cloudrail.fileviewer;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.cloudrail.si.*;
import com.cloudrail.si.exceptions.ParseException;
import com.cloudrail.si.services.Box;
import com.cloudrail.si.interfaces.CloudStorage;
import com.cloudrail.si.services.Dropbox;
import com.cloudrail.si.services.GoogleDrive;
import com.cloudrail.si.services.OneDrive;

import java.util.concurrent.atomic.AtomicReference;

/**
 * This class encapsulates the different services being used by the application. It also initializes
 * them and persists the authentication data.
 *
 *
 */
class Services {
    private final static String CLOUDRAIL_LICENSE_KEY = "<Your CloudRail License Key>";
    private final static Services ourInstance = new Services();

    private final AtomicReference<CloudStorage> dropbox = new AtomicReference<>();
    private final AtomicReference<CloudStorage> box = new AtomicReference<>();
    private final AtomicReference<CloudStorage> googledrive = new AtomicReference<>();
    private final AtomicReference<CloudStorage> onedrive = new AtomicReference<>();

    private Activity context = null;

    static Services getInstance() {
        return ourInstance;
    }

    private Services() {
    }

    private void initDropbox() {
        dropbox.set(new Dropbox(context, "<your key>", "<your secret key>"));
    }

    private void initBox() {
        box.set(new Box(context, "<your key>", "<your secret key>"));
    }

    private void initGoogleDrive() {
        googledrive.set(new GoogleDrive(context, "<your key>", "<your secret key>"));
    }

    private void initOneDrive() {
        onedrive.set(new OneDrive(context, "<your key>", "<your secret key>"));
    }

    // --------- Public Methods -----------
    void prepare(Activity context) {
        this.context = context;



        this.initDropbox();
        this.initBox();
        this.initGoogleDrive();
        this.initOneDrive();

        SharedPreferences sharedPreferences = context.getPreferences(Context.MODE_PRIVATE);

        try {
            String persistent = sharedPreferences.getString("dropboxPersistent", null);
            if (persistent != null) dropbox.get().loadAsString(persistent);
            persistent = sharedPreferences.getString("boxPersistent", null);
            if (persistent != null) box.get().loadAsString(persistent);
            persistent = sharedPreferences.getString("googledrivePersistent", null);
            if (persistent != null) googledrive.get().loadAsString(persistent);
            persistent = sharedPreferences.getString("onedrivePersistent", null);
            if (persistent != null) onedrive.get().loadAsString(persistent);
        } catch (ParseException e) {}
    }

    CloudStorage getService(int service) {
        AtomicReference<CloudStorage> ret = new AtomicReference<>();

        switch (service) {
            case 1:
                ret = this.dropbox;
                break;
            case 2:
                ret = this.box;
                break;
            case 3:
                ret = this.googledrive;
                break;
            case 4:
                ret = this.onedrive;
                break;
            default:
                throw new IllegalArgumentException("Unknown service!");
        }

        return ret.get();
    }
    //For storing token of a service for the user
    void storePersistent() {
        SharedPreferences sharedPreferences = context.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("dropboxPersistent", dropbox.get().saveAsString());
        editor.putString("boxPersistent", box.get().saveAsString());
        editor.putString("googledrivePersistent", googledrive.get().saveAsString());
        editor.putString("onedrivePersistent", onedrive.get().saveAsString());
        editor.apply();
    }
}