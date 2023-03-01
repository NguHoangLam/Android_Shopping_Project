package com.example.project.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.project.Entity.Account;
import com.example.project.Entity.Cart;
import com.example.project.Entity.Product;
import com.example.project.R;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DBHelper extends SQLiteOpenHelper {

    public  static final String DBNAME="account.db";
    private static final String TABLE_NAME = "accounts";
    /// Account
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "username";
    private static final String KEY_PHONE_NUMBER = "phone";
    private static final String KEY_PASSWORD= "password";
    private static final String KEY_CREATEDATE= "createDate";
    // Product
    private static final String TABLE_PRODUCT = "products";
    private static final String PRODUCT_ID = "id";
    private static final String PRODUCT_IMAGE = "image";
    private static final String PRODUCT_NAME = "name";
    private static final String PRODUCT_PRICE = "price";
    private static final String PRODUCT_TYPE = "type";
    private static final String PRODUCT_DES = "description";
    private static final String PRODUCT_QUANTITY = "quantity";
    // Cart
    private static final String TABLE_CART = "carts";
    private static final String CART_ID = "id";
    private static final String ACCOUNT_ID = "accountId";
    private static final String PRODUCT1_ID = "productId";
    private static final String CART_FINISH = "isfinish";

    public DBHelper(Context context){
        super(context,DBNAME,null,1);


    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create_account_table = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY, %s TEXT, %s TEXT, %s TEXT, %s TEXT)", TABLE_NAME, KEY_ID, KEY_NAME, KEY_PHONE_NUMBER, KEY_PASSWORD,KEY_CREATEDATE);
        String create_product_table = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY, %s INTEGER, %s TEXT, %s INTEGER, %s TEXT, %s TEXT, %s INTEGER)", TABLE_PRODUCT, PRODUCT_ID, PRODUCT_IMAGE, PRODUCT_NAME, PRODUCT_PRICE,PRODUCT_TYPE,PRODUCT_DES,PRODUCT_QUANTITY);
        String create_cart_table = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY, %s INTEGER, %s INTEGER, %s INTEGER)", TABLE_CART, CART_ID, ACCOUNT_ID, PRODUCT1_ID, CART_FINISH);

//        sqLiteDatabase.execSQL("create table if not exists users(username TEXT primary key,password TEXT)");
        sqLiteDatabase.execSQL(create_account_table);
        sqLiteDatabase.execSQL(create_product_table);
        sqLiteDatabase.execSQL(create_cart_table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String drop_students_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
        sqLiteDatabase.execSQL(drop_students_table);

        String drop_products_table = String.format("DROP TABLE IF EXISTS %s", TABLE_PRODUCT);
        sqLiteDatabase.execSQL(drop_products_table);

        String drop_cart_table = String.format("DROP TABLE IF EXISTS %s", TABLE_CART);
        sqLiteDatabase.execSQL(drop_cart_table);

        onCreate(sqLiteDatabase);
    }

    public  Boolean insertAccountData(String username,String phone, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String currentDate = formatter.format(date);
//        hash the password
        password = sha256(password);
        values.put(KEY_NAME,username);
        values.put(KEY_PHONE_NUMBER,phone);
        values.put(KEY_PASSWORD,password);
        values.put(KEY_CREATEDATE,currentDate);

        long result = db.insert(TABLE_NAME,null,values);
        db.close();
        return result != -1;

    }

    public Boolean checkUsername(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from accounts where username=?",new String[] {username});

        return cursor.getCount() > 0;

    }

    public Account getAccount(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        password = sha256(password);
        Cursor cursor = db.rawQuery("select * from accounts where username=? and password=?",new String[]{username,password});
        System.out.println(cursor);
        if(cursor != null)
            cursor.moveToFirst();
        Account account = new Account(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
        return account;
    }
    public Product getProduct(int productId){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from products where id=? ",new String[]{String.valueOf(productId)});
        if(cursor != null)
            cursor.moveToFirst();
        Product product = new Product(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getInt(3), cursor.getString(4), cursor.getString(5),cursor.getInt(6));
        return product;
    }

    public Boolean checkUsernamePassword(String username,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        password = sha256(password);
        Cursor cursor = db.rawQuery("select * from accounts where username=? and password=?",new String[]{username,password});
        return cursor.getCount() > 0;

    }
    public static String sha256(final String base) {
        try{
            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
            final byte[] hash = digest.digest(base.getBytes("UTF-8"));
            final StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                final String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
    public void initProductPopulation(){
        ArrayList<Product> list = getAllProducts();
        if (list.size()>0){
            return;
        }
        ArrayList<Product> item = new ArrayList<>();
        item.add(new Product(1, R.drawable.gundam1,"GUNDAM Primus",13000,Product.TYPE_GUNDAM,"Content is here",100));
        item.add(new Product(2,R.drawable.gundam2,"GUNDAM NoNoi",15000,Product.TYPE_GUNDAM,"Content is here",100));

        item.add(new Product(3,R.drawable.game1,"Assassin",13000,Product.TYPE_GAME,"Content is here",100));
        item.add(new Product(4,R.drawable.game2,"Creed",15000,Product.TYPE_GAME,"Content is here",100));

        item.add(new Product(5,R.drawable.anime1,"GOD KNOW",27000,Product.TYPE_ANIME,"Content is here",100));
        item.add(new Product(6,R.drawable.gundam2,"I DONT KNOW",30000,Product.TYPE_ANIME,"Content is here",100));
        for (int i = 0; i < item.size(); i++) {
            addProduct(item.get(i));
        }

    }

    public void addProduct(Product product){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PRODUCT_ID,product.getId());
        values.put(PRODUCT_IMAGE,product.getImage());
        values.put(PRODUCT_NAME,product.getName());
        values.put(PRODUCT_PRICE,product.getPrice());
        values.put(PRODUCT_TYPE,product.getType());
        values.put(PRODUCT_DES,product.getDescription());
        values.put(PRODUCT_QUANTITY,product.getQuantity());

        db.insert(TABLE_PRODUCT, null, values);
        db.close();
    }

    public ArrayList<Product> getAllProducts(){
        ArrayList<Product> list = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_PRODUCT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while(cursor.isAfterLast() == false) {
            Product product = new Product(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getInt(3), cursor.getString(4), cursor.getString(5),cursor.getInt(6));
            list.add(product);
            cursor.moveToNext();
        }
        return list;

    }

    public void addCart(int accountID,int productID){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ACCOUNT_ID,accountID);
        values.put(PRODUCT1_ID,productID);
        values.put(CART_FINISH,0);

        db.insert(TABLE_CART,null,values);
        db.close();
    }

    public void updateCart(Cart cart){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ACCOUNT_ID,cart.getAccountId());
        values.put(PRODUCT1_ID,cart.getProductId());
        values.put(CART_FINISH,1);
        db.update(TABLE_CART, values, CART_ID + " = ?", new String[] { String.valueOf(cart.getId()) });
        db.close();

    }
    public ArrayList<Cart> getAllUnFinishCart(int accountId){
        ArrayList<Cart> cartList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from carts where accountId=? and isfinish=?",new String[]{ String.valueOf(accountId),"0"});
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            Cart cart = new Cart(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2));
            cartList.add(cart);
            cursor.moveToNext();
        }
        return cartList;


    }

}
