package com.bob.flicklist.Model;

import java.util.ArrayList;

public class Photos {

    public ArrayList<Photo> photo;
    public String pages;
    public int page;
    public int perpage;
    public String total;

    public Photos(ArrayList<Photo> photo, String pages, int page, int perpage, String total) {
        this.photo = photo;
        this.pages = pages;
        this.page = page;
        this.perpage = perpage;
        this.total = total;
    }

    public ArrayList<Photo> getPhoto() {
        return photo;
    }

    public void setPhoto(ArrayList<Photo> photo) {
        this.photo = photo;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPerpage() {
        return perpage;
    }

    public void setPerpage(int perpage) {
        this.perpage = perpage;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
