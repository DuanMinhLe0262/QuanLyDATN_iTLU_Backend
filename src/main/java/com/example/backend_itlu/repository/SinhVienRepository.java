package com.example.backend_itlu.repository;

import com.example.backend_itlu.entity.SinhVien;
import com.example.backend_itlu.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SinhVienRepository extends JpaRepository<SinhVien, String> {

    boolean existsByMaSinhVien(String maSinhVien);
    Optional<SinhVien> findByUserId(String userId);
    Optional<SinhVien> findByMaSinhVien(String maSinhVien);
}
