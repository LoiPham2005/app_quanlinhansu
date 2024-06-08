package danhsach;

public class Student {
    String id;
    String hoTen;
    String lop;

    public Student(String id, String hoTen, String lop) {
        this.id = id;
        this.hoTen = hoTen;
        this.lop = lop;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }
}
