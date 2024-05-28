package poly.edu.vn.asm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import manchinh.home;
import phongban.maketing;

public class login extends AppCompatActivity {
Button btnDangNhap;
TextView tvDangKi;
TextInputEditText edtEmail, edtPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        edtEmail = findViewById(R.id.txt_email);
        edtPassword = findViewById(R.id.txt_password);
        btnDangNhap = findViewById(R.id.btn_login);
        tvDangKi = findViewById(R.id.tv_dangKi);



        tvDangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, dangki.class);
                startActivity(intent);
            }
        });

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();

                if(email.isEmpty() || password.isEmpty()){
                    Toast.makeText(login.this, "Email hoặc mật khẩu không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(email.length()<3){
                    edtEmail.setError("Email không được nhỏ hơn 3 ký tự");
                    return;
                }

                if(password.length()<3){
                    edtEmail.setError("Email không được nhỏ hơn 3 ký tự");
                    return;
                }

                startActivity(new Intent(login.this, home.class));

            }
        });


        Intent intentLogin = getIntent();
        Bundle bundleLogin = intentLogin.getExtras();
        String email = "";
        String password = "";
        if (bundleLogin != null) {
            email = bundleLogin.getString("email", "");
            password = bundleLogin.getString("password", "");
        }
        edtEmail.setText(email);
        edtPassword.setText(password);

    }
}