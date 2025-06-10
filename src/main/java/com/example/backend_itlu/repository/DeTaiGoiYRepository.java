package com.example.backend_itlu.repository;

import com.example.backend_itlu.entity.DeTaiGoiY;
import com.example.backend_itlu.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeTaiGoiYRepository extends JpaRepository<DeTaiGoiY, String> {
}
