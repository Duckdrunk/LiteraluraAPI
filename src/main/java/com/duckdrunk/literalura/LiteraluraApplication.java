package com.duckdrunk.literalura;

import com.duckdrunk.literalura.service.MenuService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@AllArgsConstructor
@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	private final MenuService menuService;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args){
		menuService.mostrarMenu();
	}
}
