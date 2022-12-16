package com.java.roomDescription;

import com.java.roomDescription.repository.RoomRepository;
import com.java.roomDescription.service.CameraServiceImpl;
import com.java.roomDescription.service.DoorServiceImpl;
import com.java.roomDescription.service.RoomServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

//@Slf4j
@SpringBootApplication
@EnableScheduling
public class RoomDescriptionApplication implements CommandLineRunner {
	private final DoorServiceImpl doorServiceImpl;
	private final CameraServiceImpl cameraServiceImpl;
	private final RoomServiceImpl roomServiceImpl;
	private final RoomRepository roomRepository;

	public RoomDescriptionApplication(DoorServiceImpl doorServiceImpl, CameraServiceImpl cameraServiceImpl, RoomServiceImpl roomServiceImpl,
									  RoomRepository roomRepository) {
		this.doorServiceImpl = doorServiceImpl;
		this.cameraServiceImpl = cameraServiceImpl;
		this.roomServiceImpl = roomServiceImpl;
		this.roomRepository = roomRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(RoomDescriptionApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}
}
