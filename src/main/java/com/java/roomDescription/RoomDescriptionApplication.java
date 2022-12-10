package com.java.roomDescription;

import com.java.roomDescription.repository.DoorRepository;
import com.java.roomDescription.service.CameraService;
import com.java.roomDescription.service.DoorService;
import com.java.roomDescription.service.RetrofitService;
import com.java.roomDescription.service.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
//@Slf4j
@SpringBootApplication
//@EnableScheduling
public class RoomDescriptionApplication implements CommandLineRunner {
	private final DoorService doorService;
	private final CameraService cameraService;
	private final RoomService roomService;

	public RoomDescriptionApplication(DoorService doorService, CameraService cameraService, RetrofitService retrofitService, RoomService roomService) {
		this.doorService = doorService;
		this.cameraService = cameraService;
		this.roomService = roomService;
	}

	public static void main(String[] args) {
		SpringApplication.run(RoomDescriptionApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(doorService.getByRoom());
	}
}
