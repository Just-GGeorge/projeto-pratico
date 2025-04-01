package br.com.servidores.resource;

import br.com.servidores.security.JwtUtil;
import br.com.servidores.service.UserDetailsService;
import br.com.servidores.config.LoginRequest;
import br.com.servidores.dto.LoginResponseDto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthResource {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequest loginRequest) {
        try {
            // Tenta autenticar o usuário com o AuthenticationManager
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            // Gera o JWT
            String token = jwtUtil.generateToken(loginRequest.getUsername());

            // Calcula o tempo de expiração
            Date expirationDate = jwtUtil.extractExpiration(token);
            
            // Formata o tempo de expiração para o formato desejado
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            sdf.setTimeZone(TimeZone.getTimeZone("America/Cuiaba"));
            String formattedExpireTime = sdf.format(expirationDate);

            // Retorna a resposta com o nome de usuário, token e tempo de expiração
            return new LoginResponseDto(loginRequest.getUsername(), token, formattedExpireTime);

        } catch (BadCredentialsException e) {
            // Caso a autenticação falhe, retorne erro
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
