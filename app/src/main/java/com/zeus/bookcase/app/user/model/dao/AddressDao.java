package com.zeus.bookcase.app.user.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.zeus.bookcase.app.R;
import com.zeus.bookcase.app.home.data.BookCaseDBHelper;
import com.zeus.bookcase.app.user.model.Address;
import com.zeus.bookcase.app.user.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zeus_coder on 2016/3/31.
 */
public class AddressDao {

    private static final String TAG = "AddressDao";
    private static final String TABLE_ADDRESS = "Address";
    //列定义
    private final String ID = "id";
    private final String UID = "uid";
    private final String Name = "name";
    private final String ADDRESS = "address";
    private final String PHONE = "phone";
    private final String[] ADDRESS_COLUMNS = new String[] {
            ID,
            UID,
            Name,
            ADDRESS,
            PHONE};

    private Context context;
    private BookCaseDBHelper dbHelper;

    public AddressDao(Context context) {
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
            cursor = db.query(AddressDao.TABLE_ADDRESS, new String[]{"COUNT(id)"}, null, null, null, null, null);
            if (cursor.moveToFirst()) {
                count = cursor.getInt(0);
            }
            if (count > 0) return true;
        } catch (Exception e) {
            Log.e(TAG, "",e);
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
    public List<Address> getAllData(User user){
        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            db = dbHelper.getReadableDatabase();
            // select * from Orders
            cursor = db.query(AddressDao.TABLE_ADDRESS, ADDRESS_COLUMNS,
                    UID + " = ?",
                    new String[] {user.getObjectId().toString()},
                    null, null, null);

            if (cursor.getCount() > 0) {
                List<Address> orderList = new ArrayList<Address>(cursor.getCount());
                while (cursor.moveToNext()) {
                    orderList.add(parseAddress(cursor));
                }
                return orderList;
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
     * 查询数据库中所有数据
     */
    public Address getFirstData(User user){
        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            db = dbHelper.getReadableDatabase();
            // select * from Orders
            cursor = db.query(AddressDao.TABLE_ADDRESS, ADDRESS_COLUMNS,
                    UID + " = ?",
                    new String[] {user.getObjectId().toString()},
                    null, null, null);

            if (cursor.getCount() > 0) {
                Address address = new Address();
                if (cursor.moveToNext()) {
                    address = parseAddress(cursor);
                }
                return address;
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
    public boolean insertAddress(Address address){
        SQLiteDatabase db = null;

        try {
            db = dbHelper.getWritableDatabase();
            db.beginTransaction();
            ContentValues contentValues = new ContentValues();
            contentValues.put(ID, address.getId());
            contentValues.put(UID, address.getUid());
            contentValues.put(Name, address.getName());
            contentValues.put(ADDRESS, address.getAddress());
            contentValues.put(PHONE, address.getPhone());
            db.insertOrThrow(AddressDao.TABLE_ADDRESS, null, contentValues);

            db.setTransactionSuccessful();
            return true;
        }catch (SQLiteConstraintException e){
            Toast.makeText(context, "插入地址失败", Toast.LENGTH_SHORT).show();
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
    public boolean deleteAddress(Address address) {
        SQLiteDatabase db = null;

        try {
            db = dbHelper.getWritableDatabase();
            db.beginTransaction();
            // delete from Orders where Id = ?
            db.delete(AddressDao.TABLE_ADDRESS,
                    "id = ?", new String[]{address.getId().toString()});
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
    public boolean updateAddress(Address address){
        SQLiteDatabase db = null;
        try {
            db = dbHelper.getWritableDatabase();
            db.beginTransaction();

            // update Orders set OrderPrice = 800 where Id = 6
            ContentValues cv = new ContentValues();
            cv.put(ADDRESS, address.getAddress().toString());
            cv.put(PHONE, address.getPhone().toString());
            cv.put(Name, address.getName().toString());
            db.update(AddressDao.TABLE_ADDRESS,
                    cv,
                    "id = ?",
                    new String[]{address.getId().toString()});
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
            // select count(Id) from Orders where Country = 'China'
            cursor = db.query(AddressDao.TABLE_ADDRESS,
                    new String[]{"COUNT(id)"},
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
     * 将查找到的数据转换成Address类
     */
    private Address parseAddress(Cursor cursor){
        Address address = new Address();
        address.setId(cursor.getString(cursor.getColumnIndex(ID)));
        address.setUid(cursor.getString(cursor.getColumnIndex(UID)));
        address.setName(cursor.getString(cursor.getColumnIndex(Name)));
        address.setAddress(cursor.getString(cursor.getColumnIndex(ADDRESS)));
        address.setPhone(cursor.getString(cursor.getColumnIndex(PHONE)));
        return address;
    }

}
