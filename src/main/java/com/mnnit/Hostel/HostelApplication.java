package com.mnnit.Hostel;

import com.mnnit.Hostel.database.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class HostelApplication {

	public static void main(String[] args) {
		SpringApplication.run(HostelApplication.class, args);
	}
}
