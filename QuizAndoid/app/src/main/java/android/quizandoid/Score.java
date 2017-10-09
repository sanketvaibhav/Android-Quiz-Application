package android.quizandoid;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class Score extends Activity {

    private SQLiteDatabase db;
    private Cursor c;
    TextView tv,tv2;
    String a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score);
        a =Login.email;
        tv=(TextView)findViewById(R.id.textView1);
        tv2=(TextView)findViewById(R.id.textView2);
        createDatabase();
        Cursor ch1=getAlldata();
        if(ch1.getCount()==0)
        { showmessage("Overall Score","Nothing Found FOR THIS USERNAME");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while(ch1.moveToNext())
        {
            buffer.append(" "+ ch1.getString(0)+" ");
            buffer.append("in r "+ ch1.getString(1)+" ");
            buffer.append("has scored "+ ch1.getString(2)+"\n");
        }
        showmessage("Overall Score",buffer.toString());
    }

    protected void createDatabase()
    {
        db=openOrCreateDatabase("Sall.db", Context.MODE_PRIVATE, null);

    }


    public void showmessage(String title, String message)
    {
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public Cursor getAlldata()
    {
        Cursor ch = db.rawQuery("select * from scoreOfAll where candName=?",new String[] {Login.email});
        return ch;
    }

}
