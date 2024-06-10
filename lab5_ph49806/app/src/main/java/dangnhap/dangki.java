package dangnhap;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import lab8.ReadWriteUser;
import lab8.User;
import poly.edu.vn.lab5_ph49806.R;

public class dangki extends AppCompatActivity {
    EditText edtName2, edtPassword2, edtConfirmPassword2;
    Button btnLogin2;
    Context context =this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.bai3_dangki);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtName2 = findViewById(R.id.edt_username2);
        edtPassword2 = findViewById(R.id.edt_password2);
        btnLogin2 = findViewById(R.id.btn_register2);
        edtConfirmPassword2 = findViewById(R.id.edt_comfirm_password2);

        btnLogin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName2.getText().toString();
                String password = edtPassword2.getText().toString();
                String confirmPassword = edtConfirmPassword2.getText().toString();

                if (name.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    if (name.isEmpty()) {
                        edtName2.setError("vui lòng nhập tên");
                    }
                    if (password.isEmpty()) {
                        edtPassword2.setError("vui lòng nhập mật khẩu");
                    }
                    if (confirmPassword.isEmpty()) {
                        edtConfirmPassword2.setError("vui lòng nhập lại mật khẩu");
                    }
                    return;
                }

                if (!password.equals(confirmPassword)) {
                    edtConfirmPassword2.setError("mật khẩu nhập lại không trùng khớp");
                    return;
                }


                boolean hasError = false;

                if (name.length() < 5) {
                    edtName2.setError("tên phải trên 5 kí tự");
                    hasError = true;
                }


                if (password.length() < 6) {
                    edtPassword2.setError("mật khẩu trên 6 kí tự");
                    hasError = true;
                }

                if (!password.equals(confirmPassword)) {
                    edtConfirmPassword2.setError("mật khẩu không trùng khớp");
                    hasError = true;
                }

                if (hasError) {
                    return;
                }

                // ghi dữ liệu vào file
                ReadWriteUser readWriteUser = new ReadWriteUser(context);
                readWriteUser.writeUser(context, "user.txt",
                        new User(name, password));


                Intent intent2 = new Intent(dangki.this, dangnhap.class);
                Bundle bundle = new Bundle();
                bundle.putString("username", name);
                bundle.putString("password", password);
                intent2.putExtras(bundle);
                startActivity(intent2);

                finish();

            }
        });
    }
}