package com.example.backend_itlu.repository;

import com.example.backend_itlu.entity.TaiLieu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaiLieuRepository extends JpaRepository<TaiLieu, String> {
    List<TaiLieu> findBySinhVienId(String sinhVienId);
}
