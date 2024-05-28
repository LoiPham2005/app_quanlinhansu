package phongban;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import manchinh.home;
import poly.edu.vn.asm.R;
import settings.settings;
import tintuc.tintuc;

public class phongban extends AppCompatActivity {
    TextView tvMaketing, tvDeveloper, tvFinance;
    ImageView imgHome,imgTinTuc, imgCaiDat;
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
}