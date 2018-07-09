package com.ydeliveryadmin.milyutin.dmitry.ydeliveryadmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import ru.profit_group.scorocode_sdk.Callbacks.CallbackLoginUser;
import ru.profit_group.scorocode_sdk.Responses.user.ResponseLogin;
import ru.profit_group.scorocode_sdk.ScorocodeSdk;
import ru.profit_group.scorocode_sdk.scorocode_objects.User;

public class LoginActivity extends AppCompatActivity {

    private static final String APPLICATION_ID = "c312b5a7b1794220a85b89079250e64e";
    private static final String CLIENT_KEY = "aec9813472954766897c74a55815d4e1";
    private static final String MASTER_KEY = "ec67c7fce9fb4f63a234d2d708f3a9c6";


    private EditText edLogin;
    private Button btLogin;
    private ProgressBar prBarLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edLogin = findViewById(R.id.edLogin);
        btLogin = findViewById(R.id.btLobin);
        prBarLogin = findViewById(R.id.prBarLogin);


        ScorocodeSdk.initWith(APPLICATION_ID, CLIENT_KEY,MASTER_KEY,
                null,null,null,null);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btLogin.setClickable(false);
                prBarLogin.setVisibility(ProgressBar.VISIBLE);
                checkLogin();


            }
        });
    }




    private void checkLogin(){
        String pass = "1";
        User user = new User();
        final String textLogin = edLogin.getText().toString();
        user.login(textLogin, pass, new CallbackLoginUser() {
            @Override
            public void onLoginSucceed(ResponseLogin responseLogin) {
                prBarLogin.setVisibility(ProgressBar.INVISIBLE);
                Boolean admin = responseLogin.getResult().getUserInfo().getBoolean("Admin");
                if (admin){
                btLogin.setClickable(true);
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);}
                else
                    Toast.makeText(LoginActivity.this, "Удалите программу!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLoginFailed(String errorCode, String errorMessage) {
                prBarLogin.setVisibility(ProgressBar.INVISIBLE);
                Toast.makeText(LoginActivity.this, "Неверный номер", Toast.LENGTH_SHORT).show();
                btLogin.setClickable(true);
            }
        });
    }





    // сбор даных с форм
    // авторизация
    // запуск главной активити

}
