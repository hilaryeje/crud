package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    EditText first_name, middle_name, last_name, email, username, password;
    Button register;
    ToggleButton passwordToggle;

    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        first_name = findViewById(R.id.first_name);
        middle_name = findViewById(R.id.middle_name);
        last_name = findViewById(R.id.last_name);
        email = findViewById(R.id.email);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        passwordToggle = findViewById(R.id.passwordToggle);

        register = findViewById(R.id.registerButton);
        db = new DBHelper(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String first_nameTXT = first_name.getText().toString();
                String middle_nameTXT = middle_name.getText().toString();
                String last_nameTXT = last_name.getText().toString();
                String emailTXT = email.getText().toString();
                String usernameTXT = username.getText().toString();
                String passwordTXT = password.getText().toString();

                if(first_nameTXT.equals("") || last_nameTXT.equals("") || emailTXT.equals("") || usernameTXT.equals("") || passwordTXT.equals(""))
                    Toast.makeText(MainActivity.this, "Fill out all the fields!", Toast.LENGTH_SHORT).show();
                else if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches())
                    Toast.makeText(MainActivity.this, "Invalid Email Address", Toast.LENGTH_SHORT).show();
                else if (password.getText().toString().length() < 6)
                    Toast.makeText(MainActivity.this, "Password must be more than 6 letters", Toast.LENGTH_SHORT).show();
                else if (username.getText().toString().length() < 4)
                    Toast.makeText(MainActivity.this, "Username must be more than 4 letters", Toast.LENGTH_SHORT).show();
                else {
                    if(passwordTXT.equals(passwordTXT)){
                        Boolean checkusername = db.checkusername(usernameTXT);
                        if (checkusername==false){
                            Boolean insert = db.insertuserdata(first_nameTXT,middle_nameTXT,last_nameTXT,emailTXT,usernameTXT,passwordTXT);
                            if(insert==true){
                                Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(MainActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "User already exists!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Password does not match!", Toast.LENGTH_SHORT).show();
                    }
                }

//                Boolean checkinsertdata = db.insertuserdata(first_nameTXT, middle_nameTXT, last_nameTXT, emailTXT, usernameTXT, passwordTXT);
//                if(checkinsertdata==true)
//                    Toast.makeText(MainActivity.this, "Your Details Have Been Saved", Toast.LENGTH_SHORT).show();
//                else
//                    Toast.makeText(MainActivity.this, "Your Details Have Not Been Saved", Toast.LENGTH_SHORT).show();
            }
        });

        passwordToggle.setOnCheckedChangeListener(showPasswordListener);
    }

    CompoundButton.OnCheckedChangeListener showPasswordListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                password.setSelection(password.getText().length());
            } else {
                password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                password.setSelection(password.getText().length());
            }
        }
    };

//        private boolean isFormValid() {
//            if (TextUtils.isEmpty(first_name.getText().toString())) {
//                first_name.setError("Invalid name!");
//                return false;
//            }
//            return true;

//
//        if (TextUtils.isEmpty(middle_name.getText().toString())) {
//            middle_name.setError("Email is Empty!");
//            return false;
//        }
//
//        if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
//            email.setError("Invalid Email!");
//            return false;
//        }
//
//        if (username.getText().toString().length() < 4) {
//            username.setError("Username should be more than 4 letters");
//            return false;
//        }
//
//        if (TextUtils.isEmpty(password.getText().toString())) {
//            password.setError("Password is Empty!");
//            return false;
//        }
//
//        if (password.getText().toString().length() < 6) {
//            password.setError("Password must be more than 6 letters");
//            return false;
//        }
//
//        return true;
//    }


    public void login(View view){
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }
}