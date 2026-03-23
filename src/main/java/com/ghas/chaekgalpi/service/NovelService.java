package com.ghas.chaekgalpi.service;

import com.ghas.chaekgalpi.model.Novel;
import com.ghas.chaekgalpi.model.Usuario;
import com.ghas.chaekgalpi.repository.NovelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NovelService {
    private final NovelRepository novelRepository;

    public NovelService(NovelRepository novelRepository) {
        this.novelRepository = novelRepository;
    }

    public List<Novel> getAll() {
        return novelRepository.findAll();
    }

    public List<Novel> findByUsuario(Usuario usuario) {
        return novelRepository.findByUsuario(usuario);
    }

    public Novel findById(Long id) {
        return novelRepository.findById(id).orElseThrow(() -> new RuntimeException("Novel não encontrada"));
    }

    public Novel save(Novel novel) {
        return novelRepository.save(novel);
    }

    public void delete(Long id) {
        novelRepository.deleteById(id);
    }

    public Novel update(Long id, Novel novel) {
        novel.setId(id);
        return novelRepository.save(novel);
    }

    public void favoritar(Long id) {
        Novel novel = findById(id);
        novel.setFavorito(!novel.getFavorito());
        novelRepository.save(novel);
    }
}