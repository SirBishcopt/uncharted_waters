package com.sirbishcopt.unchartedwaters;

//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sirbishcopt.unchartedwaters.service.collecting.*;

//@SpringBootApplication
public class UnchartedwatersApplication {

	public static void main(String[] args) {
		//SpringApplication.run(UnchartedwatersApplication.class, args);

		ImageManipulationService imageNameService = new ImageManipulationForNamesService();
		ImageManipulationService imageCommoditiesService = new ImageManipulationForCommoditiesService();


		OcrService ocrService = new Tess4jService();

		//ocrService.doOcr(imageNameService.prepareImage("https://cdn.discordapp.com/attachments/970011748956524657/998510679944331314/Screenshot_20220718-094440.jpg",false));//Malacca
		//ocrService.doOcr(imageCommoditiesService.prepareImage("https://cdn.discordapp.com/attachments/970011748956524657/998510679944331314/Screenshot_20220718-094440.jpg",true));//Malacca
		//ocrService.doOcr(imageNameService.prepareImage("https://cdn.discordapp.com/attachments/970010293264580738/998524337646743613/IMG_3327.png"));//Darwin
		//ocrService.doOcr(imageCommoditiesService.prepareImage("https://cdn.discordapp.com/attachments/970010293264580738/998524347088117760/IMG_3328.png",true));//Darwin
		//ocrService.doOcr(imageNameService.prepareImage("https://cdn.discordapp.com/attachments/970011748956524657/997783392097685625/F3EA8CD0-EDB4-482B-BD8D-ACB034167F6A.png"));//Panama City
		//ocrService.doOcr(imageCommoditiesService.prepareImage("https://cdn.discordapp.com/attachments/970011748956524657/997783392097685625/F3EA8CD0-EDB4-482B-BD8D-ACB034167F6A.png"));//Panama City
		//ocrService.doOcr(imageNameService.prepareImage("https://cdn.discordapp.com/attachments/970011748956524657/997784016705040384/Screenshot_20220716-103701.jpg"));//Nantes
		//ocrService.doOcr(imageCommoditiesService.prepareImage("https://cdn.discordapp.com/attachments/970011748956524657/997784016705040384/Screenshot_20220716-103701.jpg"));//Nantes
		//ocrService.doOcr(imageNameService.prepareImage("https://cdn.discordapp.com/attachments/970011748956524657/997784417223331840/Screenshot_2022.07.16_10.38.38.942.png"));//Boston
		//ocrService.doOcr(imageCommoditiesService.prepareImage("https://cdn.discordapp.com/attachments/970011748956524657/997784417223331840/Screenshot_2022.07.16_10.38.38.942.png"));//Boston
		//ocrService.doOcr(imageNameService.prepareImage("https://cdn.discordapp.com/attachments/970011748956524657/998501357017370694/image.png"));//Ceylon
		//ocrService.doOcr(imageCommoditiesService.prepareImage("https://cdn.discordapp.com/attachments/970011748956524657/998501357017370694/image.png"));//Ceylon
		//ocrService.doOcr(imageNameService.prepareImage("https://cdn.discordapp.com/attachments/970011748956524657/992770176086974474/Screenshot_2022-07-02-14-33-58-324_com.onemt.and.kc.jpg"));//St. George's
		//ocrService.doOcr(imageCommoditiesService.prepareImage("https://cdn.discordapp.com/attachments/970011748956524657/992770176086974474/Screenshot_2022-07-02-14-33-58-324_com.onemt.and.kc.jpg"));//St. George's

	}

}