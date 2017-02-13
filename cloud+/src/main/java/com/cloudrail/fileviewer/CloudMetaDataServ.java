package com.cloudrail.fileviewer;

import com.cloudrail.si.types.CloudMetaData;
import com.cloudrail.si.types.SandboxObject;

/**
 * Created by PM on 11/8/2016.
 * It fetches the information of files fetched from the cloud
 */
public class CloudMetaDataServ extends SandboxObject {
    private int service;

    private String path;
    private String name;
    private int size;
    private boolean folder;

    public CloudMetaDataServ() {

    }

    public void setService(int service) {
        this.service = service;
    }

    public int getService() {
        return service;
    }


    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean getFolder() {
        return this.folder;
    }

    public void setFolder(boolean folder) {
        this.folder = folder;
    }

    public String toString() {
        String s = "";
        s = s + "name -> \'" + this.name + "\'\n";
        s = s + "path -> \'" + this.path + "\'\n";
        s = s + "size -> \'" + this.size + "\'\n";
        s = s + "folder -> \'" + this.folder + "\'";
        return s;
    }

    public boolean equals(Object o) {
        if(this == o) {
            return true;
        } else if(!(o instanceof CloudMetaData)) {
            return false;
        } else {
            CloudMetaDataServ metaData = (CloudMetaDataServ)o;
            return this.size != metaData.size?false:(this.folder != metaData.folder?false:(!this.path.equals(metaData.path)?false:this.name.equals(metaData.name)));
        }
    }

    public int hashCode() {
        int result = this.path.hashCode();
        result = 31 * result + this.name.hashCode();
        result = 31 * result + this.size;
        result = 31 * result + (this.folder?1:0);
        return result;
    }

}
