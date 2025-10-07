package com.example.Cadastro_Aluno.security;

import com.example.modelo_autenticacao_autorizacao.domain.repository.repository.UsuarioRepository;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioDetailsService implements UserDetailsService {

    private final UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var usuario = repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        return new User(
                usuario.getEmail(),
                usuario.getSenhaHash(),
                Lis t.of(new SimpleGrantedAuthority("ROLE_" + usuario.getRole().name()))
        );
    }
}