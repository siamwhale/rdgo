package th.go.rd.manop.rdrun;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by user on 9/2/2016.
 */
public class MyOpenHelper extends SQLiteOpenHelper {
    // Explicit
    public static final String database_name = "rdRun.db";   // Database file name
    private static final int database_version = 1;           // Version
    private static final String create_user_table = "CREATE TABLE userTABLE (" +
            "_id integer primary key," + // fix _id int first
            "User text," +
            "Password text," +
            "Name text," +
            "Surname text," +
            "Avata text," +
            "idUser text);";

    public MyOpenHelper(Context context) {//Constructor
        super(context,database_name,null,database_version); // ต่อท่อ  Context


    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(create_user_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
