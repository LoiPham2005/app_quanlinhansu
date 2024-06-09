package settings;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import manchinh.home;
import poly.edu.vn.asm.R;
import poly.edu.vn.asm.login;
import tintuc.tintuc;

public class settings extends AppCompatActivity {
    ImageView imgHome, imgTinTuc;
    Button btnLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settings);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        imgHome = findViewById(R.id.icon_home);
        imgTinTuc = findViewById(R.id.icon_news);
        btnLogout = findViewById(R.id.btn_logout);

        imgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(settings.this, home.class));
            }
        });

        imgTinTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(settings.this, tintuc.class));
            }
        });

        btnLogout.setOnClickListener(v -> {
            // Tạo một AlertDialog để xác nhận đăng xuất
            new AlertDialog.Builder(settings.this)
                    .setTitle("Xác nhận đăng xuất")
                    .setMessage("Bạn có chắc chắn muốn đăng xuất không?")
                    .setPositiveButton("Có", (dialog, which) -> {
                        // Nếu người dùng nhấn "Có", chuyển sang màn hình đăng nhập
                        startActivity(new Intent(settings.this, login.class));
                        finish(); // Tùy chọn: đóng activity hiện tại
                    })
                    .setNegativeButton("Không", (dialog, which) -> {
                        // Nếu người dùng nhấn "Không", đóng hộp thoại
                        dialog.dismiss();
                    })
                    .show(); // Hiển thị hộp thoại
        });
    }
}