package com.example.backend_itlu.service;

import com.example.backend_itlu.entity.*;
import com.example.backend_itlu.repository.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FakeData implements CommandLineRunner {

    KhoaRepository khoaRepo;
    BoMonRepository boMonRepo;
    NganhRepository nganhRepo;
    LopRepository lopRepo;
    RoleRepository roleRepo;
    UserRepository userRepo;
    SinhVienRepository sinhVienRepo;
    PasswordEncoder passwordEncoder;



    @Override
    public void run(String... args) throws Exception {
        if (khoaRepo.count() == 0) {
            // Tạo Khoa
            Khoa khoaCNTT = new Khoa();
            khoaCNTT.setTenKhoa("Công nghệ thông tin");
            khoaRepo.save(khoaCNTT);

            // Tạo Bộ môn
            BoMon bomonCNPM = new BoMon();
            bomonCNPM.setTenBoMon("Công nghệ phần mềm");
            bomonCNPM.setKhoa(khoaCNTT);
            boMonRepo.save(bomonCNPM);

            // Tạo Ngành
            Nganh nganhKTPM = new Nganh();
            nganhKTPM.setTenNganh("Kỹ thuật phần mềm");
            nganhKTPM.setBomon(bomonCNPM);
            nganhRepo.save(nganhKTPM);

            // Tạo Lớp
            Lop lop = new Lop();
            lop.setTenLop("63KTPM1");
            lop.setKhoaHoc("K63");
            lop.setNganh(nganhKTPM);
            lopRepo.save(lop);

            Role role = new Role();
            role.setName("STUDENT");
            roleRepo.save(role);

            // Tạo user
            User user = new User();
            user.setEmail("2151173756@e.tlu.edu.vn");
            user.setPassword(passwordEncoder.encode("12345678"));
            user.setRoles(Set.of(role));
            userRepo.save(user);


            // Tạo sinh viên
            SinhVien sv = new SinhVien();
            sv.setHoTen("Le Minh Duan");
            sv.setMaSinhVien("2151173756");
            sv.setUser(user);
            sv.setLop(lop);
            sinhVienRepo.save(sv);

            System.out.println(" Dữ liệu mẫu đã được thêm vào cơ sở dữ liệu.");
        }
    }
}