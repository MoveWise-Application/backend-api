package com.movewise.movewise_api;

import java.net.URI;
import java.net.URISyntaxException;
import java.awt.Desktop;
import java.io.IOException;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MovewiseApiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MovewiseApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		openSwaggerUI();
	}

	private void openSwaggerUI() {
		try {
			String url = "http://localhost:8080/swagger-ui/index.html#";
			if (Desktop.isDesktopSupported()) {
				Desktop desktop = Desktop.getDesktop();
				if (desktop.isSupported(Desktop.Action.BROWSE)) {
					desktop.browse(new URI(url));
				}
			} else {
				Runtime runtime = Runtime.getRuntime();
				String os = System.getProperty("os.name").toLowerCase();
				if (os.contains("win")) {
					runtime.exec("rundll32 url.dll,FileProtocolHandler " + url);
				} else if (os.contains("mac")) {
					runtime.exec("open " + url);
				} else if (os.contains("nix") || os.contains("nux")) {
					runtime.exec("xdg-open " + url);
				}
			}
		} catch (URISyntaxException | IOException e) {
			e.printStackTrace();
		}
	}
}
