package com.example.lab1_pd06976_nguyenkhactrung.database;

public class Data_SQLite {
    public static final  String INSERT_THU_THU = "Insert into ThuThu(maTT, hoTen, matKhau) values" +
            "('Đức', 'Cu Duy Đức','123456')," +
            "('Trung', 'Nguyễn Khắc Trung','123456')," +
            "('Toan', 'Bùi Thị Toan','123456')," +
            "('Trang', 'Nguyễn Thị Thuỳ Trang', '123456')";

    public static final  String INSERT_THANH_VIEN = "Insert into ThanhVien(hoTen, namSinh) values" +
            "('Nguyễn Anh Thắng','2002')," +
            "('Nguyễn Văn Toàn','2002')," +
            "('Huỳnh Trần Thảo Trinh','2002')," +
            "('Cù Duy Đức','2002')," +
            "('Võ Lê Phương Nam','2002')," +
            "('Nguyễn Minh Đức','2002')," +
            "('Hồ Thị Hoàng Ngọc','2002')," +
            "('Đậu Đức Trung','2002')," +
            "('Nguyễn Tăng Thu Duyên','2002')," +
            "('Trần Thị Oanh','2002')," +
            "('Nguyễn Khắc Trung','2002')," +
            "('Lê Quang Khánh','2002')," +
            "('Phạm Ngọc Hải','2002')," +
            "('Phan Hùng Thành','2002')," +
            "('Phan Mùi','2002')," +
            "('Phạm Văn Truy','2002')," +
            "('Phạm Ngọc Khải','2002')," +
            "('Nguyễn Thành Hiếu','2002')," +
            "('Nguyễn Tùng Sơn','2002')," +
            "('Phan Anh Tuấn','2002')," +
            "('Võ Ngọc Hiếu','2002')," +
            "('Nguyễn Bùi Văn Sỹ','2002')," +
            "('Trương Văn Triệu Vĩ','2002')," +
            "('Vũ Minh Hùng','2002')," +
            "('Lê Đức Tài','2002')," +
            "('Dương Quang Huy','2002')," +
            "('Nguyễn Thị Quý','2002')," +
            "('Bùi Văn Thái','2002')";
    public static final String INSERT_LOAI_SACH = "insert into LoaiSach(tenLoai) values" +
            "('Tiếng Anh cơ bản')," +
            "('Tiếng Anh nâng cao')," +
            "('Lập trình cơ bản')," +
            "('Lập trình android')," +
            "('Lập trình java')," +
            "('Lập trình web')";
    public  static  final  String INSERT_SACH = "insert into Sach(tenSach, giaThue, maLoai) values" +
            "('Lập trình Java cơ bản ','2000','5')," +
            "('Lập trình Java nâng cao ','2000','5')," +
            "('Lập trình mạng với java ','2000','5')," +
            "('Lập trình desktop với Swing ','2000','3')," +
            "('Dự án với công nghệ MS.NET MVC ','2000','1')," +
            "('Dự án với công nghệ Spring MVC ','2000','1')," +
            "('Dự án với công nghệ Servlet/JSP ','2000','5')," +
            "('Dự án với AngularJS & WebAPI ','2000','6')," +
            "('Dự án với Swing & JDBC ','2000','5')," +
            "('Dự án với WindownForm ','2000','1')," +
            "('Cơ sở dữ liêuh SQL Server ','2000','3')," +
            "('Lập trình JDBC ','2000','4')," +
            "('Lập trình cơ sở dữ liệu Hibernate ','2000','4')," +
            "('Lập trình web với Servlet/JSP ','2000','1')," +
            "('Lập trình Spring MVC ','2000','5')," +
            "('Lập trình MS.NET MVC ','2000','1')," +
            "('Xây dựng Web API với Spring MVC & ASP.NET MVC ','2000','3')," +
            "('Thiết kế web với HTML và CSS ','2000','6')," +
            "('Thiết kế web với Bootstrap ','2000','6')," +
            "('Thiết kế front-end với javaScript và jQuery ','2000','6')," +
            "('Lập trình AngularJS ','2000','6')";
    public  static  final  String INSERT_PHIEU_MUON = "insert into PhieuMuon(maTT, maTV, maSach, tienThue, ngay, traSach) values" +
            "('admin','1 ','1','2000','2023-09-30',1)," +
            "('admin','2 ','2','2500','2023-09-29',1)," +
            "('admin','3 ','3','3000','2023-09-28',0)," +
            "('admin','4 ','4','3200','2023-09-27',0)," +
            "('admin','5 ','5','4000','2023-09-26',0)," +
            "('admin','6 ','6','2000','2023-09-25',0)," +
            "('admin','7 ','7','3500','2023-09-24',1)," +
            "('admin','8 ','8','3000','2023-09-23',1)," +
            "('admin','9 ','9','2800','2023-09-22',0)," +
            "('admin','10 ','10','3200','2023-09-21',1)," +
            "('admin','11 ','11','2000','2023-09-20',0)," +
            "('admin','12 ','12','2500','2023-09-19',1)," +
            "('admin','13 ','13','3000','2023-09-18',0)," +
            "('admin','14 ','14','3200','2023-09-17',1)," +
            "('admin','15 ','15','4000','2023-09-16',1)," +
            "('admin','16 ','16','2000','2023-09-15',0)," +
            "('admin','17 ','17','3500','2023-09-14',0)," +
            "('admin','18 ','18','3000','2023-09-13',0)," +
            "('admin','19 ','19','2800','2023-09-12',0)," +
            "('admin','20 ','20','3200','2023-09-11',1)," +
            "('admin','21 ','1','2000','2023-09-10',1)," +
            "('admin','22 ','2','2500','2023-09-09',0)," +
            "('admin','23 ','3','3000','2023-09-08',1)," +
            "('admin','24 ','4','3200','2023-09-07',0)," +
            "('admin','25 ','5','4000','2023-09-06',1)," +
            "('admin','26 ','6','2000','2023-09-05',0)," +
            "('admin','27 ','7','3500','2023-09-04',1)," +
            "('admin','18 ','19','2800','2023-09-03',1)";
}

