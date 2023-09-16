package hakanozdabak.productData.webapi.controllers;

import hakanozdabak.productData.business.abstracts.RefreshTokenService;
import hakanozdabak.productData.business.jwt.AuthenticationService;
import hakanozdabak.productData.business.jwt.JWTService;
import hakanozdabak.productData.business.requests.AuthenticationRequest;
import hakanozdabak.productData.business.requests.RefreshTokenRequest;
import hakanozdabak.productData.business.requests.RegisterRequest;
import hakanozdabak.productData.business.responses.AuthenticationResponse;
import hakanozdabak.productData.entities.concretes.RefreshToken;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final RefreshTokenService refreshTokenService;
    private final JWTService jwtService;
    @PostMapping("/register")
    public String register(
            @RequestBody RegisterRequest registerRequest
    ) {
         authenticationService.register(registerRequest);
         return "User Successfully Added";
    }

    @PostMapping("/authenticate")
    public AuthenticationResponse register(
            @RequestBody AuthenticationRequest authenticationRequest
    ) {
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(authenticationRequest.getEmail());

        return AuthenticationResponse.builder()
                .accessToken(String.valueOf(authenticationService.authenticate(authenticationRequest).getToken()))
                .token(refreshToken.getToken()).build();


    }
    @PostMapping("/refresh")
    public AuthenticationResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return refreshTokenService.findByToken(refreshTokenRequest.getToken())
                 .map(refreshTokenService::verifyExpiration)
                 .map(RefreshToken::getUser)
                 .map(user ->{String accessToken = jwtService.generateToken(user.getEmail());
                   return AuthenticationResponse.builder()
                         .accessToken(accessToken)
                         .token(refreshTokenRequest.getToken())
                         .build();
                 }).orElseThrow(()->new RuntimeException("Refresh token is not in database'"));

    }
}
