package lk.sliit.finestay;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Property;

public class DatabaseHelperProperty extends SQLiteOpenHelper {
        public static final String DATABASE_NAME= "property.db";
        public static final String  TABLE_NAME= "property.tb";

    public static final String COLUMN_1= "ID";
    public static final String COLUMN_2= "PACKAGE_NAME";
    public static final String COLUMN_3= "NO_OF_GUESTS";
    public static final String COLUMN_4= "NO_OF_ROOMS";
    public static final String COLUMN_5= "NO_OF_BATHS";
    public static final String COLUMN_6= "EST_VALUE(RS.)";


    public DatabaseHelperProperty(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
