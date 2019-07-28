package com.alek.influentialpeople.user;

public class TokenFactory implements TokenCreator {

	@Override
	public String getToken() {
//			String token = JWT.create().withSubject(((User) auth.getPrincipal()).getUsername())
//					.withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).sign(HMAC512(SECRET.getBytes()));
//			res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
		return "";
	}
//tworzenie odswiezanie tokena

}
