package com.ghas.chaekgalpi.repository;

import com.ghas.chaekgalpi.model.Novel;
import com.ghas.chaekgalpi.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NovelRepository extends JpaRepository<Novel,Long> {
    List<Novel> findByUsuario(Usuario usuario);
}
