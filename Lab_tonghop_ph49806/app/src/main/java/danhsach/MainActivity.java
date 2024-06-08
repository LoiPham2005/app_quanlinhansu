package danhsach;

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

import poly.edu.vn.lab_tonghop_ph49806.R;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    AdapterStudent adapter;
    List<Student> studentsList;
    int currentEditPosition; // Lưu trữ vị trí của sinh viên đang được sửa

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listView = findViewById(R.id.lvStudents);
        studentsList = new ArrayList<>();

        populateStudentList();

        // Sử dụng launcherEditStudent để cập nhật sinh viên
        adapter = new AdapterStudent(this, studentsList, launcherEditStudent);
        listView.setAdapter(adapter);
    }

    private void populateStudentList() {
        studentsList.add(new Student("ph49806", "Phạm Đức Lợi", "MD19302"));
        studentsList.add(new Student("ph49235", "Phạm Văn Thành", "MD19305"));
        studentsList.add(new Student("ph50735", "Lê Văn Phúc", "MD19301"));
    }

    private final ActivityResultLauncher<Intent> launcherAddStudent = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Intent data = result.getData();
                        String id = data.getStringExtra("id");
                        String name = data.getStringExtra("name");
                        String lop = data.getStringExtra("lop");

                        if (id != null && name != null && lop != null) {
                            studentsList.add(new Student(id, name, lop));
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
            }
    );

    private final ActivityResultLauncher<Intent> launcherEditStudent = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Intent data = result.getData();
                        String id = data.getStringExtra("id");
                        String name = data.getStringExtra("name");
                        String lop = data.getStringExtra("lop");

                        if (currentEditPosition != -1 && id != null && name != null && lop != null) {
                            Student student = studentsList.get(currentEditPosition);
                            student.setId(id);
                            student.setHoTen(name);
                            student.setLop(lop);
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
            }
    );
}
