package phongban.dulieuchung;

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

import java.util.ArrayList;

import phongban.developer;
import phongban.suanhanvien.suanhanvien;
import poly.edu.vn.asm.R;

public class adapter_nhanVien extends BaseAdapter {
    Context context;
    ArrayList<NhanVien> list;
    ActivityResultLauncher<Intent> launcher;


    public adapter_nhanVien(Context context, ArrayList<NhanVien> list, ActivityResultLauncher<Intent> launcher) {
        this.context = context;
        this.list = list;
        this.launcher = launcher;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
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
            row = inflater.inflate(R.layout.developer_phu, parent, false);
        }
        TextView tvID = row.findViewById(R.id.tv_maNV);
        TextView tvName = row.findViewById(R.id.tv_name);
        TextView tvAddress = row.findViewById(R.id.tv_chucvu);
        Button btnUpdate = row.findViewById(R.id.btn_update);
        Button btnDelete = row.findViewById(R.id.btn_delete);

        NhanVien devo = list.get(position);

        tvID.setText("Mã: " + devo.getMaNV());
        tvName.setText("Họ tên: " + devo.getHoTen());
        tvAddress.setText("Chức vụ: " + devo.getChucVu());

        btnDelete.setOnClickListener(v -> confirmDelete(position));

        btnUpdate.setOnClickListener(v -> {
            // Gọi phương thức sửa thông tin nhân viên
            Intent intent = new Intent(context, suanhanvien.class);
            // Đính kèm thông tin nhân viên cần sửa vào Intent
            intent.putExtra("ma", devo.getMaNV());
            intent.putExtra("ten", devo.getHoTen());
            intent.putExtra("chucvu", devo.getChucVu());

            launcher.launch(intent);
        });

        return row;
    }


    private void confirmDelete(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Xác nhận xóa");
        builder.setMessage("Bạn có chắc chắn muốn xóa?");

        builder.setPositiveButton("Đồng ý xoá", (dialog, which) -> {
            list.remove(position);
            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
            notifyDataSetChanged();
        });

        builder.setNegativeButton("Không xoá", (dialog, which) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
