package jfdev.apitodolist.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jfdev.apitodolist.infra.TokenService;
import jfdev.apitodolist.model.user.User;
import jfdev.apitodolist.model.user.dto.UserRegisterRequest;
import jfdev.apitodolist.model.user.dto.UserSignInRequest;
import jfdev.apitodolist.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;


    @Transactional
    @PostMapping("/register")
    public ResponseEntity cadastrar(@Valid @RequestBody UserRegisterRequest userRegister, UriComponentsBuilder uriBuilder) {

        User usuario = authService.registerUser(userRegister);
        if (usuario == null)
            return ResponseEntity.badRequest().body("Usuário já cadastrado");
        URI uri = uriBuilder.path("/users/{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(uri).body(usuario.getName());
    }

    @Transactional
    @PostMapping("/login")
    public ResponseEntity signIn(@RequestBody UserSignInRequest request){

        UsernamePasswordAuthenticationToken userPass = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

        Authentication auth = authenticationManager.authenticate(userPass);
        if(auth == null)
            throw new RuntimeException("Invalid username or password");
        String token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(token);

    }
}
