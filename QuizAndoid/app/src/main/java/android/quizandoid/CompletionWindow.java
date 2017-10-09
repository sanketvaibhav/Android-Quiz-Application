package android.quizandoid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CompletionWindow extends Activity {

    TextView tv,tv2;

    String ename1=Login.email;
    int round;
    private SQLiteDatabase db;
    private Cursor c;
    Button btn;
    int s;String n;
    private static final String x="SELECT * FROM scores";
    String use;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.completionwindow);
        use=Login.email;
        tv=(TextView)findViewById(R.id.textView3);
        tv2=(TextView)findViewById(R.id.textView2);
        btn=(Button)findViewById(R.id.button1);
        Bundle data=getIntent().getExtras();
        s=data.getInt("score");
        n=data.getString("Name");
        tv.setText(use+" "+"with score "+s);
        createDatabase2();
        findRoundNumber();
        ++round;
        insertDB2();

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent i=new Intent(getApplicationContext(), StartWindow.class);
                i.putExtra("Name", n);
                startActivity(i);
            }
        });
    }

    protected void createdatabase()
    {
        db=openOrCreateDatabase("ScoresDB.db", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS scores(name VARCHAR, score NUMBER)");
    }

    protected void createDatabase2()
    {
        db=openOrCreateDatabase("Sall.db", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS scoreOfAll(candName VARCHAR, roundNo NUMBER, scoreCand NUMBER)");

    }

    protected void findRoundNumber()
    {
        Cursor cursor=db.rawQuery("SELECT * FROM scoreOfALL WHERE candName=?",new String[] {ename1});
        round=cursor.getCount();
    }
    protected void insertDB2()
    {
                String query="INSERT INTO scoreOfAll(candName,roundNo,scoreCand) values('"+ename1+"','"+round+"','"+s+"');)";

        db.execSQL(query);
    }
}
