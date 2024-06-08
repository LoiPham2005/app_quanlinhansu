package phongban;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

import phongban.dulieuchung.NhanVien;
import phongban.dulieuchung.adapter_nhanVien;
import poly.edu.vn.asm.R;

public class maketing extends AppCompatActivity {
    private ListView lvMaketing;
    private adapter_nhanVien adapter;
    private List<NhanVien> maketingList;

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

        lvMaketing = findViewById(R.id.lv_maketing);
        maketingList = new ArrayList<>();
        populateDev();

        adapter = new adapter_nhanVien(this, (ArrayList<NhanVien>) maketingList);
        lvMaketing.setAdapter(adapter);

    }
    private void populateDev() {
        maketingList.add(new NhanVien("PH01", "Phạm Đức Lợi", "Leder"));
        maketingList.add(new NhanVien("PH02", "Phạm Văn Thành ", "staff"));
        maketingList.add(new NhanVien("PH03", "Nguyễn Trung Duy", "staff"));
        maketingList.add(new NhanVien("PH04", "Lê Thị Hiền", "staff"));
        maketingList.add(new NhanVien("PH05", "Đàm Vĩnh Hưng", "staff"));
    }
}