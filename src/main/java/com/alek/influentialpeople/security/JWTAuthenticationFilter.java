package com.alek.influentialpeople.security;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.alek.influentialpeople.security.SecurityConstants.EXPIRATION_TIME;
import static com.alek.influentialpeople.security.SecurityConstants.HEADER_STRING;
import static com.alek.influentialpeople.security.SecurityConstants.SECRET;
import static com.alek.influentialpeople.security.SecurityConstants.TOKEN_PREFIX;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager; // rozszerzamy takze spokojnie ta klasa to bean.
	}

	// ten filtr jest jako drugi
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {
		//tutaj parsujemy sobie dane uzytkownika i przkazujemy do obiektu authentication poprzez menagera
		//spring security zapewnia /login endpoint 
		System.out.println("attemt in JWTAUTHENTI");
		try {
			com.alek.influentialpeople.persistance.entity.User creds = new ObjectMapper() // wyciagniecie usera z req
					.readValue(req.getInputStream(), com.alek.influentialpeople.persistance.entity.User.class);

			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(creds.getUsername(),
					creds.getPassword(), new ArrayList<>()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		//wywolana w momencie udanego logowania 
		System.out.println("successfulin JWTAUTHENti");
		String token = JWT.create().withSubject(((User) auth.getPrincipal()).getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).sign(HMAC512(SECRET.getBytes()));
		res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
	}
}