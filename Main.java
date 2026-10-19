import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * =========================================================
 * BÀI TẬP LỚN / ĐỒ ÁN: QUẢN LÝ THÍ SINH DỰ THI ĐẠI HỌC
 * Danh sách thành viên nhóm:
 * 1. Đặng Xuân Hiệp
 * 2. Bùi Gia Linh
 * 3. Bùi Văn Cường
 * =========================================================
 */

// --- LỚP CƠ SỞ: THÍ SINH ---
class ThiSinh {
    protected String soBaoDanh;
    protected String hoTen;
    protected String diaChi;
    protected int mucUuTien;

    public ThiSinh(String soBaoDanh, String hoTen, String diaChi, int mucUuTien) {
        this.soBaoDanh = soBaoDanh;
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        this.mucUuTien = mucUuTien;
    }

    public void hienThiThongTin() {
        System.out.printf("SBD: %-8s | Họ tên: %-18s | Địa chỉ: %-12s | Ưu tiên: %d\n", 
                soBaoDanh, hoTen, diaChi, mucUuTien);
    }

    public String getSoBaoDanh() {
        return soBaoDanh;
    }
}

// --- KHỐI A ---
class ThiSinhKhoiA extends ThiSinh {
    public ThiSinhKhoiA(String soBaoDanh, String hoTen, String diaChi, int mucUuTien) {
        super(soBaoDanh, hoTen, diaChi, mucUuTien);
    }

    @Override
    public void hienThiThongTin() {
        super.hienThiThongTin();
        System.out.println("   └─ Khối thi: Khối A (Môn thi: Toán, Lý, Hoá)");
    }
}

// --- KHỐI B ---
class ThiSinhKhoiB extends ThiSinh {
    public ThiSinhKhoiB(String soBaoDanh, String hoTen, String diaChi, int mucUuTien) {
        super(soBaoDanh, hoTen, diaChi, mucUuTien);
    }

    @Override
    public void hienThiThongTin() {
        super.hienThiThongTin();
        System.out.println("   └─ Khối thi: Khối B (Môn thi: Toán, Hoá, Sinh)");
    }
}

// --- KHỐI C ---
class ThiSinhKhoiC extends ThiSinh {
    public ThiSinhKhoiC(String soBaoDanh, String hoTen, String diaChi, int mucUuTien) {
        super(soBaoDanh, hoTen, diaChi, mucUuTien);
    }

    @Override
    public void hienThiThongTin() {
        super.hienThiThongTin();
        System.out.println("   └─ Khối thi: Khối C (Môn thi: Văn, Sử, Địa)");
    }
}

// --- LỚP QUẢN LÝ TUYỂN SINH ---
class TuyenSinh {
    private List<ThiSinh> danhSach = new ArrayList<>();

    // Thêm mới thí sinh với kiểm tra trùng số báo danh
    public void themThiSinh(Scanner scanner) {
        System.out.println("\n--- CHỌN KHỐI THI ---");
        System.out.println("1. Khối A (Toán, Lý, Hoá)");
        System.out.println("2. Khối B (Toán, Hoá, Sinh)");
        System.out.println("3. Khối C (Văn, Sử, Địa)");
        System.out.print("Lựa chọn của bạn (1-3): ");
        
        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("❌ Lỗi: Lựa chọn không hợp lệ. Hủy thêm mới!");
            return;
        }

        if (choice < 1 || choice > 3) {
            System.out.println("❌ Lỗi: Khối thi không tồn tại!");
            return;
        }

        System.out.print("Nhập số báo danh: ");
        String sbd = scanner.nextLine().trim();

        // Kiểm tra trùng SBD
        for (ThiSinh ts : danhSach) {
            if (ts.getSoBaoDanh().equalsIgnoreCase(sbd)) {
                System.out.println("❌ Lỗi: Số báo danh '" + sbd + "' đã tồn tại trong hệ thống!");
                return;
            }
        }

        System.out.print("Nhập họ tên: ");
        String hoTen = scanner.nextLine().trim();
        System.out.print("Nhập địa chỉ: ");
        String diaChi = scanner.nextLine().trim();
        
        int mucUuTien;
        while (true) {
            try {
                System.out.print("Nhập mức ưu tiên: ");
                mucUuTien = Integer.parseInt(scanner.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: Mức ưu tiên phải là một số nguyên. Nhập lại!");
            }
        }

        ThiSinh thiSinhMoi = null;
        switch (choice) {
            case 1:
                thiSinhMoi = new ThiSinhKhoiA(sbd, hoTen, diaChi, mucUuTien);
                break;
            case 2:
                thiSinhMoi = new ThiSinhKhoiB(sbd, hoTen, diaChi, mucUuTien);
                break;
            case 3:
                thiSinhMoi = new ThiSinhKhoiC(sbd, hoTen, diaChi, mucUuTien);
                break;
        }

        if (thiSinhMoi != null) {
            danhSach.add(thiSinhMoi);
            System.out.println("✅ Thêm thí sinh thành công!");
        }
    }

    // Hiển thị danh sách thí sinh
    public void hienThiDanhSach() {
        if (danhSach.isEmpty()) {
            System.out.println("\n📌 Danh sách thí sinh hiện đang trống!");
            return;
        }
        System.out.println("\n=================== DANH SÁCH THÍ SINH DỰ THI ===================");
        for (int i = 0; i < danhSach.size(); i++) {
            System.out.print((i + 1) + ". ");
            danhSach.get(i).hienThiThongTin();
            System.out.println("-----------------------------------------------------------------");
        }
    }

    // Tìm kiếm theo số báo danh
    public void timKiemTheoSoBaoDanh(Scanner scanner) {
        System.out.print("\nNhập số báo danh cần tìm: ");
        String sbd = scanner.nextLine().trim();

        boolean found = false;
        System.out.println("\n--- KẾT QUẢ TÌM KIẾM ---");
        for (ThiSinh ts : danhSach) {
            if (ts.getSoBaoDanh().equalsIgnoreCase(sbd)) {
                ts.hienThiThongTin();
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("🔍 Không tìm thấy thí sinh có số báo danh: " + sbd);
        }
    }
}

// --- HÀM MAIN CHÍNH ---
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TuyenSinh quanLy = new TuyenSinh();
        int luaChon = 0;

        do {
            System.out.println("\n=========================================================");
            System.out.println("               HỆ THỐNG QUẢN LÝ THÍ SINH                 ");
            System.out.println("   Nhóm thực hiện: Đặng Xuân Hiệp - Bùi Gia Linh - Bùi Văn Cường");
            System.out.println("=========================================================");
            System.out.println("1. Thêm mới thí sinh");
            System.out.println("2. Hiển thị thông tin thí sinh và khối thi");
            System.out.println("3. Tìm kiếm theo số báo danh");
            System.out.println("4. Thoát khỏi chương trình");
            System.out.print("👉 Lựa chọn của bạn (1-4): ");

            try {
                luaChon = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: Vui lòng nhập một số nguyên từ 1 đến 4!");
                continue;
            }

            switch (luaChon) {
                case 1:
                    quanLy.themThiSinh(scanner);
                    break;
                case 2:
                    quanLy.hienThiDanhSach();
                    break;
                case 3:
                    quanLy.timKiemTheoSoBaoDanh(scanner);
                    break;
                case 4:
                    System.out.println("\n👋 Cảm ơn nhóm bạn đã sử dụng chương trình. Tạm biệt!");
                    break;
                default:
                    System.out.println("❌ Lựa chọn không hợp lệ! Vui lòng chọn từ 1 đến 4.");
            }
        } while (luaChon != 4);

        scanner.close();
    }
}
