package com.zeus.bookcase.app.home.data;

import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.home.model.BookList;
import com.zeus.bookcase.app.home.model.Geek;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zeus_coder on 2016/3/24.
 */
public class MockData {

    public static List<BookList> getBookLists() {
        //初始化lists数据
        List<BookList> bookLists = new ArrayList<>();
        String[] listTitles = { "一个人的周末",
                "有你就很好了",
                "咖啡馆的享受" };
        int[] numbers = { 235, 564, 652,};
        String[] urls = { "http://ww2.sinaimg.cn/mw690/af493ad3jw1f28arnw6krj20ox080dgg.jpg",
                "http://ww3.sinaimg.cn/mw690/af493ad3jw1f28arng05sj20ax055wem.jpg",
                "http://ww3.sinaimg.cn/mw690/af493ad3jw1f28armf9ssj20qo0aj0ti.jpg" };
//        Book book = new Book();
//        List<Book> books = new ArrayList<>();
//        for(int i = 0;i < 3;i++) {
//            book.setTitle("全能天才");
//            books.add(book);
//        }
        List<List<String>> urlss = new ArrayList<>();
        String[][] uris = {
                {"https://img3.doubanio.com/mpic/s28351121.jpg", "https://img1.doubanio.com/mpic/s27997453.jpg", "https://img1.doubanio.com/mpic/s6641009.jpg"},
                {"https://img1.doubanio.com/mpic/s28320163.jpg", "https://img3.doubanio.com/mpic/s28293575.jpg", "https://img3.doubanio.com/mpic/s2686916.jpg"},
                {"https://img3.doubanio.com/mpic/s28283341.jpg", "https://img3.doubanio.com/mpic/s27243455.jpg", "https://img1.doubanio.com/mpic/s28327779.jpg"} };
        for (int i = 0; i < 3; i++) {
            List<String> uri = new ArrayList<>();
            uri.add(uris[i][0]);
            uri.add(uris[i][1]);
            uri.add(uris[i][2]);
            urlss.add(uri);
        }
        List<List<String>> idss = new ArrayList<>();
        String[][] ids = {
                {"25942191", "25921329", "6515839"},
                {"26644935", "26609447", "2243615"},
                {"26599538", "2130190", "26259017"} };
        for (int i = 0; i < 3; i++) {
            List<String> id = new ArrayList<>();
            id.add(ids[i][0]);
            id.add(ids[i][1]);
            id.add(ids[i][2]);
            idss.add(id);
        }
        for(int i = 0;i < 3;i++) {
            BookList bookList = new BookList();
            bookList.setTitle(listTitles[i]);
            bookList.setNumber(numbers[i]);
            bookList.setUrl(urls[i]);
//            bookList.setBooks(books);
            bookList.setUrls(urlss.get(i));
            bookList.setIds(idss.get(i));
            bookLists.add(bookList);
        }
        return bookLists;
    }

    public static List<Geek> getGeeks() {
        //初始化geek数据
        List<Geek> geeks = new ArrayList<>();
//        int[] photos = { R.mipmap.app_home_geek_photo,
//                R.mipmap.app_home_geek_photo2,
//                R.mipmap.app_home_geek_photo3,
//                R.mipmap.app_home_geek_photo4,
//                R.mipmap.app_home_geek_photo5,
//                R.mipmap.app_home_geek_photo6 };
        String[] images = {
                "http://ww2.sinaimg.cn/mw690/af493ad3jw1f2og80qn02j205k05k3ye.jpg",
                "http://ww2.sinaimg.cn/mw690/af493ad3jw1f2og815f2kj205k05kjrb.jpg",
                "http://ww2.sinaimg.cn/mw690/af493ad3jw1f2og7zm24dj205k05kdfs.jpg",
                "http://ww4.sinaimg.cn/mw690/af493ad3jw1f2og7zm1psj205k05kglh.jpg",
                "http://ww4.sinaimg.cn/mw690/af493ad3jw1f2og7yvkn8j205k05kglg.jpg",
                "http://ww4.sinaimg.cn/mw690/af493ad3jw1f2og7yhruvj205k05kjr8.jpg"
        };
        String[] names = { "zeus",
                "east",
                "Look",
                "Bricoleur",
                "jimmy",
                "merry" };
        String[] ids = { "00184a30d2",
                "38624b614a",
                "886f9a31e7",
                "261699f5c5",
                "9debdd710b",
                "vWTjKKKe" };
        String[] titles = { "帅气达人",
                "技术达人",
                "运动达人",
                "时尚达人",
                "科技达人",
                "心理达人" };
        for(int i = 0;i < 6;i++) {
            Geek geek = new Geek();
            geek.setName(names[i]);
            geek.setTitle(titles[i]);
            geek.setImage(images[i]);
            geek.setUid(ids[i]);
            geeks.add(geek);
        }
        return geeks;
    }
}
