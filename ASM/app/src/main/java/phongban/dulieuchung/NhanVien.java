package phongban.dulieuchung;

public class NhanVien {
    private String maNV;
    private String hoTen;
    private String chucVu;

    public NhanVien(String maNV, String hoTen, String chucVu) {
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.chucVu = chucVu;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }
}
