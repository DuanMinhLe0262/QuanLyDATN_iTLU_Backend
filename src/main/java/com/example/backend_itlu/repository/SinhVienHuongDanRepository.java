package com.example.backend_itlu.repository;

import com.example.backend_itlu.entity.SinhVienHuongDan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SinhVienHuongDanRepository extends JpaRepository<SinhVienHuongDan, String> {
    int countBySinhVienId(String sinhVienHuongDanId);
    boolean existsBySinhVienIdAndGiangVienId(String sinhVienId, String giangVienId);
}
