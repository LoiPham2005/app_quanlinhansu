package dangnhap;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import bai2.Home;
import poly.edu.vn.lab5_ph49806.R;

public class dangnhap extends AppCompatActivity {

    EditText edtName, edtPassword;
    Button btnLogin, btnRegister;
    CheckBox remember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.bai3_dangnhap);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtName = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        btnLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_register);
        remember = findViewById(R.id.cb_luuMK);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(dangnhap.this, dangki.class));
            }
        });

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String username = bundle.getString("username", "");
            String pass = bundle.getString("password", "");

            edtName.setText(username);
            edtPassword.setText(pass);

        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                String password = edtPassword.getText().toString();
                boolean check = remember.isChecked();

                if (name.isEmpty() || password.isEmpty()) {
                    Toast.makeText(dangnhap.this, "Vui lòng nhập tên đăng nhập và mật khẩu", Toast.LENGTH_SHORT).show();
                } else {
                    // Xử lý logic đăng nhập ở đây
                    if(check) {
                        SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("fullName", name);
                        editor.putString("password", password);
                        editor.apply();

                        SharedPreferences sharedPreferences2 = getSharedPreferences("data", MODE_PRIVATE);
                        String fullName2 = sharedPreferences2.getString("fullName", "");
                        String password2 = sharedPreferences2.getString("password", "");
//
                    }

                    Toast.makeText(dangnhap.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(dangnhap.this, Home.class));
                }
            }


        });

    }

}