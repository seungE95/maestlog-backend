//package com.blog.maestlog.jwt;
//
//import java.io.IOException;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.http.HttpHeaders;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import com.blog.maestlog.service.UserService;
//
//import lombok.RequiredArgsConstructor;
////OncePerRequestFilter: 매번 들어갈 때 마다 체크 해주는 필터
//@RequiredArgsConstructor
//public class JwtTokenFilter extends OncePerRequestFilter{
//
//	private final UserService userService;
//	private final String secretKey;
//	
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//		//전송 받은 토큰 값
//		String token = request.getHeader(HttpHeaders.AUTHORIZATION);
//		
//		//Header의 Authorzation 값이 비어있으면 => Jwt Token을 전송하지 않음 => 로그인 하지 않음
//		if(token == null) {
//			filterChain.doFilter(request, response);
//			return;
//		}
//		
//		//전송 받은 Jwt Token이 만료되었으면 => 다음 필터 진행(인증 x)
//		if(JwtTokenUtil.isExpired(token, secretKey)) {
//			filterChain.doFilter(request, response);
//			return;
//		}
//		
//		//Jwt Token에서 email  추출
//		String email = JwtTokenUtil.getEmail(token, secretKey);
//		
//		//추출한 email로 User 찾아오기
//		//String unique_nickname = userService.get
//		
//		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(token, email);
//		System.out.println("\nauthToken ::: "+authenticationToken);
//		authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//		
//		//권한 부여
//		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//		filterChain.doFilter(request, response);
//	}
//
//}
