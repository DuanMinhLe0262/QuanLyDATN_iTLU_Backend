package com.example.backend_itlu.repository;

import com.example.backend_itlu.entity.DeTai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DeTaiRepository extends JpaRepository<DeTai, String> {
    @Query("SELECT COUNT(d) > 0 FROM DeTai d WHERE d.sinhVien.id = :sinhVienId")
    boolean existsDeTaiBySinhVienId(@Param("sinhVienId") String sinhVienId);
}
