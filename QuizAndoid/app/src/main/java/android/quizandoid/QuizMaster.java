package android.quizandoid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class QuizMaster extends Activity {
    Button b;
    EditText t;
    String s;
    String pass="Singh";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quizmaster);
        b = (Button) findViewById(R.id.button4);
        t = (EditText) findViewById(R.id.editText);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                s=t.getText().toString();
                if(s.equals("admin"))
                {
                    Intent i=new Intent(QuizMaster.this, Db.class);
                    startActivity(i);}
                else {
                    Toast.makeText(getApplicationContext(), "Incorrect Password", Toast.LENGTH_SHORT).show();
                }
            }


        });

    }
}

