package com.ghas.chaekgalpi.controller;

import com.ghas.chaekgalpi.model.Novel;
import com.ghas.chaekgalpi.model.Usuario;
import com.ghas.chaekgalpi.repository.UsuarioRepository;
import com.ghas.chaekgalpi.service.NovelService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/novels")
public class NovelController {
    private final NovelService novelService;
    private final UsuarioRepository usuarioRepository;

    public NovelController(NovelService novelService, UsuarioRepository usuarioRepository) {
        this.novelService = novelService;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public List<Novel> getAll(@AuthenticationPrincipal UserDetails user) {
        Usuario usuario = usuarioRepository.findByNome(user.getUsername()).get();
        return novelService.findByUsuario(usuario);
    }

    @PostMapping
    public Novel save(@RequestBody Novel novel, @AuthenticationPrincipal UserDetails user) {
        Usuario usuario = usuarioRepository.findByNome(user.getUsername()).get();
        novel.setUsuario(usuario);
        return novelService.save(novel);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id, @AuthenticationPrincipal UserDetails user) {
        Novel novel = novelService.findById(id);
        if (novel.getUsuario().getNome().equals(user.getUsername())) {
            novelService.delete(id);
        } else {
            throw new RuntimeException("Você não tem permissão para excluir esta novel");
        }
    }

    @PutMapping("/{id}")
    public Novel update(@PathVariable Long id, @RequestBody Novel novel, @AuthenticationPrincipal UserDetails user) {
        Novel existing = novelService.findById(id);
        if (!existing.getUsuario().getNome().equals(user.getUsername())) {
            throw new RuntimeException("Você não tem permissão para editar esta novel");
        }
        novel.setId(id);
        novel.setUsuario(existing.getUsuario());
        return novelService.save(novel);
    }

    @PatchMapping("/{id}/favoritar")
    public void favoritar(@PathVariable Long id, @AuthenticationPrincipal UserDetails user) {
        Novel novel = novelService.findById(id);
        if (novel.getUsuario().getNome().equals(user.getUsername())) {
            novelService.favoritar(id);
        } else {
            throw new RuntimeException("Você não tem permissão para favoritar esta novel");
        }
    }
}