package com.zeus.bookcase.app.home.model.annotation;

import com.google.gson.annotations.SerializedName;
import com.zeus.bookcase.app.home.model.book.Book;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * Created by zeus_coder on 2016/4/3.
 */
public class Annotations extends BmobObject implements Serializable {

    private String chapter;

    private Book book;

    private Author_user author_user;

    private int privacy;

    private String abstract_photo;

    @SerializedName("abstract")
    private String abstracts;

    private String summary;

    private String content;

    private int last_photo;

    private int comments_count;

    private boolean hasmath;

    private String book_id;

    private String time;

    private String author_id;

    private String id;

    private int page_no;

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Author_user getAuthor_user() {
        return author_user;
    }

    public void setAuthor_user(Author_user author_user) {
        this.author_user = author_user;
    }

    public int getPrivacy() {
        return privacy;
    }

    public void setPrivacy(int privacy) {
        this.privacy = privacy;
    }

    public String getAbstract_photo() {
        return abstract_photo;
    }

    public void setAbstract_photo(String abstract_photo) {
        this.abstract_photo = abstract_photo;
    }

    public String getAbstracts() {
        return abstracts;
    }

    public void setAbstracts(String abstracts) {
        this.abstracts = abstracts;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLast_photo() {
        return last_photo;
    }

    public void setLast_photo(int last_photo) {
        this.last_photo = last_photo;
    }

    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public boolean isHasmath() {
        return hasmath;
    }

    public void setHasmath(boolean hasmath) {
        this.hasmath = hasmath;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(String author_id) {
        this.author_id = author_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPage_no() {
        return page_no;
    }

    public void setPage_no(int page_no) {
        this.page_no = page_no;
    }
}
