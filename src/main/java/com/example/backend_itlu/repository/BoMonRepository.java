package com.example.backend_itlu.repository;

import com.example.backend_itlu.entity.BoMon;
import com.example.backend_itlu.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoMonRepository extends JpaRepository<BoMon, String> {

}
