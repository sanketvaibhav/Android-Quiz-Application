package android.quizandoid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity implements View.OnClickListener{
    private Button login, register,y;
    private EditText etEmail, etPass;
    private Database db;
    private Session session;
    public static final String MyPREFERENCES="MyPrefs";
    public static final String Name = "nameKey";
    public static final String Password="passKey";
    public static String email;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        db = new Database(this);
        session = new Session(this);
        login = (Button)findViewById(R.id.btnLogin);
        y = (Button)findViewById(R.id.button);
        register = (Button)findViewById(R.id.btnReg);
        etEmail = (EditText)findViewById(R.id.etEmail);
        etPass = (EditText)findViewById(R.id.etPass);
        login.setOnClickListener(this);
        register.setOnClickListener(this);

        y.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnLogin:
                login();
                break;
            case R.id.btnReg:
                startActivity(new Intent(Login.this,Register.class));
                break;
            case R.id.button:
                startActivity(new Intent(Login.this,QuizMaster.class));
                break;

            default:

        }
    }
    private void login(){
        email = etEmail.getText().toString();
        String pass = etPass.getText().toString();

        if(db.getUser(email,pass)){
            session.setLoggedin(true);

            Intent i=new Intent(Login.this, StartWindow.class);
            i.putExtra("username", email);
            startActivity(i);
            finish();
        }else{
            Toast.makeText(getApplicationContext(), "Wrong username/password",Toast.LENGTH_SHORT).show();
        }
    }
}
