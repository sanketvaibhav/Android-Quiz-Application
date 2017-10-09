package android.quizandoid;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Db extends Activity {
    Button g;
    private SQLiteDatabase db1;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.db);

        g = (Button) findViewById(R.id.button5);
        createDatabase();


        g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor ch1=getAlldata();
                if(ch1.getCount()==0)
                { showmessage("Overall Score","No Data");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while(ch1.moveToNext())
                {
                    buffer.append(" "+ ch1.getString(0)+" ");
                    buffer.append("in r "+ ch1.getString(1)+" ");
                    buffer.append("has score "+ ch1.getString(2)+"\n");
                }
                showmessage("Overall Score",buffer.toString());


            }


        });
    }

    protected void createDatabase()
    {
        db1=openOrCreateDatabase("Sall.db", Context.MODE_PRIVATE, null);

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
        Cursor ch = db1.rawQuery("select * from scoreOfAll",null);
        return ch;
    }
}