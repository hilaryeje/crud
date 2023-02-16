package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    TextView forgotPassword;
    Button login;
    ToggleButton passwordToggle;

    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        forgotPassword = findViewById(R.id.forgotPassword);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login_btn);
        db = new DBHelper(this);
        passwordToggle = findViewById(R.id.passwordToggle);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameTXT = username.getText().toString();
                String passwordTXT = password.getText().toString();
                
                if(usernameTXT.equals("") || passwordTXT.equals(""))
                    Toast.makeText(LoginActivity.this, "Fill out the forms!", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkusernamepassword = db.checkusernamepassword(usernameTXT, passwordTXT);
                    if(checkusernamepassword==true){
                        Toast.makeText(LoginActivity.this, "Logged In Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        passwordToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                password.setSelection(password.getText().length());
            }
        });

    }

    public void register (View view){
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }


}