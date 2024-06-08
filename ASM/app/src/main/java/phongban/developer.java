package phongban;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

import phongban.dulieuchung.adapter_nhanVien;
import phongban.dulieuchung.NhanVien;
import poly.edu.vn.asm.R;
import phongban.themnhanvien.them_nv;

public class developer extends AppCompatActivity {
    private ListView lvDeveloper;
    private adapter_nhanVien adapter;
    private ArrayList<NhanVien> developerList;
    Button btnThem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_developer);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lvDeveloper = findViewById(R.id.lv_dev);
        btnThem = findViewById(R.id.btn_them);

        developerList = new ArrayList<>();


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            String ma = bundle.getString("ma");
            String ten = bundle.getString("ten");
            String chucvu = bundle.getString("chucvu");

            NhanVien nv = new NhanVien(ma, ten, chucvu);
            developerList.add(nv);
            adapter.notifyDataSetChanged();
        }

        populateDev();

        adapter = new adapter_nhanVien(this, developerList, launcherEdit);
        lvDeveloper.setAdapter(adapter);

        btnThem.setOnClickListener(v -> {
            startActivity(new Intent(developer.this, them_nv.class));
        });



    }
    private void populateDev() {
        developerList.add(new NhanVien("PH01", "Phạm Đức Lợi", "Leder"));
        developerList.add(new NhanVien("PH02", "Phạm Văn Thành ", "staff"));
        developerList.add(new NhanVien("PH03", "Nguyễn Trung Duy", "staff"));
        developerList.add(new NhanVien("PH04", "Lê Thị Hiền", "staff"));
    }

    ActivityResultLauncher<Intent> launcherEdit = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult o) {
                    if(o.getResultCode() == RESULT_OK && o.getData() != null){
                        Intent data = getIntent();
                        String id = data.getStringExtra("ma");
                        String hoTen = data.getStringExtra("hoten");
                        String chucVu = data.getStringExtra("chucvu");

//                        NhanVien nv = new NhanVien(id, hoTen, chucVu);
//                        int index = developerList.indexOf(nv);
//                        developerList.set(index, nv);
                        for (NhanVien nhanVien : developerList){
                            if(nhanVien.getMaNV()==id){
                                nhanVien.setMaNV(id);
                                nhanVien.setHoTen(hoTen);
                                nhanVien.setChucVu(chucVu);
                                adapter.notifyDataSetChanged();
                            }
                        }
                    }
                }
            }
    );

}