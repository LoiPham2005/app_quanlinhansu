package manchinh;

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

import poly.edu.vn.asm.R;
import nhanvien.nhanvien;
import phongban.phongban;
import settings.settings;
import tintuc.tintuc;
import tuyendung.tuyendung;

public class home extends AppCompatActivity {
Button btnDetail1, btnDetail2, btnDetail3;
ImageView imgTinTuc, imgCaiDat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);


        btnDetail1 = findViewById(R.id.btn_detail1);
        btnDetail2 = findViewById(R.id.btn_detail2);
        btnDetail3 = findViewById(R.id.btn_detail3);

        imgTinTuc = findViewById(R.id.icon_news);
        imgCaiDat = findViewById(R.id.icon_settings);

        btnDetail1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(home.this, phongban.class));
            }
        });

        btnDetail2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(home.this, nhanvien.class));
            }
        });

        btnDetail3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(home.this, tuyendung.class));
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        imgTinTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(home.this, tintuc.class));
            }
        });

        imgCaiDat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(home.this, settings.class));

            }
        });
    }
}