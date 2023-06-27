package hakanozdabak.productData.webapi.controllers;

import hakanozdabak.productData.business.jwt.AuthenticationService;
import hakanozdabak.productData.business.requests.AuthenticationRequest;
import hakanozdabak.productData.business.requests.RegisterRequest;
import hakanozdabak.productData.business.responses.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
   private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest registerRequest
  ){return ResponseEntity.ok(authenticationService.register(registerRequest));}
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody AuthenticationRequest registerRequest
    ){return ResponseEntity.ok(authenticationService.authenticate(registerRequest));}



}
