package com.sirbishcopt.unchartedwaters;

//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sirbishcopt.unchartedwaters.services.OcrService;

//@SpringBootApplication
public class UnchartedwatersApplication {

	public static void main(String[] args) {
		//SpringApplication.run(UnchartedwatersApplication.class, args);

		OcrService ocrService = new OcrService();
		ocrService.doOCR("https://cdn.discordapp.com/attachments/970011748956524657/998310520283205662/Screenshot_20220717-202824.jpg");

	}

}