package bai2;

import android.widget.Button;

import java.io.Serializable;

public class Student  {
    private static int idCounter = 0;
    private int id;
    private String branch;
    private String name;
    private String address;

    public Student(String branch, String name, String address) {
        this.id = ++idCounter;  // Tự động tăng ID mỗi khi tạo mới
        this.branch = branch;
        this.name = name;
        this.address = address;
    }

    public static int getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(int idCounter) {
        Student.idCounter = idCounter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
