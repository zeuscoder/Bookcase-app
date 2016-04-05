package com.zeus.bookcase.app.home.model.book;

import java.io.Serializable;
import java.util.List;

import cn.bmob.v3.BmobObject;

/**
 * Created by zeus_coder on 2016/3/26.
 */
public class Book extends BmobObject implements Serializable {

    //图书评分
    private Rating rating;

    private String subtitle;
    //图书作者
    private List<String> author;
    //图书作者
    private String authors;
    //出版时间
    private String pubdate;
    //图书标签
    private List<Tags> tags ;

    private String origin_title;
    //图书图片
    private String image;

    private String binding;
    //图书目录
    private String catalog;
    //图书页数
    private String pages;

    private Images images;

    private String alt;
    //图书ID
    private String id;
    //图书出版社
    private String publisher;
    //图书ISBN码
    private String isbn10;
    //图书ISBN码
    private String isbn13;
    //图书标题
    private String title;

    private String url;

    private String alt_title;
    //作者信息
    private String author_intro;
    //图书摘要
    private String summary;
    //图书价格
    private String price;

    public void setRating(Rating rating){
        this.rating = rating;
    }
    public Rating getRating(){
        return this.rating;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public void setSubtitle(String subtitle){
        this.subtitle = subtitle;
    }
    public String getSubtitle(){
        return this.subtitle;
    }
    public List<String> getAuthor() {
        return author;
    }
    public void setAuthor(List<String> author) {
        this.author = author;
    }
    public void setPubdate(String pubdate){
        this.pubdate = pubdate;
    }
    public String getPubdate(){
        return this.pubdate;
    }
    public void setTags(List<Tags> tags){
        this.tags = tags;
    }
    public List<Tags> getTags(){
        return this.tags;
    }
    public void setOrigin_title(String origin_title){
        this.origin_title = origin_title;
    }
    public String getOrigin_title(){
        return this.origin_title;
    }
    public void setImage(String image){
        this.image = image;
    }
    public String getImage(){
        return this.image;
    }
    public void setBinding(String binding){
        this.binding = binding;
    }
    public String getBinding(){
        return this.binding;
    }
    public void setCatalog(String catalog){
        this.catalog = catalog;
    }
    public String getCatalog(){
        return this.catalog;
    }
    public void setPages(String pages){
        this.pages = pages;
    }
    public String getPages(){
        return this.pages;
    }
    public void setImages(Images images){
        this.images = images;
    }
    public Images getImages(){
        return this.images;
    }
    public void setAlt(String alt){
        this.alt = alt;
    }
    public String getAlt(){
        return this.alt;
    }
    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setPublisher(String publisher){
        this.publisher = publisher;
    }
    public String getPublisher(){
        return this.publisher;
    }
    public void setIsbn10(String isbn10){
        this.isbn10 = isbn10;
    }
    public String getIsbn10(){
        return this.isbn10;
    }
    public void setIsbn13(String isbn13){
        this.isbn13 = isbn13;
    }
    public String getIsbn13(){
        return this.isbn13;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }
    public void setUrl(String url){
        this.url = url;
    }
    public String getUrl(){
        return this.url;
    }
    public void setAlt_title(String alt_title){
        this.alt_title = alt_title;
    }
    public String getAlt_title(){
        return this.alt_title;
    }
    public void setAuthor_intro(String author_intro){
        this.author_intro = author_intro;
    }
    public String getAuthor_intro(){
        return this.author_intro;
    }
    public void setSummary(String summary){
        this.summary = summary;
    }
    public String getSummary(){
        return this.summary;
    }
    public void setPrice(String price){
        this.price = price;
    }
    public String getPrice(){
        return this.price;
    }

}
