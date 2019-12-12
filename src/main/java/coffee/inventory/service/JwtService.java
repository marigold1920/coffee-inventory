package coffee.inventory.service;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {
    private static final String USERNAME = "username";
    private static final String EMPLOYEE_ID = "employeeId";
    private static final String SECRET_KEY = "11111111111111111111111111111111";
    private static final int EXPIRE_TIME = 86400000;

    public String generateTokenLogin(String username, Integer employeeId) {
        String token = null;
        try {
            JWSSigner signer = new MACSigner(generateShareSecret());
            JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder();
            builder.claim(USERNAME, username);
            builder.claim(EMPLOYEE_ID, String.valueOf(employeeId));
            builder.expirationTime(generateExpirationDate());

            JWTClaimsSet claimsSet = builder.build();
            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);

            signedJWT.sign(signer);
            token = signedJWT.serialize();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return token;
    }

    private Date generateExpirationDate() {

        return new Date(System.currentTimeMillis() + EXPIRE_TIME);
    }

    private byte[] generateShareSecret() {
        byte[] shareSecret;
        shareSecret = SECRET_KEY.getBytes();
        return shareSecret;
    }

    public boolean validateTokenLogin(String token) {
        if (token == null || token.trim().length() == 0) {
            return false;
        }
        String username = getUsernameFromToken(token);
        if (username == null || username.isEmpty()) {
            return false;
        }
        return !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    private Date getExpirationDateFromToken(String token) {
        Date expiration;
        JWTClaimsSet claims = getClaimsFromToken(token);
        expiration = claims.getExpirationTime();
        return expiration;
    }

    public String getUsernameFromToken(String token) {
        String username = null;
        try {
            JWTClaimsSet claimsSet = getClaimsFromToken(token);
            username = claimsSet.getStringClaim(USERNAME);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return username;
    }

    public String getEmployeeIdFromToken(String token) {
        String employeeId = null;
        try {
            JWTClaimsSet claimsSet = getClaimsFromToken(token);
            employeeId = claimsSet.getStringClaim(EMPLOYEE_ID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employeeId;
    }

    private JWTClaimsSet getClaimsFromToken(String token) {
        JWTClaimsSet claimsSet = null;
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(generateShareSecret());
            if (signedJWT.verify(verifier)) {
                claimsSet = signedJWT.getJWTClaimsSet();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return claimsSet;
    }
}
