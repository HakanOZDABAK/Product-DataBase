package hakanozdabak.productData.business.jwt;

import hakanozdabak.productData.business.requests.AuthenticationRequest;
import hakanozdabak.productData.business.requests.RegisterRequest;
import hakanozdabak.productData.business.responses.AuthenticationResponse;
import hakanozdabak.productData.dataAccess.abstracts.UserRepository;
import hakanozdabak.productData.entities.concretes.Role;
import hakanozdabak.productData.entities.concretes.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private  final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest registerRequest) {
      var user = User.builder()
              .firstName(registerRequest.getFirstName())
              .lastName(registerRequest.getLastName())
              .email(registerRequest.getEmail())
              .password(passwordEncoder.encode(registerRequest.getPassword()))
              .role(Role.ROLE_USER)
              .build();
      userRepository.save(user);
      var jwtToken = jwtService.generateToken(user.getEmail());
      return AuthenticationResponse.builder()
              .token(jwtToken)
              .build();

    }

    public AuthenticationResponse authenticate(AuthenticationRequest registerRequest) {
    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    registerRequest.getEmail(),
                    registerRequest.getPassword()
            )
    );
    var user =userRepository.findByEmail(registerRequest.getEmail())
            .orElseThrow();
        var jwtToken = jwtService.generateToken(user.getEmail());
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
