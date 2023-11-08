package com.blog.maestlog.jwt;

import java.util.Date;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtTokenUtil {
	
	// JWT Token 발급
	public static String createToken(String email, String secretKey, long expireTimeMs) {
		//Claim = Jwt Token에 들어갈 정보
		//Claim에 email을 넣어 줌으로써 나중에 email를 꺼낼 수 있음
		Claims claims = Jwts.claims();
		claims.put("email", email);
		//claims.put("nickname", nickname);
		
		return Jwts.builder()
					.setClaims(claims)
					.setIssuedAt(new Date(System.currentTimeMillis()))
					.setExpiration(new Date(System.currentTimeMillis() + expireTimeMs))
					.signWith(SignatureAlgorithm.HS256, secretKey)
					.compact();
	}
	
	//Claims에서 email 꺼내기
	public static String getEmail(String token, String secretKey) {
		return extractClaims(token, secretKey).get("email").toString();
	}
	
	//Claims에서 nickname 꺼내기
	public static String getNickname(String token, String secretKey) {
		return extractClaims(token, secretKey).get("nickname").toString();
	}

	//발급된 token이 만료 시간이 지났는지 체크
	public static boolean isExpired(String token, String secretKey) {
		Date expiredDate = extractClaims(token, secretKey).getExpiration();
		//Token의 만료 날짜가 지금보다 이전인지 check
		return expiredDate.before(new Date());
	}
	
	//SecretKey를 사용해 Token Parsing
	private static Claims extractClaims(String token, String secretKey) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
	}
}
