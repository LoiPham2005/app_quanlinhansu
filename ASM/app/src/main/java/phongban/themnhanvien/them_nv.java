package phongban.themnhanvien;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import phongban.developer;
import poly.edu.vn.asm.R;
import poly.edu.vn.asm.login;

public class them_nv extends AppCompatActivity {
    EditText edtMa, edtHoTen, edtChucVu;
    Button btnTao;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.layout_them_nv);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // toobar
        toolbar = findViewById(R.id.toolbar);
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
        edtMa = findViewById(R.id.edt_maNV);
        edtHoTen = findViewById(R.id.edt_hoten);
        edtChucVu = findViewById(R.id.edt_chucvu);
        btnTao = findViewById(R.id.btn_tao);

        btnTao.setOnClickListener(v -> {
            String ma = edtMa.getText().toString();
            String hoTen = edtHoTen.getText().toString();
            String chucVu = edtChucVu.getText().toString();

            if (ma.isEmpty() || hoTen.isEmpty() || chucVu.isEmpty()) {
                if (ma.isEmpty()) {
                    edtMa.setError("vui lòng nhập ma");
                }
                if (hoTen.isEmpty()) {
                    edtHoTen.setError("vui lòng nhập họ tên");
                }
                if (chucVu.isEmpty()) {
                    edtChucVu.setError("vui lòng nhập chức vụ");
                }
                return;
            }

            Toast.makeText(them_nv.this, "Thêm thành công", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(them_nv.this, developer.class);
            Bundle bundle = new Bundle();
            bundle.putString("ma",ma);
            bundle.putString("hoten",hoTen);
            bundle.putString("chucvu",chucVu);
            intent.putExtras(bundle);
            setResult(RESULT_OK, intent);
            finish();
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
        }
        return super.onOptionsItemSelected(item);
    }

}
