package phongban;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import manchinh.home;
import poly.edu.vn.asm.R;
import poly.edu.vn.asm.login;
import settings.settings;
import tintuc.tintuc;

public class phongban extends AppCompatActivity {
    TextView tvMaketing, tvDeveloper, tvFinance;
    ImageView imgHome,imgTinTuc, imgCaiDat;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_phongban);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tvMaketing = findViewById(R.id.tv_maketing);
        tvDeveloper = findViewById(R.id.tv_devoloper);
        tvFinance = findViewById(R.id.tv_finance);
        imgHome = findViewById(R.id.icon_home);
        imgTinTuc = findViewById(R.id.icon_news);
        imgCaiDat = findViewById(R.id.icon_settings);

        // toobar
        toolbar = findViewById(R.id.toolbar);
        // gắn toolbar vào activity
        setSupportActionBar(toolbar);

        ActionBar bar = getSupportActionBar();
        // thiết lập cho toolbar
        bar.setTitle("tiêu đề ứng dụng");
//        bar.setIcon(android.R.drawable.ic_dialog_alert);

        // ẩn tiêu đề nếu cần thiết
        bar.setDisplayShowTitleEnabled(false);

        // gắn toolbar vào activity
        setSupportActionBar(toolbar);

        bar.setDisplayHomeAsUpEnabled(true);  // hiện thị nút back

        tvMaketing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(phongban.this, maketing.class));
            }
        });

        tvDeveloper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(phongban.this, developer.class));
            }
        });

        tvFinance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(phongban.this, finance.class));
            }
        });

        imgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(phongban.this, home.class));
            }
        });

        imgTinTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(phongban.this, tintuc.class));
            }
        });

        imgCaiDat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(phongban.this, settings.class));
            }
        });
    }

    //// toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // Handle the back button press
            finish(); // Finish the activity to go back
            return true;
        } else if (id == R.id.it_3gạch) {
            startActivity(new Intent(phongban.this, login.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}