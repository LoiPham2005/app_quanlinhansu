package poly.edu.vn.asm;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import manchinh.home;
import phongban.maketing;
import poly.edu.vn.asm.luufile.ReadWriteUser;
import poly.edu.vn.asm.luufile.User;

public class login extends AppCompatActivity {
    Button btnDangNhap;
    TextView tvDangKi;
    CheckBox cbLuuMatKhau;
    TextInputEditText edtEmail, edtPassword;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        edtEmail = findViewById(R.id.txt_email);
        edtPassword = findViewById(R.id.txt_password);
        btnDangNhap = findViewById(R.id.btn_login);
        tvDangKi = findViewById(R.id.tv_dangKi);
        cbLuuMatKhau = findViewById(R.id.cb_luuMK);


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
                boolean remember = cbLuuMatKhau.isChecked();

                // đọc dữ liệu từ file
                ReadWriteUser readWriteUser = new ReadWriteUser(context);
                ArrayList<User> ListUser = (ArrayList<User>) readWriteUser.readUser(context, "user.txt");

                boolean loginSuccessful = false;
                for (User user : ListUser) {
                    if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                        loginSuccessful = true;
                        break;
                    }
                }

                if (loginSuccessful) {
                    // Đăng nhập thành công
                    if (remember) {
                        SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("email", email);
                        editor.putString("password", password);
                        editor.apply();
                    }
                    Toast.makeText(login.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(login.this, home.class));
                } else if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(login.this, "Email or password cannot be blank", Toast.LENGTH_SHORT).show();
                } else {
                    // Đăng nhập thất bại
                    Toast.makeText(login.this, "Username or password is incorrect", Toast.LENGTH_SHORT).show();
                }


//                if(email.isEmpty() || password.isEmpty()){
//                    Toast.makeText(login.this, "Email hoặc mật khẩu không được để trống", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                if(email.length()<3){
//                    edtEmail.setError("Email không được nhỏ hơn 3 ký tự");
//                    return;
//                }
//
//                if(password.length()<3){
//                    edtEmail.setError("Email không được nhỏ hơn 3 ký tự");
//                    return;
//                }
//
//                if(remember) {
//                    SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.putString("email", email);
//                    editor.putString("password", password);
//                    editor.apply();
//
//                    SharedPreferences sharedPreferences2 = getSharedPreferences("data", MODE_PRIVATE);
//                    String fullName2 = sharedPreferences2.getString("fullName", "");
//                    String password2 = sharedPreferences2.getString("password", "");
//
//                }
//                Toast.makeText(login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
//
//                startActivity(new Intent(login.this, home.class));

            }
        });


        // lấy thông tin bên đăng kí
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