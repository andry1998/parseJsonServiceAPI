package com.java.roomDescription;

import com.java.roomDescription.repository.RoomRepository;
import com.java.roomDescription.service.CameraServiceImpl;
import com.java.roomDescription.service.DoorServiceImpl;
import com.java.roomDescription.service.RoomServiceImpl;
import com.java.roomDescription.service.SynchronizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

//@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
@EnableScheduling
public class RoomDescriptionApplication implements CommandLineRunner {
	final DoorServiceImpl doorServiceImpl;
	final CameraServiceImpl cameraServiceImpl;
	final RoomServiceImpl roomServiceImpl;
	final RoomRepository roomRepository;
	final SynchronizationService synchronizationService;



	public static void main(String[] args) {
		SpringApplication.run(RoomDescriptionApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//synchronizationService.dataSynchronization();
	}
}
