package com.zeus.bookcase.app.cabinet.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.home.data.BookCaseDBHelper;
import com.zeus.bookcase.app.cabinet.model.MyBook;
import com.zeus.bookcase.app.user.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zeus_coder on 2016/3/31.
 */
public class BookDao {

    private static final String TAG = "BookDao";
    private static final String TABLE_BOOK = "Book";

    // 列定义
    private final String ID = "id";
    private final String UID = "uid";
    private final String ISBN = "isbn";
    private final String AUTHOR = "author";
    private final String TITLE = "title";
    private final String PRICE = "price";
    private final String IMAGE = "image";
    private final String SUMMARY = "summary";
    private final String PUBLISHER = "publisher";
    private final String PUBDATE = "pubdate";
    private final String[] BOOK_COLUMNS = new String[] {
            ID,
            UID,
            ISBN,
            AUTHOR,
            TITLE,
            PRICE,
            IMAGE,
            SUMMARY,
            PUBLISHER,
            PUBDATE
    };

    private Context context;
    private BookCaseDBHelper dbHelper;

    public BookDao(Context context) {
        this.context = context;
        dbHelper = new BookCaseDBHelper(context);
    }

    //判断表中是否有数据
    public boolean isDataExist() {
        int count = 0;
        //初始化数据库
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = dbHelper.getReadableDatabase();
            cursor = db.query(BookDao.TABLE_BOOK, new String[]{"COUNT(id)"}, null, null, null, null, null);
            if (cursor.moveToFirst()) {
                count = cursor.getInt(0);
            }
            if (count > 0) return true;
        } catch (Exception e) {
            Log.e(TAG, "", e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return false;
    }

    /**
     * 初始化数据
     */
    public void initTable(){
        SQLiteDatabase db = null;
        try {
            db = dbHelper.getWritableDatabase();
            db.beginTransaction();
            //db.execSQL("insert into " + AddressDao.TABLE_ADDRESS + " (Id, CustomName, OrderPrice, Country) values (1, 'Arc', 100, 'China')");
            db.setTransactionSuccessful();
        }catch (Exception e){
            Log.e(TAG, "", e);
        }finally {
            if (db != null) {
                db.endTransaction();
                db.close();
            }
        }
    }

    /**
     * 执行自定义SQL语句
     */
    public void execSQL(String sql) {
        SQLiteDatabase db = null;

        try {
            if (sql.contains("select")){
                Toast.makeText(context, R.string.strUnableSql, Toast.LENGTH_SHORT).show();
            }else if (sql.contains("insert") || sql.contains("update") || sql.contains("delete")){
                db = dbHelper.getWritableDatabase();
                db.beginTransaction();
                db.execSQL(sql);
                db.setTransactionSuccessful();
                Toast.makeText(context, R.string.strSuccessSql, Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(context, R.string.strErrorSql, Toast.LENGTH_SHORT).show();
            Log.e(TAG, "", e);
        } finally {
            if (db != null) {
                db.endTransaction();
                db.close();
            }
        }
    }


    /**
     * 查询数据库中所有数据
     */
    public List<MyBook> getAllData(User user){
        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            db = dbHelper.getReadableDatabase();
            // select * from Book
            cursor = db.query(BookDao.TABLE_BOOK, BOOK_COLUMNS,
                    UID + " = ?",
                    new String[] {user.getObjectId().toString()},
                    null, null, null);
            if (cursor.getCount() > 0) {
                List<MyBook> bookList = new ArrayList<MyBook>(cursor.getCount());
                while (cursor.moveToNext()) {
                    bookList.add(parseBook(cursor));
                }
                return bookList;
            }
        }
        catch (Exception e) {
            Log.e(TAG, "", e);
        }
        finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }

        return null;
    }

    /**
     * 新增一条数据
     */
    public boolean insertBook(MyBook book){
        SQLiteDatabase db = null;

        try {
            db = dbHelper.getWritableDatabase();
            db.beginTransaction();
            ContentValues contentValues = new ContentValues();
            contentValues.put(ID, book.getId());
            contentValues.put(UID, book.getUid());
            contentValues.put(ISBN, book.getIsbn13());
            contentValues.put(AUTHOR, book.getAuthors());
            contentValues.put(TITLE, book.getTitle());
            contentValues.put(PRICE, book.getPrice());
            contentValues.put(IMAGE, book.getImage());
            contentValues.put(SUMMARY, book.getSummary());
            contentValues.put(PUBLISHER, book.getPublisher());
            contentValues.put(PUBDATE, book.getPubdate());
            db.insertOrThrow(BookDao.TABLE_BOOK, null, contentValues);
            db.setTransactionSuccessful();
            return true;
        }catch (SQLiteConstraintException e){
            Toast.makeText(context, "已经添加过该书籍", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "", e);
        }catch (Exception e){
            Log.e(TAG, "", e);
        }finally {
            if (db != null) {
                db.endTransaction();
                db.close();
            }
        }
        return false;
    }

    /**
     * 删除一条数据
     */
    public boolean deleteBook(MyBook book,User user) {
        SQLiteDatabase db = null;
        try {
            db = dbHelper.getWritableDatabase();
            db.beginTransaction();
            // delete from Orders where Id = ?
            db.delete(BookDao.TABLE_BOOK,
                    ID + " = ?" + " and " + UID + " = ?",
                    new String[]{book.getId().toString(), user.getObjectId().toString()});
            db.setTransactionSuccessful();
            return true;
        } catch (Exception e) {
            Log.e(TAG, "", e);
        } finally {
            if (db != null) {
                db.endTransaction();
                db.close();
            }
        }
        return false;
    }

    /**
     * 修改一条数据
     */
    public boolean updateAddress(MyBook book){
        SQLiteDatabase db = null;
        try {
            db = dbHelper.getWritableDatabase();
            db.beginTransaction();

            // update Orders set OrderPrice = 800 where Id = 6
            ContentValues cv = new ContentValues();
            //cv.put(ADDRESS, address.getAddress().toString());
            db.update(BookDao.TABLE_BOOK,
                    cv,
                    "id = ?",
                    new String[]{book.getId().toString()});
            db.setTransactionSuccessful();
            return true;
        }
        catch (Exception e) {
            Log.e(TAG, "", e);
        }
        finally {
            if (db != null) {
                db.endTransaction();
                db.close();
            }
        }

        return false;
    }

    /**
     * 统计查询
     */
    public int getCount(){
        int count = 0;

        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            db = dbHelper.getReadableDatabase();
            cursor = db.query(BookDao.TABLE_BOOK,
                    new String[]{"COUNT(id)"},
                    null,
                    null,
                    null, null, null);
            if (cursor.moveToFirst()) {
                count = cursor.getInt(0);
            }
        }
        catch (Exception e) {
            Log.e(TAG, "", e);
        }
        finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return count;
    }

    /**
     * 将查找到的数据转换成Book类
     */
    private MyBook parseBook(Cursor cursor) {
        MyBook book = new MyBook();
        book.setId(cursor.getString(cursor.getColumnIndex(ID)));
        book.setUid(cursor.getString(cursor.getColumnIndex(UID)));
        book.setIsbn13(cursor.getString(cursor.getColumnIndex(ISBN)));
        book.setAuthors(cursor.getString(cursor.getColumnIndex(AUTHOR)));
        book.setTitle(cursor.getString(cursor.getColumnIndex(TITLE)));
        book.setPrice(cursor.getString(cursor.getColumnIndex(PRICE)));
        book.setImage(cursor.getString(cursor.getColumnIndex(IMAGE)));
        book.setSummary(cursor.getString(cursor.getColumnIndex(SUMMARY)));
        book.setPublisher(cursor.getString(cursor.getColumnIndex(PUBLISHER)));
        book.setPubdate(cursor.getString(cursor.getColumnIndex(PUBDATE)));
        return book;
    }

}
