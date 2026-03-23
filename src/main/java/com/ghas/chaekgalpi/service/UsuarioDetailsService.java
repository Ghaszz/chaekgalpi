package com.ghas.chaekgalpi.service;

import com.ghas.chaekgalpi.model.Usuario;
import com.ghas.chaekgalpi.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String nome) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByNome(nome)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + nome));

        return User.builder()
                .username(usuario.getNome())
                .password(usuario.getSenha())
                .roles("USER")
                .build();
    }
}