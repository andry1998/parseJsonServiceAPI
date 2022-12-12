package com.java.roomDescription;

import com.java.roomDescription.service.CameraServiceImpl;
import com.java.roomDescription.service.DoorServiceImpl;
import com.java.roomDescription.service.RoomServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@Slf4j
@SpringBootApplication
//@EnableScheduling
public class RoomDescriptionApplication implements CommandLineRunner {
	private final DoorServiceImpl doorServiceImpl;
	private final CameraServiceImpl cameraServiceImpl;
	private final RoomServiceImpl roomServiceImpl;

	public RoomDescriptionApplication(DoorServiceImpl doorServiceImpl, CameraServiceImpl cameraServiceImpl, RoomServiceImpl roomServiceImpl) {
		this.doorServiceImpl = doorServiceImpl;
		this.cameraServiceImpl = cameraServiceImpl;
		this.roomServiceImpl = roomServiceImpl;
	}

	public static void main(String[] args) {
		SpringApplication.run(RoomDescriptionApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//doorServiceImpl.deleteData(1);
		//doorServiceImpl.doorSynchronization();
		doorServiceImpl.updateData();
	}
}
