package prbrios.jwt.controles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import prbrios.jwt.configuracao.seguranca.TokenService;
import prbrios.jwt.controles.dto.TokenDto;
import prbrios.jwt.form.LoginForm;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LoginForm form){

        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(form.getEmail(), form.getSenha());

        try {

            Authentication authentication = authenticationManager.authenticate(login);
            String token = this.tokenService.gerarToken(authentication);
            return ResponseEntity.ok(new TokenDto(token, "Bearer"));

        }catch (AuthenticationException e){
            return ResponseEntity.badRequest().build();
        }

    }

}
