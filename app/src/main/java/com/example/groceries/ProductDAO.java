package com.example.groceries;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.groceries.GroceriesDbHelper;

import java.util.ArrayList;

public class ProductDAO {
    private SQLiteDatabase db;
    private GroceriesDbHelper dbHelper;
    public ProductDAO(Context context){
        dbHelper=new GroceriesDbHelper(context);
    }
    public long insertProduct(Product product) {
        long result=0;
        db=dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(GroceriesContract.Product.COLUMN_NAME_BARCODE,product.getBarcode());
        values.put(GroceriesContract.Product.COLUMN_NAME_DESCRIPTION,product.getDescription());
        values.put(GroceriesContract.Product.COLUMN_NAME_BRAND,product.getBrand());
        values.put(GroceriesContract.Product.COLUMN_NAME_COST,product.getCost());
        values.put(GroceriesContract.Product.COLUMN_NAME_PRICE,product.getPrice());
        values.put(GroceriesContract.Product.COLUMN_NAME_STOCK,product.getStock());
        result=db.insert(GroceriesContract.Product.TABLE_NAME,null,values);
        return result;
    }

    public ArrayList<String> getAllBarcodes(){
        ArrayList<String> barcodes = new ArrayList<String>();
        db=dbHelper.getReadableDatabase();
        String[] projection={
                GroceriesContract.Product.COLUMN_NAME_BARCODE,
//                    GroceriesContract.Product.COLUMN_NAME_DESCRIPTION
        };

        Cursor cursor = db.query(
                GroceriesContract.Product.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            barcodes.add(cursor.getString(0));
        }
        cursor.close();

        return  barcodes;
    }
}
