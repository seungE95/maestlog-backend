package com.blog.maestlog.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.RequiredArgsConstructor;
//OncePerRequestFilter: 매번 들어갈 때 마다 체크 해주는 필터
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter{

	//private final UserService userService;
	private final String secretKey;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
	
		//Header의 Authorzation 값이 비어있으면 => Jwt Token을 전송하지 않음 => 로그인 하지 않음
		if(authorizationHeader == null) {
			filterChain.doFilter(request, response);
			return;
		}
	}

}
