package poly.edu.vn.lab_tonghop_ph49806;

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

import danhsach.MainActivity;

public class DangNhap extends AppCompatActivity {
    EditText edtName, edtPassword;
    Button btnLogin;
    CheckBox cbLuuMatKhau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.layout_dangnhap);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtName = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        btnLogin = findViewById(R.id.btn_login);
        cbLuuMatKhau = findViewById(R.id.cb_luuMK);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                String password = edtPassword.getText().toString();
                boolean remember = cbLuuMatKhau.isChecked();


                String checkfullName = "ph49806";
                String checkpassword = "ph49806";

                if (name.isEmpty() || password.isEmpty()) {
                    Toast.makeText(DangNhap.this, "Vui lòng nhập tên đăng nhập và mật khẩu", Toast.LENGTH_SHORT).show();
                } else if (name.equals(checkfullName) && password.equals(checkpassword)) {

                    if(remember) {
                        SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("fullName", checkfullName);
                        editor.putString("password", checkpassword);
                        editor.apply();

                        SharedPreferences sharedPreferences2 = getSharedPreferences("data", MODE_PRIVATE);
                        String fullName2 = sharedPreferences2.getString("fullName", "");
                        String password2 = sharedPreferences2.getString("password", "");

//                        Toast.makeText(this, fullName2 + " - " + password2, Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(DangNhap.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(DangNhap.this, MainActivity.class));
                } else {
                    Toast.makeText(DangNhap.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}