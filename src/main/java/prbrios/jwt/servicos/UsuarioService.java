package prbrios.jwt.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import prbrios.jwt.entidades.Usuario;
import prbrios.jwt.form.UsuarioForm;
import prbrios.jwt.repositorio.UsuarioRepository;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Usuario salvar(UsuarioForm obj){

        Optional<Usuario> usuario = this.repository.findByEmail(obj.getEmail());
        String senha = new BCryptPasswordEncoder().encode(obj.getSenha());
        if (usuario.isPresent()) {

            Usuario usuario2 = usuario.get();
            usuario2.setSenha(senha);
            return this.repository.save(usuario2);

        } else {
            throw new IllegalArgumentException("Usuário inválido");
        }
    }

}
