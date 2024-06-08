package poly.edu.vn.lab5_ph49806;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SchoolSpinnerAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<School> list;
    public SchoolSpinnerAdapter(Context context, ArrayList<School> list) {
        this.context = context;
        this.list = list;
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
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.bai1_item_spinner, parent, false);
        }

        ImageView ivLogo = convertView.findViewById(R.id.iv_logo);
        TextView tvName = convertView.findViewById(R.id.tv_name);

        School school = list.get(position);
        ivLogo.setImageResource(school.getImage());
        tvName.setText("FPoly " + school.getName());

        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return super.getDropDownView(position, convertView, parent);
    }
}
