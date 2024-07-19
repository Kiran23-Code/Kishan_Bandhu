package com.tce.kisanbandhu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tce.kisanbandhu.model.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, String> {

}
