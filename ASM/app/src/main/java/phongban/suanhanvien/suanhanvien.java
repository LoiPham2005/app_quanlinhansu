package phongban.suanhanvien;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import poly.edu.vn.asm.R;

public class suanhanvien extends AppCompatActivity {
    Button btnUpdate;
    EditText edtTen, edtChucVu;
    TextView tvThongTinCu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.layout_sua_nhan_vien);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        // toobar
        Toolbar toolbar = findViewById(R.id.toolbar);
        // gắn toolbar vào activity
        setSupportActionBar(toolbar);

        ActionBar bar = getSupportActionBar();
        // thiết lập cho toolbar
//        bar.setIcon(android.R.drawable.ic_dialog_alert);

        // ẩn tiêu đề nếu cần thiết
        bar.setDisplayShowTitleEnabled(false);

        // gắn toolbar vào activity
        setSupportActionBar(toolbar);

        bar.setDisplayHomeAsUpEnabled(true);  // hiện thị nút back


        //--------------------------------
        tvThongTinCu = findViewById(R.id.tv_thongtincu);
        edtTen = findViewById(R.id.edt_hoten);
        edtChucVu = findViewById(R.id.edt_chucvu);
        btnUpdate = findViewById(R.id.btn_sua);

        // Lấy dữ liệu từ Intent
        Intent intent = getIntent();
        String id = intent.getStringExtra("ma");
        String name = intent.getStringExtra("ten");
        String chucVu = intent.getStringExtra("chucvu");


        // hiển thị thông tin cũ
        tvThongTinCu.setText("Edit information: \n" +
                "\nID: " + id +
                "\nFullname: " + name +
                "\nPosition: " + chucVu);

        // Gán dữ liệu vào các EditText
//        edtMa.setText(id);
        edtTen.setText(name);
        edtChucVu.setText(chucVu);

        OnBackPressedDispatcher dispatcher = this.getOnBackPressedDispatcher();
        dispatcher.addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent resultIntent = new Intent();
//                resultIntent.putExtra("ma", edtMa.getText().toString());
                resultIntent.putExtra("ma", id);
                resultIntent.putExtra("hoten", edtTen.getText().toString());
                resultIntent.putExtra("chucvu", edtChucVu.getText().toString());
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });

        // Xử lý sự kiện nút lưu
        btnUpdate.setOnClickListener(view -> dispatcher.onBackPressed());
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
        }
        return super.onOptionsItemSelected(item);
    }
}