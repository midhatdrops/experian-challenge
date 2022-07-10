package br.com.midhatdrops.experianChallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ExperianChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExperianChallengeApplication.class, args);
	}

}
