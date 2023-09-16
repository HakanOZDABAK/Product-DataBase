package hakanozdabak.productData.business.concretes;

import hakanozdabak.productData.business.abstracts.RefreshTokenService;
import hakanozdabak.productData.dataAccess.abstracts.RefreshTokenRepository;
import hakanozdabak.productData.dataAccess.abstracts.UserRepository;
import hakanozdabak.productData.entities.concretes.RefreshToken;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RefreshTokenManager implements RefreshTokenService {
    private RefreshTokenRepository refreshTokenRepository;
    private UserRepository userRepository;

    @Override
    public RefreshToken createRefreshToken(String email) {
        RefreshToken refreshToken = RefreshToken.builder()
                .user(userRepository.findByEmail(email).get())
                .token(UUID.randomUUID().toString())
                .expirayDate(Instant.now().plusMillis(600000))//10 min
                .build();
        return refreshTokenRepository.save(refreshToken);
    }

    public Optional<RefreshToken> findByToken(String token){
        return refreshTokenRepository.findByToken(token);
    }

  public RefreshToken verifyExpiration(RefreshToken token){
        if(token.getExpirayDate().compareTo(Instant.now())<0){
            refreshTokenRepository.delete(token);
            throw new RuntimeException(token.getToken()+"Refresh token was expired. Please make a new signin requst");
        }
     return token;
  }
}
