package th.ac.tu.siit.its333.lab4exercise1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CourseDBHelper extends SQLiteOpenHelper {

    private static final String name = "courses.sqlite3";
    private static final int version = 4;


    public CourseDBHelper(Context ctx) {
        super(ctx, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE course (" +
                "_id integer primary key autoincrement," +         // credit
                "grade text not null," +            // letter grade e.g. A, B+
                "price int default 0," +            // letter grade e.g. A, B+
                "value real default 0.0);";         // grade value e.g. 4, 3.5
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS course;";
        db.execSQL(sql);
        this.onCreate(db);
    }
}
