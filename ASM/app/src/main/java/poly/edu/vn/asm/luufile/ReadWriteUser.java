package poly.edu.vn.asm.luufile;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ReadWriteUser {
    Context context;

    public ReadWriteUser(Context context) {
        this.context = context;
    }

    public void writeUser(Context context, String fileName, User user) {
        List<User> list = new ArrayList<>();

        try {
            FileOutputStream fileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            // Thêm người dùng vào danh sách
            list.add(user);

            // Ghi danh sách người dùng vào tệp
            objectOutputStream.writeObject(list);

            // Đóng luồng
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<User> readUser(Context context, String fileName) {
        List<User> objectList = new ArrayList<>();

        try {
            FileInputStream fileInputStream = context.openFileInput(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            objectList = (List<User>) objectInputStream.readObject();

            objectInputStream.close();
            fileInputStream.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return objectList;
    }


}
