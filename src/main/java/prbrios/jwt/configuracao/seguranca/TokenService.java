package prbrios.jwt.configuracao.seguranca;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import prbrios.jwt.entidades.Usuario;

import java.util.Date;

@Service
public class TokenService {

    @Value("${api.jwt.token.expiracao}")
    private String expiracao;

    @Value("${api.jwt.secreto}")
    private String secreto;

    public String gerarToken(Authentication authentication) {
        Usuario usuario = (Usuario) authentication.getPrincipal();

        Date hoje = new Date();
        Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(this.expiracao));

        return Jwts.builder()
                .setIssuer("API Teste")
                .setSubject(usuario.getId().toString())
                .setIssuedAt(hoje)
                .setExpiration(dataExpiracao)
                .signWith(SignatureAlgorithm.HS256, secreto)
                .compact();
    }

    public boolean isTokenValido(String token) {

        try {

            Jwts.parser().setSigningKey(this.secreto).parseClaimsJws(token);
            return true;

        }catch (Exception e){
            return false;
        }

    }

    public Long getIdUsuario(String token) {

        Claims claims = Jwts.parser().setSigningKey(this.secreto).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }
}
