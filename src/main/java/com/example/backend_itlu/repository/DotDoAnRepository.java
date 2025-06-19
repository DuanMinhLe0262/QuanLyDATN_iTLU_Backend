package com.example.backend_itlu.repository;

import com.example.backend_itlu.entity.DotDoAn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DotDoAnRepository extends JpaRepository<DotDoAn, String> {
    List<DotDoAn> findAllByOrderByThoiGianTaoDesc();

}
