package prbrios.jwt.controles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prbrios.jwt.form.UsuarioForm;
import prbrios.jwt.servicos.UsuarioService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PutMapping
    public ResponseEntity<?> alterar(@RequestBody @Valid UsuarioForm obj) {

        this.usuarioService.salvar(obj);
        return ResponseEntity.noContent().build();

    }

}
