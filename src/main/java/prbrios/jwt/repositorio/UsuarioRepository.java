package prbrios.jwt.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import prbrios.jwt.entidades.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

}
