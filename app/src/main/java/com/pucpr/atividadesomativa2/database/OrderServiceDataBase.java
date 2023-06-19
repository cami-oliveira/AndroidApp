package com.pucpr.atividadesomativa2.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.pucpr.atividadesomativa2.model.OrderService;

import java.util.ArrayList;
import java.util.Date;

public class OrderServiceDataBase extends SQLiteOpenHelper {

    private static final String DB_NAME = "orderservice.sqlite";
    private static final int DB_VERSION = 1;
    private static final String DB_TABLE = "OrderService";
    private static final String COL_ID = "id";
    private static final String COL_CLIENT = "client";
    private static final String COL_PHONE = "phone";
    private static final String COL_CREATED_DATE = "created_date";
    private static final String COL_FINISHED_DATE = "finished_date";
    private static final String COL_REMOVED_DATE = "removed_date";
    private static final String COL_DEVICE = "device";
    private static final String COL_DETAIL = "detail";
    private static final String COL_SOLUTION = "solution";

    public OrderServiceDataBase(@Nullable Context context) {
        super (context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "Create table if not exists "+DB_TABLE + "(" +
                COL_ID + " integer primary key autoincrement, " +
                COL_CLIENT + " text, " +
                COL_PHONE + " text, " +
                COL_DETAIL + " text, " +
                COL_DEVICE + " text, " +
                COL_SOLUTION + " text, " +
                COL_CREATED_DATE + " int, " +
                COL_FINISHED_DATE + " int, " +
                COL_REMOVED_DATE + " int)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long createDefaultOrderServiceInDB(OrderService orderService){
        ContentValues values = new ContentValues();
        values.put(COL_CLIENT, orderService.getClient());
        values.put(COL_PHONE, orderService.getPhone());
        values.put(COL_CREATED_DATE, System.currentTimeMillis());
        values.put(COL_DETAIL, orderService.getDetail());
        values.put(COL_DEVICE, orderService.getDevice());
        SQLiteDatabase database = getWritableDatabase();
        long id = database.insert(DB_TABLE, null, values);
        database.close();
        return id;
    }

    public long createEntireOrderServiceInDB(OrderService orderService){
        ContentValues values = new ContentValues();
        values.put(COL_CLIENT, orderService.getClient());
        values.put(COL_PHONE, orderService.getPhone());
        values.put(COL_CREATED_DATE, System.currentTimeMillis());
        values.put(COL_FINISHED_DATE, orderService.getFinishedAt().getTime());
        values.put(COL_REMOVED_DATE, orderService.getRemovedAt().getTime());
        values.put(COL_DETAIL, orderService.getDetail());
        values.put(COL_SOLUTION, orderService.getSolution());
        SQLiteDatabase database = getWritableDatabase();
        long id = database.insert(DB_TABLE, null, values);
        database.close();
        return id;
    }

    public ArrayList<OrderService> getOrdersServicesFromDB(){
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query(DB_TABLE, null, null, null,
                null, null, null);
        ArrayList<OrderService> orderServices = new ArrayList<>();
        if (cursor.moveToFirst()){
            do{
                long id = cursor.getLong(cursor.getColumnIndexOrThrow(COL_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COL_CLIENT));
                String phone = cursor.getString(cursor.getColumnIndexOrThrow(COL_PHONE));
                long createdAt = cursor.getLong(cursor.getColumnIndexOrThrow(COL_CREATED_DATE));
                orderServices.add(new OrderService(id,
                        name,
                        phone,
                        null,
                        null,
                        null,
                        new Date(createdAt),
                        null,
                        null));
            }while (cursor.moveToNext());
        }


        database.close();
        return orderServices;
    }

    public int removeOrderServiceInDB(OrderService orderService){
        ContentValues values = new ContentValues();
        values.put(COL_REMOVED_DATE, System.currentTimeMillis());
        String id = String.valueOf(orderService.getId());
        SQLiteDatabase database = getWritableDatabase();
        int count = database.update(DB_TABLE, values, COL_ID + "?=", new String[]{id});
        database.close();
        return count;
    }

    public int finishOrderServiceInDB(OrderService orderService){
        ContentValues values = new ContentValues();
        values.put(COL_SOLUTION, orderService.getSolution());
        values.put(COL_FINISHED_DATE, System.currentTimeMillis());
        String id = String.valueOf(orderService.getId());
        SQLiteDatabase database = getWritableDatabase();
        int count = database.update(DB_TABLE, values, COL_ID + "?=", new String[]{id});
        database.close();
        return count;
    }
}
