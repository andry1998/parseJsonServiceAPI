package com.java.roomDescription;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

//@Slf4j
@SpringBootApplication
@EnableScheduling
public class RoomDescriptionApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(RoomDescriptionApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}
}
