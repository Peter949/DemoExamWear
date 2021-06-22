package com.example.demoexamwear.wear;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.demoexamwear.API.API;
import com.example.demoexamwear.API.SignInParam;
import com.example.demoexamwear.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignIn extends Activity {

    private EditText email, password;
    private Button submit;
    private API api;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        api = API.retrofit.create(API.class);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                func();
            }
        });
    }
    private boolean emailValid(String email)
    {
        Pattern pattern = Pattern.compile(".+@[a-z]+\\.[a-z]+");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    private void Dialoger(Activity activity, String error)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(activity).setTitle("Сообщение").setMessage(error).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).create();
        alertDialog.show();
    }
    private void func()
    {
        if(email.getText().toString().equals("") || password.getText().toString().equals(""))
        {
            Dialoger(SignIn.this, "Заполните все пустые поля!");
        }
        else if(!emailValid(email.getText().toString()))
        {
            Dialoger(SignIn.this, "Неправльная почта!");
        }
        else {
            SignInParam param = new SignInParam();
            param.setEmail(email.getText().toString());
            param.setPassword(password.getText().toString());
            Call<SignInParam> call = api.doAuth(param);
            call.enqueue(new Callback<SignInParam>() {
                @Override
                public void onResponse(Call<SignInParam> call, Response<SignInParam> response) {
                    if (response.isSuccessful()) {
                        Intent intent = new Intent(SignIn.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Dialoger(SignIn.this, "Неправильно введенные данные!");
                    }
                }

                @Override
                public void onFailure(Call<SignInParam> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}