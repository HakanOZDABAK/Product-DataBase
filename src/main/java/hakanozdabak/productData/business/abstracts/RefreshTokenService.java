package hakanozdabak.productData.business.abstracts;

import hakanozdabak.productData.entities.concretes.RefreshToken;

import java.util.Optional;

public interface RefreshTokenService {
    RefreshToken createRefreshToken(String email);
    RefreshToken verifyExpiration(RefreshToken token);
    Optional<RefreshToken> findByToken(String token);
}
