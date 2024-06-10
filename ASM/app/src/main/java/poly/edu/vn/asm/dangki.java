package poly.edu.vn.asm;

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

import poly.edu.vn.asm.luufile.ReadWriteUser;
import poly.edu.vn.asm.luufile.User;

public class dangki extends AppCompatActivity {
    EditText edtName, edtEmail, edtPassword, edtConfirmPassword;
    Button btnRegister;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dangki);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtName = findViewById(R.id.edt_name);
        edtEmail = findViewById(R.id.edt_email);
        edtPassword = findViewById(R.id.edt_password);
        edtConfirmPassword = findViewById(R.id.edt_thepassword);
        btnRegister = findViewById(R.id.btn_register);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                String email = edtEmail.getText().toString();
                String pass = edtPassword.getText().toString();
                String confirmPass = edtConfirmPassword.getText().toString();

                if (name.isEmpty() || email.isEmpty() || pass.isEmpty() || confirmPass.isEmpty()) {
                    if (name.isEmpty()) {
                        edtName.setError("Please enter your name");
                    }
                    if (email.isEmpty()) {
                        edtEmail.setError("Please enter your email");
                    }
                    if (pass.isEmpty()) {
                        edtPassword.setError("Please enter your password");
                    }
                    if (confirmPass.isEmpty()) {
                        edtConfirmPassword.setError("Please confirm your password");
                    }
                    return;
                }

                boolean hasError = false;

                if (name.length() < 3) {
                    edtName.setError("Name must be at least 3 characters");
                    hasError = true;
                }

                if (!email.contains("@gmail.com")) {
                    edtEmail.setError("Please enter your email address ending in @gmail.com");
                    hasError = true;
                }

                if (pass.length() < 5) {
                    edtPassword.setError("Password must be at least 6 characters");
                    hasError = true;
                }

                if (!pass.equals(confirmPass)) {
                    edtConfirmPassword.setError("Passwords do not match");
                    hasError = true;
                }

                if (hasError) {
                    return;
                }

                // ghi dữ liệu vào file
                ReadWriteUser readWriteUser = new ReadWriteUser(context);
                readWriteUser.writeUser(context, "user.txt",
                        new User(email, pass));

                Intent intent = new Intent(dangki.this, login.class);
                Bundle bundle = new Bundle();
                bundle.putString("email", email);
                bundle.putString("password", pass);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });


    }


}