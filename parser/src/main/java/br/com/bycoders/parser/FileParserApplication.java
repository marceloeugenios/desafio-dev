package br.com.bycoders.parser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class FileParserApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileParserApplication.class, args);
	}

}
