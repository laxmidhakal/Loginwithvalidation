package com.example.loginwithvalidation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    Button login;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setupUI();
        setupListeners();
    }
    private void setupUI(){
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        register=findViewById(R.id.register);
        login=findViewById(R.id.login);
    }
    private void setupListeners(){
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkUsername();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(LoginActivity.this,MainActivity.class);
                startActivity(i);

            }
        });
    }
    boolean isEmail(EditText text){
        CharSequence email=text.getText().toString();
        return (!TextUtils.isEmpty(email)&& Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
    boolean isEmpty(EditText text){
        CharSequence str=text.getText().toString();
        return TextUtils.isEmpty(str);
    }
    void checkUsername(){
        boolean isValid=true;
        if (isEmpty(username)){
            username.setError("You must enter username to login!");
            isValid=false;
        }else {
            if (!isEmail(username)){
                username.setError("Enter valid email!");
                isValid=false;
            }
        }
        if (isEmpty(password)){
            password.setError("You must enter password to login!");
            isValid=false;
        }else {
            if (password.getText().toString().length()<4){
                password.setError("Password must be at least 4 chars long!");
                isValid=false;
            }
        }
        if (isValid){
            String usernameValue=username.getText().toString();
            String passwordValue=password.getText().toString();
            if (usernameValue.equals("")&& passwordValue.equals("")){
                Intent i=new Intent(LoginActivity.this,FirstActivity.class);
                startActivity(i);
                this.finish();
            }else {
                Toast t=Toast.makeText(this,"Wrong email or password",Toast.LENGTH_SHORT);
                t.show();
            }
        }
    }
}
