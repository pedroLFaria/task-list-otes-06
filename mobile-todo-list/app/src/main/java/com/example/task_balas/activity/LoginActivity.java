package com.example.task_balas.activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.task_balas.R;
import com.example.task_balas.config.RetrofitConfig;
import com.example.task_balas.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText nameInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nameInput = findViewById(R.id.nameInput);
        Button loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    private void login() {
        String userName = nameInput.getText().toString().trim();
        if (!userName.isEmpty()) {
            Call<User> call =  new RetrofitConfig().getUserService().userCreate(new User(userName));
            call.enqueue(getUserCreateCallback(userName));

        } else {
            nameInput.setError("Please enter your name");
        }
    }

    @NonNull
    private Callback<User> getUserCreateCallback(String userName) {
        return new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("userName", userName);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                nameInput.setError("Erro ao tentar acessar o servidor");
            }
        };
    }
}
