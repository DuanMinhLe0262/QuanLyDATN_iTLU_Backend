package com.example.backend_itlu.repository;

import com.example.backend_itlu.entity.DotDoAn;
import com.example.backend_itlu.enums.Dot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface DotDoAnRepository extends JpaRepository<DotDoAn, String> {
    Optional<DotDoAn> findByTenDot(Dot tenDot);

    @Query("SELECT d FROM DotDoAn d WHERE d.thoiGianBatDau <= :date AND d.thoiGianKetThuc >= :date")
    Optional<DotDoAn> findDotHienTai(@Param("date") LocalDate date);

    @Query("SELECT d FROM DotDoAn d WHERE d.thoiGianBatDau > :date ORDER BY d.thoiGianBatDau ASC")
    Optional<DotDoAn> findDotGanNhat(@Param("date") LocalDate date);

}
