package com.proyecto.apartahotel.sispart.login.auth;

public class JwtConfig {
	
	public static final String LLAVE_SECRETA = "sispart.secret";
	
	public static final String RSA_PRIVADA = "-----BEGIN RSA PRIVATE KEY-----\r\n"
			+ "MIIEpAIBAAKCAQEA0EWhomKXbzB4y6KgqsWmsiY7YgesVbJZVp/ZsD8y3HJHYiVM\r\n"
			+ "LvsYvTUUAsw0+/GqQ6oM4dSseWeP7hXgLLokOuuwzUdf9eRWmV2Ujv/OAMePNXb+\r\n"
			+ "AoRUP93Dz9E3+vRyaJUteB1QD0P9lvde+YO0XvstXvbkqL6R4QkGcWC+NA7g2xe7\r\n"
			+ "f132sFGs9JI/BKZHQuGRfB8b6KWnAwU7nPtmA2h+EOeKfAMYE/LhspD5R+++7/wj\r\n"
			+ "J50oN7xwnWEj1e1FoPtTr2aZsG9mcyMob3R9CtuSGFTIuTH1yvBE1eFilv0lTqz1\r\n"
			+ "M9xfI/J0naetsTZHwMomY5psk3Mk2NvykpNgYQIDAQABAoIBABq59MX71OcF11p6\r\n"
			+ "YhfICV4vHcu0pF7S5jgBh1PgqEw1tgdYNfCJdGFDYHADeIEkP+8y8ThBrGhf685n\r\n"
			+ "rD7RHDkbabLOB/hr9tjzc07ucKcU9MGtpWb5SD5x3ODcPkzL9aqKCi2xxUZQOUiY\r\n"
			+ "Me3fAtR1cxsOn8Uvv1SbbeI6we24B5QUNsdQxUmo4svkQJ/2sC/sFn2HHeH5RrK6\r\n"
			+ "pw+MWjCDlJTZuGb/8TDJfUsYq+Gjd0X4gYgQsk8/Wkc0DFUmINMqbCbjXUHieivG\r\n"
			+ "wYKb3EMvkOn+nQ2M1ODrVmNTfLe/Ahvjvu/tqdQ6EZoUfYlroLlDGJCJS7wzSVj2\r\n"
			+ "hv6y54ECgYEA+4U+jhZ8IYAUsdeWSA5/jJevPpUujHANXi3RtEpEANTGAY1FuE8T\r\n"
			+ "9CGgbyDYT+r56Zjr+LHrrY0GJH6xl3SP5ywevoyNTk15b9nst9qBTDqLQZ9jtOyP\r\n"
			+ "WwsksK5HPaVUIKEx3FAfU9u5MMV6M+YHaeDkfs7AdgTbd5PdNMwrQHkCgYEA0/s0\r\n"
			+ "WXCwQhboKUwiXTuycae30icee1j2gZKeUX/JDvMpfBMtgJMUg89qzAt7co/Qm6Sh\r\n"
			+ "gFgJrsggm6xIT2cx9l+Tm7tttxshbgmCA3GJegU5POHpvWfh6BMC8IzZZa7C9SA+\r\n"
			+ "b4gYjc/eAuGM4uAHiK0wisvkHy3iVFD+AxR1NSkCgYApbfI231dFAV7nSFaVueWi\r\n"
			+ "l92i1lnbgh2BlNSE+PKonGOdH9MLH+NuhBd0Fpnq2A6gPrMnvM7QQHlxi3jfNLD7\r\n"
			+ "aBYMVwRzkGlfVisawaAomUAU6Bc5VvnqCjTZ8WsEqXXpBgQ7gOsYGgowEWcmo043\r\n"
			+ "T3bzuESVbAlmcfGu366EKQKBgQCnsoUZdKKbxg9oDEjrRPzIGQy8/iGJJznsC5ef\r\n"
			+ "ld3zK77X3zwSCUkjdYVhYnwIh2lamAUDyaHvWXcicErMNjE6XSYi/0Wuo5HwwSjP\r\n"
			+ "nJRLqbI8TeRh6Lq4o8Ghl2usm3K0BQwfo6T5G0bhNJoGKYEMSSarxtzzJZN1bJVm\r\n"
			+ "zlpi8QKBgQCIoRL+QmfcH9MXGGGBIaQHRtcrEOUUZI5CbvsfipVKwjANY0U6PVER\r\n"
			+ "DG1fUjq4MqIwL8MRxJ/kIujXDviEaNg8qUD8ClGbyFsKr8NhP1ZZpPB3Q/ky1/Mg\r\n"
			+ "8gF1/LlNs4UM1JM2N54FkaWachBmzIjAm87flMcBzRP/miA4kI9Cmg==\r\n"
			+ "-----END RSA PRIVATE KEY-----";

	public static final String RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\r\n"
			+ "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0EWhomKXbzB4y6KgqsWm\r\n"
			+ "siY7YgesVbJZVp/ZsD8y3HJHYiVMLvsYvTUUAsw0+/GqQ6oM4dSseWeP7hXgLLok\r\n"
			+ "OuuwzUdf9eRWmV2Ujv/OAMePNXb+AoRUP93Dz9E3+vRyaJUteB1QD0P9lvde+YO0\r\n"
			+ "XvstXvbkqL6R4QkGcWC+NA7g2xe7f132sFGs9JI/BKZHQuGRfB8b6KWnAwU7nPtm\r\n"
			+ "A2h+EOeKfAMYE/LhspD5R+++7/wjJ50oN7xwnWEj1e1FoPtTr2aZsG9mcyMob3R9\r\n"
			+ "CtuSGFTIuTH1yvBE1eFilv0lTqz1M9xfI/J0naetsTZHwMomY5psk3Mk2NvykpNg\r\n"
			+ "YQIDAQAB\r\n"
			+ "-----END PUBLIC KEY-----";
}
