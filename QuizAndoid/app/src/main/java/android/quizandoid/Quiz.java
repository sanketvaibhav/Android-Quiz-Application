package android.quizandoid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Quiz extends Activity {

    TextView tv, tv2, tv3, tv4;
    RadioGroup rg;
    Button btn;
    int scr;
    private SQLiteDatabase db;
    private static final String x = "SELECT * FROM questions";
    private Cursor c;
    int qid = 0;
    CountDownTimer timer;
    String n;
    int done[] = new int[11];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz);
        tv = (TextView) findViewById(R.id.textView1);
        tv2 = (TextView) findViewById(R.id.textView2);
        tv3 = (TextView) findViewById(R.id.textView3);
        tv4 = (TextView) findViewById(R.id.textView4);
        rg = (RadioGroup) findViewById(R.id.radioGroup1);
        btn = (Button) findViewById(R.id.button1);
        Bundle data = getIntent().getExtras();
        n = data.getString("Name");
        openDatabase();
        c = db.rawQuery(x, null);
        c.moveToFirst();
        getQuestion();
        tv2.setText("Score: 0");
        startCount();


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int res = rg.getCheckedRadioButtonId();
                switch (res) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "Select one option", Toast.LENGTH_SHORT).show();
                        return;
                }
                if (res == Integer.parseInt(c.getString(6)))
                {
                    final Toast toast = Toast.makeText(getApplicationContext(), "Correct Answer!", Toast.LENGTH_SHORT);
                    toast.show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            toast.cancel();
                        }
                    }, 10);
                    scr++;
                    tv2.setText("Score: " + scr);
                    for (int i = 0; i < rg.getChildCount(); ++i) {

                        if (i == res) {

                            ((RadioButton) rg.getChildAt(i))
                                    .setTextColor(Color.GREEN);
                        }
                    }


                }
                else {
                    final Toast toast = Toast.makeText(getApplicationContext(), "Incorrect Answer!", Toast.LENGTH_SHORT);
                    toast.show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            toast.cancel();
                        }
                    }, 10);
                    for (int i = 0; i < rg.getChildCount(); ++i) {

                        if(i== Integer.parseInt(c.getString(6)))
                        {
                            ((RadioButton) rg.getChildAt(i))
                                    .setTextColor(Color.GREEN);

                        }
                        if (i == res) {

                            ((RadioButton) rg.getChildAt(i))
                                    .setTextColor(Color.RED);
                        }
                    }
                }

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (qid < 5)//unknown
                        {

                            rg.removeAllViews();
                            getQuestion();
                            timer.cancel();
                            startCount();
                        }
                        else {

                            timer.cancel();
                            Intent i = new Intent(getApplicationContext(), CompletionWindow.class);
                            i.putExtra("score", scr);
                            i.putExtra("Name", n);
                            startActivity(i);
                       }

                    }
                }, 1000);
            }
        });
    }

    protected void openDatabase() {
        db = openOrCreateDatabase("QuizDB.db", Context.MODE_PRIVATE, null);
    }

    protected void getQuestion() {
        qid++;
        c.moveToNext();
        tv.setText(c.getString(1));
        for (int i = 0; i <= 3; i++) {
            RadioButton rb = new RadioButton(this);
            rb.setId(i);
            rb.setText(c.getString(i + 2));
            rg.addView(rb);
        }
        rg.clearCheck();
    }

    protected void startCount() {
        tv4.setText("Time Remaining");
        btn.setText("SUBMIT");
        timer = new CountDownTimer(15000, 1000) {


            public void onTick(long millisUntilFinished) {
                tv3.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                tv3.setText("");
                tv4.setText("Time Over");
                btn.setText("Next");
                           }

        }.start();
    }
}