package danhsach;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;

import java.util.List;

import poly.edu.vn.lab_tonghop_ph49806.R;

public class AdapterStudent extends BaseAdapter {
    Context context;
    List<Student> studentList;
    ActivityResultLauncher<Intent> launcher;

    public AdapterStudent(Context context, List<Student> studentList, ActivityResultLauncher<Intent> launcher) {
        this.context = context;
        this.studentList = studentList;
        this.launcher = launcher;
    }

    @Override
    public int getCount() {
        return studentList.size();
    }

    @Override
    public Object getItem(int position) {
        return studentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        if (convertView != null) {
            row = convertView;
        } else {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(R.layout.activity_adapter_student, parent, false);
        }

        Student student = studentList.get(position);

        TextView tvID = row.findViewById(R.id.tv_ID);
        TextView tvName = row.findViewById(R.id.tv_name);
        TextView tvClass = row.findViewById(R.id.tv_Lop);
        Button btnUpdate = row.findViewById(R.id.btn_update);
        Button btnDelete = row.findViewById(R.id.btn_delete);

        tvID.setText("Mã: " + student.getId());
        tvName.setText("Họ tên: " + student.getHoTen());
        tvClass.setText("Lớp: " + student.getLop());

        btnDelete.setOnClickListener(v -> confirmDelete(position));

        btnUpdate.setOnClickListener(v -> {
            Intent intent = new Intent(context, SuaThongTin.class);
            intent.putExtra("id", student.getId());
            intent.putExtra("name", student.getHoTen());
            intent.putExtra("lop", student.getLop());

            // Truyền vị trí hiện tại vào launcher để MainActivity biết cần cập nhật sinh viên nào
            ((MainActivity) context).currentEditPosition = position;
            launcher.launch(intent);
        });

        return row;
    }

    private void confirmDelete(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Xác nhận xóa");
        builder.setMessage("Bạn có chắc chắn muốn xóa?");

        builder.setPositiveButton("Đồng ý xoá", (dialog, which) -> {
            studentList.remove(position);
            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
            notifyDataSetChanged();
        });

        builder.setNegativeButton("Không xoá", (dialog, which) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
