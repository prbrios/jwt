package prbrios.jwt.form;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UsuarioForm {

    @NotNull(message = "Informa\u00e7\u00e3o obrigat\u00f3ria")
    @Email
    private String email;

    @NotNull(message = "Informa\u00e7\u00e3o obrigat\u00f3ria")
    @NotEmpty
    private String senha;

}
