package com.zeus.bookcase.app.home.model;

import java.io.Serializable;
import java.util.List;

import cn.bmob.v3.BmobObject;

/**
 * Created by zeus_coder on 2016/3/23.
 */
public class BookList extends BmobObject implements Serializable {

    private String title;
    private Integer number;
//    private List<Book> books;
    private List<String> ids;
    private List<String> urls;

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

//    public List<Book> getBooks() {
//        return (ArrayList)books;
//    }
//
//    public void setBooks(List<Book> books) {
//        this.books = books;
//    }
}
