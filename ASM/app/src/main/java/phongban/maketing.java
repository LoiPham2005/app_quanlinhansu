package phongban;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

import phongban.dulieuchung.NhanVien;
import phongban.dulieuchung.adapter_nhanVien;
import phongban.themnhanvien.them_nv;
import poly.edu.vn.asm.R;
import poly.edu.vn.asm.login;

public class maketing extends AppCompatActivity {
    ListView lvDeveloper;
    adapter_nhanVien adapter;
    ArrayList<NhanVien> developerList;
    Button btnThem;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_maketing);
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

        lvDeveloper = findViewById(R.id.lv_dev);
        developerList = new ArrayList<>();


        btnThem = findViewById(R.id.btn_them);

        btnThem.setOnClickListener(v -> {
            launcherAddStudent.launch(new Intent(maketing.this, them_nv.class));
        });

        populateDev();

        adapter = new adapter_nhanVien(this, developerList, launcherEdit);
        lvDeveloper.setAdapter(adapter);

    }
    private void populateDev() {
        developerList.add(new NhanVien("PH01", "Phạm Đức Lợi00", "Leder"));
        developerList.add(new NhanVien("PH02", "Phạm Văn Thành ", "staff"));
        developerList.add(new NhanVien("PH03", "Nguyễn Trung Duy", "staff"));
        developerList.add(new NhanVien("PH04", "Lê Thị Hiền", "staff"));
    }

    // sửa thông tin
    // developer.java
    ActivityResultLauncher<Intent> launcherEdit = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Intent data = result.getData();
                        String ma = data.getStringExtra("ma");
                        String hoTen = data.getStringExtra("hoten");
                        String chucVu = data.getStringExtra("chucvu");

                        // Tìm nhân viên theo mã và cập nhật thông tin
                        for (NhanVien nhanVien : developerList) {
                            if (nhanVien.getMaNV().equals(ma)) {
                                nhanVien.setHoTen(hoTen);
                                nhanVien.setChucVu(chucVu);
                                break; // Thoát khỏi vòng lặp sau khi cập nhật
                            }
                        }
                        // Thông báo cho adapter biết dữ liệu đã thay đổi
                        adapter.notifyDataSetChanged();
                    }
                }
            }
    );


    //thêm danh sách list
    private final ActivityResultLauncher<Intent> launcherAddStudent = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Intent data = result.getData();
                        String ma = data.getStringExtra("ma");
                        String hoten = data.getStringExtra("hoten");
                        String chucVu = data.getStringExtra("chucvu");

                        if (ma != null && hoten != null && chucVu != null) {
                            developerList.add(new NhanVien(ma, hoten, chucVu));
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
            }
    );

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
            startActivity(new Intent(maketing.this, login.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}