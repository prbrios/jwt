package prbrios.jwt.configuracao.seguranca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import prbrios.jwt.entidades.Usuario;
import prbrios.jwt.repositorio.UsuarioRepository;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Optional<Usuario> usuario = this.repository.findByEmail(s);

        if(usuario.isPresent()){
            return usuario.get();
        }

        throw new UsernameNotFoundException("Usuário inválido!");

    }
}
