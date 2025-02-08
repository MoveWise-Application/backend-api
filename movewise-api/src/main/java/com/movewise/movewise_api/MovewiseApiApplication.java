package com.movewise.movewise_api;

import java.net.URI;
import java.awt.Desktop;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MovewiseApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovewiseApiApplication.class, args);
	}

	// @Bean
	// public ApplicationRunner applicationRunner() {
	// return args -> {
	// openSwaggerUI();
	// // Runtime.getRuntime().addShutdownHook(new Thread(this::closeSwaggerUI));
	// };
	// }

	// private void openSwaggerUI() {
	// try {
	// if (Desktop.isDesktopSupported() &&
	// Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
	// Desktop.getDesktop().browse(new
	// URI("http://localhost:8080/swagger-ui.html"));
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
}
