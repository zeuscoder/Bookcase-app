package com.zeus.bookcase.app.home.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.home.data.BookCaseDBHelper;
import com.zeus.bookcase.app.home.model.Cart;
import com.zeus.bookcase.app.home.model.book.Book;
import com.zeus.bookcase.app.user.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zeus_coder on 2016/4/1.
 */
public class CartDao {

    private static final String TAG = "CartDao";
    private static final String TABLE_CART = "Cart";

    // 列定义
    private final String BID = "bid";
    private final String UID = "uid";
    private final String TITLE = "title";
    private final String IMAGE = "image";
    private final String PUBLISHER = "publisher";
    private final String PRICE = "price";
    private final String NUMBER = "number";
    private final String[] CART_COLUMNS = new String[]{
            BID,
            UID,
            TITLE,
            IMAGE,
            PUBLISHER,
            PRICE,
            NUMBER
    };

    private Context context;
    private BookCaseDBHelper dbHelper;

    public CartDao(Context context) {
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
            cursor = db.query(CartDao.TABLE_CART, new String[]{"COUNT(id)"}, null, null, null, null, null);
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
    public List<Cart> getAllData(User user){
        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            db = dbHelper.getReadableDatabase();
            // select * from Book
            cursor = db.query(CartDao.TABLE_CART, CART_COLUMNS,
                    UID + " = ?",
                    new String[] {user.getObjectId().toString()},
                    null, null, null);
            if (cursor.getCount() > 0) {
                List<Cart> cartList = new ArrayList<Cart>(cursor.getCount());
                while (cursor.moveToNext()) {
                    cartList.add(parseCart(cursor));
                }
                return cartList;
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
    public boolean insertCart(Book book,User user, String number){
        SQLiteDatabase db = null;

        try {
            db = dbHelper.getWritableDatabase();
            db.beginTransaction();
            ContentValues contentValues = new ContentValues();
            contentValues.put(BID, book.getId());
            contentValues.put(UID, user.getObjectId());
            contentValues.put(TITLE, book.getTitle());
            contentValues.put(PRICE, book.getPrice());
            contentValues.put(IMAGE, book.getImage());
            contentValues.put(NUMBER, number);
            contentValues.put(PUBLISHER, book.getPublisher());
            db.insertOrThrow(CartDao.TABLE_CART, null, contentValues);
            db.setTransactionSuccessful();
            return true;
        }catch (SQLiteConstraintException e){
            Toast.makeText(context, "已经添加过", Toast.LENGTH_SHORT).show();
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
    public boolean deleteCart(Cart cart) {
        SQLiteDatabase db = null;
        try {
            db = dbHelper.getWritableDatabase();
            db.beginTransaction();
            // delete from Orders where Id = ?
            db.delete(CartDao.TABLE_CART, "bid = ?", new String[]{cart.getBid().toString()});
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
    public boolean updateCart(Cart cart){
        SQLiteDatabase db = null;
        try {
            db = dbHelper.getWritableDatabase();
            db.beginTransaction();

            // update Orders set OrderPrice = 800 where Id = 6
            ContentValues cv = new ContentValues();
            //cv.put(ADDRESS, address.getAddress().toString());
            db.update(CartDao.TABLE_CART,
                    cv,
                    "bid = ?",
                    new String[]{cart.getBid().toString()});
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
    public int getCount(User user){
        int count = 0;

        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            db = dbHelper.getReadableDatabase();
            cursor = db.query(CartDao.TABLE_CART,
                    new String[]{"COUNT(bid)"},
                    UID + " = ?",
                    new String[] {user.getObjectId().toString()},
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
     * 将查找到的数据转换成Cart类
     */
    private Cart parseCart(Cursor cursor) {
        Cart cart = new Cart();
        cart.setBid(cursor.getString(cursor.getColumnIndex(BID)));
        cart.setUid(cursor.getString(cursor.getColumnIndex(UID)));
        cart.setTitle(cursor.getString(cursor.getColumnIndex(TITLE)));
        cart.setImage(cursor.getString(cursor.getColumnIndex(IMAGE)));
        cart.setPublisher(cursor.getString(cursor.getColumnIndex(PUBLISHER)));
        cart.setPrice(cursor.getString(cursor.getColumnIndex(PRICE)));
        cart.setNumber(cursor.getString(cursor.getColumnIndex(NUMBER)));
        return cart;
    }


}


