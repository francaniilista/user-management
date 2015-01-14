package com.odesk.model;

import java.io.IOException;

public class UserTest {
	public static void main(String[] args) throws IOException {
		User user = User
				.getBuilder("pafranca", "q1w2e3")
				.email("pafranca@ig.com.br")
				.build();
		
		System.out.println(user.toString());
		System.out.println(user.toJSON());
	}
}
