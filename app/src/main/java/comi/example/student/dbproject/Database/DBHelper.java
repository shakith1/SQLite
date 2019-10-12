package comi.example.student.dbproject.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "user.db";

    public DBHelper(Context context) {
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + UserMaster.Users.TABLE_NAME + " (" +
                UserMaster.Users.COLUMN_NAME_ID + " INTEGER PRIMARY KEY," +
                UserMaster.Users.COLUMN_NAME_USERNAME + " TEXT," +
                UserMaster.Users.COLUMN_NAME_PASSWORD + " TEXT)";

    db.execSQL(query);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void addInfo(String userName,String password){
        SQLiteDatabase database = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UserMaster.Users.COLUMN_NAME_USERNAME,userName);
        values.put(UserMaster.Users.COLUMN_NAME_PASSWORD,password);

        long row = database.insert(UserMaster.Users.TABLE_NAME,null,values);

        System.out.println("Row : " + row);
    }

    public List readAllInfo(){
        SQLiteDatabase database = getReadableDatabase();

        String projection[] = {UserMaster.Users.COLUMN_NAME_ID,UserMaster.Users.COLUMN_NAME_USERNAME,UserMaster.Users.COLUMN_NAME_PASSWORD};

        String sortOrder = UserMaster.Users.COLUMN_NAME_USERNAME + " DESC";

        List userNameList = new ArrayList();
        List pwdList = new ArrayList();

        Cursor data = database.query(UserMaster.Users.TABLE_NAME,projection,null,null,null,null,sortOrder);

        while(data.moveToNext()){
            String uName = data.getString(data.getColumnIndexOrThrow(UserMaster.Users.COLUMN_NAME_USERNAME));
            String pwd = data.getString(data.getColumnIndexOrThrow(UserMaster.Users.COLUMN_NAME_PASSWORD));

            userNameList.add(uName);
            pwdList.add(pwdList);
        }

        data.close();
        return userNameList;
    }

    public void readInfo(String uName,String pwd,Context context){
        SQLiteDatabase database = getReadableDatabase();

        String projection[] = {UserMaster.Users.COLUMN_NAME_ID,UserMaster.Users.COLUMN_NAME_USERNAME,UserMaster.Users.COLUMN_NAME_PASSWORD};

        String sortOrder = UserMaster.Users.COLUMN_NAME_USERNAME + " DESC";

        List userNameList = new ArrayList();
        List pwdList = new ArrayList();

        Cursor data = database.query(UserMaster.Users.TABLE_NAME,projection,null,null,null,null,sortOrder);

        while(data.moveToNext()){
            String userName = data.getString(data.getColumnIndexOrThrow(UserMaster.Users.COLUMN_NAME_USERNAME));
            String pwds = data.getString(data.getColumnIndexOrThrow(UserMaster.Users.COLUMN_NAME_PASSWORD));

            userNameList.add(userName);
            pwdList.add(pwds);
        }

        data.close();

        if(userNameList.contains(uName)){
            if (pwdList.contains(pwd)) {
                Toast.makeText(context, "Logged In Successfully", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context,"Password is incorrect",Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(context,"Unable to find the user",Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteInfo(String userName){
        SQLiteDatabase database = getReadableDatabase();

        String selection = UserMaster.Users.COLUMN_NAME_USERNAME + " LIKE ?";

        String selectionArgs[] = {userName};

        database.delete(UserMaster.Users.TABLE_NAME,selection,selectionArgs);
    }

    public void updateInfo(String userName,String password){
        SQLiteDatabase database = getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(UserMaster.Users.COLUMN_NAME_PASSWORD,password);

        String selection = UserMaster.Users.COLUMN_NAME_USERNAME + " LIKE ?";
        String selectionArgs[] = {userName};

        int count = database.update(UserMaster.Users.TABLE_NAME,values,selection,selectionArgs);
    }
}
