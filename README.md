# 2023_ChieuT6_CNPM_Nhom14

## How to run code?
<br>
-> Import code to IDE (Eclipse)
<br>
-> src/main/resources/application.properties -> Bật "spring.jpa.hibernate.ddl-auto=create-drop" Tắt "spring.jpa.hibernate.ddl-auto=none" (Ctrl + /) Để JPA tự động tạo table. Sau lần chạy đầu tiên Tắt "spring.jpa.hibernate.ddl-auto=create-drop" Bật "spring.jpa.hibernate.ddl-auto=none" để tránh tạo tại bảng gây mất hết dữ liệu.
<br>
-> Run file WebThiTracNghiemApplication.java
Nếu có lỗi driver class name
<br>
Thêm "spring.datasource.driver-class-name=com.mysql.jdbc.Driver" vào file application.properties
<br>
-> Insert data vào csdl (file datav1.sql)
<br>
Tắt Server và chạy lại file WebThiTracNghiemApplication.java

