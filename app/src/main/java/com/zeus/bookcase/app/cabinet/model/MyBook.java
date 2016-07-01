package com.zeus.bookcase.app.cabinet.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zeus_coder on 2016/3/31.
 */
public class MyBook implements Serializable {

    //拥有者
    private String uid;
    //图书作者
    private List<String> author;
    //图书作者
    private String authors;
    //出版时间
    private String pubdate;
    //图书图片
    private String image;
    //图书ID
    private String id;
    //图书出版社
    private String publisher;
    //图书ISBN码
    private String isbn13;
    //图书标题
    private String title;
    //图书摘要
    private String summary;
    //图书价格
    private String price;
    //交易状态
    private String state;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public List<String> getAuthor() {
        return author;
    }

    public void setAuthor(List<String> author) {
        this.author = author;
    }

    public String getAuthors() {
        authors = "";
        for (String writer:getAuthor()) {
            authors = authors + " " + writer;
        }
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
