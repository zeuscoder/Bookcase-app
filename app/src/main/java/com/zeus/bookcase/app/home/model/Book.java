package com.zeus.bookcase.app.home.model;

import java.io.Serializable;

/**
 * Created by zeus_coder on 2016/2/6.
 */
public class Book implements Serializable {

    //图书ID
    private String id;
    //图书标题
    private String Title;
    //图书作者
    private String Author;
    //作者信息
    private String AuthorInfo;
    //图书出版社
    private String Publisher;
    //出版时间
    private String PublishDate;
    //图书ISBN码
    private String ISBN;
    //图书价格
    private String Price;
    //图书页数
    private String Page;
    //图书评分
    private double Rate;
    //图书标签
    private String Tag;
    //图书目录
    private String Content;
    //图书摘要
    private String Summary;
    //图书图片
    private String Bitmap;
    //图书评论数量
    private int ReviewCount;

    private String Url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getAuthorInfo() {
        return AuthorInfo;
    }

    public void setAuthorInfo(String authorInfo) {
        AuthorInfo = authorInfo;
    }

    public String getPublisher() {
        return Publisher;
    }

    public void setPublisher(String publisher) {
        Publisher = publisher;
    }

    public String getPublishDate() {
        return PublishDate;
    }

    public void setPublishDate(String publishDate) {
        PublishDate = publishDate;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getPage() {
        return Page;
    }

    public void setPage(String page) {
        Page = page;
    }

    public double getRate() {
        return Rate;
    }

    public void setRate(double rate) {
        Rate = rate;
    }

    public String getTag() {
        return Tag;
    }

    public void setTag(String tag) {
        Tag = tag;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getBitmap() {
        return Bitmap;
    }

    public void setBitmap(String bitmap) {
        Bitmap = bitmap;
    }

    public String getSummary() {
        return Summary;
    }

    public void setSummary(String summary) {
        Summary = summary;
    }

    public int getReviewCount() {
        return ReviewCount;
    }

    public void setReviewCount(int reviewCount) {
        ReviewCount = reviewCount;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
