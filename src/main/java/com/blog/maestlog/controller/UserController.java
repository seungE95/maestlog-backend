package com.blog.maestlog.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.maestlog.jwt.JwtTokenUtil;
import com.blog.maestlog.service.UserService;
import com.blog.maestlog.social.GoogleLoginService;
import com.blog.maestlog.vo.UserVo;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@CrossOrigin("*")	//CROS오류 방지
@RequestMapping("/api/user")
public class UserController {
	
	private final UserService userService;
	private final GoogleLoginService loginService;
	
	private final int expireTimeMs = 1000*60*60;
	private final String secretKey = "secret-key-maestlog";
	
	@PostMapping("/email")
	public Map<String, String> sendMail(@RequestBody UserVo userVo) {
		Map<String, String> res = new HashMap<String, String>();
		
		String token = JwtTokenUtil.createToken(userVo.getEmail(), secretKey, expireTimeMs);	//토큰 유효시간 10시간
		userService.sendMail(userVo, token);
		res.put("result", "Y");
		res.put("code", "200");
		res.put("message", "이메일 전송 완료");
		res.put("Authorization", token);

		return res;
	}

	//회원 가입 유무 조회
	@PostMapping("/login")
	public Map<String, String> userAuth(@RequestHeader String Authorization) {
		Map<String, String> res = new HashMap<String, String>();
		
		try {
			//토큰 만료 시간 체크
			JwtTokenUtil.isExpired(Authorization, secretKey);
			String email = JwtTokenUtil.getEmail(Authorization, secretKey);
			
			//회원 조회가 안되는 경우
			if(null != userService.userJoin(email)) {
				res = userService.userJoin(email);
			} else {
				res.put("result", "Y");
				res.put("code", "200");
				res.put("message", "일치하는 회원 정보가 없습니다.");
			}
			return res;
			
		} catch (Exception e) {
			res.put("result", "N");
			res.put("code", "419");
			res.put("message", "토큰이 만료되었습니다.");
			return res;
		}
	}
	
	//@NoAuth //인가가 필요하지 않은 api(spring security)
	@GetMapping("/api/{registrationId}")
	public void socialLogin(@RequestParam String code, @PathVariable String registrationId) throws IOException{
		loginService.socialLogin(code, registrationId);
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/")
	public String home() {
		return "Hello jaeseung";
	}
	
	@PostMapping("/signup")
	public String signUp(@RequestBody UserVo userVo) {
			
		return null;
	}
}
