package com.alek.influentialpeople;

public class ApplicationDemo {

	public static void main(String[] args) {
		
	}
//"C:\Program Files\Git\git-bash.exe" --cd-to-home
}

class Url {

	public final String name;
	public final int age;

	public Url() {
		this(new Url.Builder());
	}

	private Url(Url.Builder builder) {
		name = builder.name;
		age = builder.age;
	}

	public static class Builder {

		private String name = null;
		private int age = -1;

		public Builder() {
			name("Broom Hilda");
			age(127);
		}

		public Builder name(String name) {
			if (name == null || name.length() == 0) {
				throw new IllegalArgumentException("Thing.Builder.name (" + name + ") is null or empty.");
			}
			this.name = name;
			return this;
		}

		public Builder age(int age) {
			if (age < 0) {
				throw new IllegalArgumentException("Thing.Builder.age (" + age + ") is negative.");
			}
			this.age = age;
			return this;
		}

		public Url build() {
			return (new Url(this));
		}
	}
}