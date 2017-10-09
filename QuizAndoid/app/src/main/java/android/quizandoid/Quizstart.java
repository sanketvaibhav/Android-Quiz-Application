package android.quizandoid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Quizstart extends Activity {

    RadioGroup rg;
    TextView tv;
    Button btn;
    private Cursor p;
    String n;
    private SQLiteDatabase db;
    String q[]={
            "Two places on the same meridian must have the same",
            "Where is Dead Sea situated in the following continents",
            "Two places on same meridian must have same",
            "Which Apollo mission first landed the first humans on the moon",
            "Oceans hold what percentage of Earth Surface Water",
            "Which Country produces proton cars",
    };
    String a[]={"Solar time","Europe","Length of Summer","Apollo 7","67 percent","Indonesia"};
    String b[]={"Length of Summer","Australia","Solar Time","Apollo 9","77 percent","Malaysia"};
    String c[]={"Length of Winter","Africa","Length of Winter","Apollo 11","97 percent","Japan"};
    String d[]={"Latitude","Asia","Latitude","Apollo 3","87 percent","South Korea"};
    int ans[]={1,3,1,2,2,1};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quizstart);
        Bundle data=getIntent().getExtras();
        btn=(Button)findViewById(R.id.button1);
        createDatabase();
        p=db.rawQuery("SELECT * FROM questions", null);
        if(!p.moveToFirst())
            insertDB();
        btn=(Button)findViewById(R.id.button1);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), Quiz.class);
                i.putExtra("Name", n);
                startActivity(i);
            }
        });
    }
    protected void createDatabase()
    {
        db=openOrCreateDatabase("QuizDB.db", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS questions(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, question VARCHAR, opA VARCHAR," +
                "opB VARCHAR, opC VARCHAR,opD VARCHAR,answer NUMBER)");
    }
    protected void insertDB()
    {
        int l=50;
        for(int i=0;i<l;i++)
        {
            String query="INSERT INTO questions(question,opA,opB,opC,opD,answer) values('"+q[i]+"','"+a[i]+"','"+b[i]+
                    "','"+c[i]+"','"+d[i]+"','"+ans[i]+"');)";
            db.execSQL(query);
        }
        Toast.makeText(getApplicationContext(), "Application launched", Toast.LENGTH_SHORT).show();
    }

}
