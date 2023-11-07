package crm.configuration.security;

import crm.infrastructure.entities.Role;
import crm.infrastructure.entities.User;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JwtUtil {
    @Value("${app.jwtSecret}")
    private String jwtSecret;
    @Value("${app.jwtExpirationInSeconds}")
    private int jwtExpirationInSeconds;

    public String generateJwtToken(User user, Set<Role> roles) {
        Set<String> roleNames = roles.stream()
                .map(m -> m.getId().name())
                .collect(Collectors.toSet());

        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", roleNames);
        claims.put("last-updated-timestamp", user.getAuditInfo().getLastUpdatedAt());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setId(user.getId().toString())
                .setIssuer("AIS-SYSTEM")
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationInSeconds))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public Set<String> getRolesFromJwtToken(String token) {
        Set<String> output = new HashSet<>();
        Collections.addAll(output, Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .get("roles").toString()
                .replace(" ", "")
                .replace("[", "")
                .replace("]", "")
                .split(","));

        return output;
    }
}
