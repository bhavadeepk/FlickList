package com.bob.flicklist.Model;

public class FlicksResponse {
    String stat;
    Photos photos;

    public FlicksResponse(String stat, Photos photos) {
        this.stat = stat;
        this.photos = photos;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public Photos getPhotos() {
        return photos;
    }

    public void setPhotos(Photos photos) {
        this.photos = photos;
    }
}
