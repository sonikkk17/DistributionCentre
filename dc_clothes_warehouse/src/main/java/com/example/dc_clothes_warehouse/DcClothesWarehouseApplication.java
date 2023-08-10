package com.example.dc_clothes_warehouse;

import com.example.dc_clothes_warehouse.model.DistributionCentre;
import com.example.dc_clothes_warehouse.model.ItemModel;
import com.example.dc_clothes_warehouse.model.User;
import com.example.dc_clothes_warehouse.repository.DistributionCentreRepository;
import com.example.dc_clothes_warehouse.repository.ItemRepository;
import com.example.dc_clothes_warehouse.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
public class DcClothesWarehouseApplication {

	public static void main(String[] args) {
		SpringApplication.run(DcClothesWarehouseApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(DistributionCentreRepository DCrepository, ItemRepository itemRepository, UserRepository repository3, PasswordEncoder passwordEncoder){
		return args ->{
			LocalDateTime now = LocalDateTime.now();
			var distributionCentre1 = DCrepository.save(DistributionCentre.builder()
					.name("Yorkdale")
					.longitude(235)
					.latitude(110).build());
			var distributionCentre2 = DCrepository.save(DistributionCentre.builder()
					.name("Dufferin")
					.longitude(240)
					.latitude(120).build());
			var distributionCentre3 = DCrepository.save(DistributionCentre.builder()
					.name("Loblaws Plaza")
					.longitude(210)
					.latitude(100).build());
			var distributionCentre4 = DCrepository.save(DistributionCentre.builder()
					.name("Square One")
					.longitude(200)
					.latitude(80).build());
			itemRepository.save(ItemModel.builder()
					.name("T-Shirt")
					.brand(ItemModel.FashionBrand.DIOR)
					.yearOfCreation(2022)
					.price(2150)
					.createdAt(now)
					.quantity(40)
					.distributionCentre(distributionCentre1).build());
			itemRepository.save(ItemModel.builder()
					.name("Gloves")
					.brand(ItemModel.FashionBrand.BALENCIAGA)
					.yearOfCreation(2022)
					.price(1070)
					.createdAt(now)
					.quantity(15)
					.distributionCentre(distributionCentre1).build());
			itemRepository.save(ItemModel.builder()
					.name("T-Shirt")
					.brand(ItemModel.FashionBrand.BALENCIAGA)
					.yearOfCreation(2022)
					.price(2100)
					.createdAt(now)
					.quantity(40)
					.distributionCentre(distributionCentre4).build());
			itemRepository.save(ItemModel.builder()
					.name("Hat")
					.brand(ItemModel.FashionBrand.DIOR)
					.yearOfCreation(2023)
					.price(1090)
					.createdAt(now)
					.quantity(18)
					.distributionCentre(distributionCentre3).build());
			itemRepository.save(ItemModel.builder()
					.name("Suit")
					.brand(ItemModel.FashionBrand.BALENCIAGA)
					.yearOfCreation(2022)
					.price(2800)
					.createdAt(now)
					.quantity(15)
					.distributionCentre(distributionCentre2).build());
			itemRepository.save(ItemModel.builder()
					.name("Pants")
					.brand(ItemModel.FashionBrand.STONE_ISLAND)
					.yearOfCreation(2022)
					.price(2150)
					.createdAt(now)
					.quantity(20)
					.distributionCentre(distributionCentre4).build());
			itemRepository.save(ItemModel.builder()
					.name("Long-sleeve")
					.brand(ItemModel.FashionBrand.STONE_ISLAND)
					.yearOfCreation(2022)
					.price(2650)
					.createdAt(now)
					.quantity(40)
					.distributionCentre(distributionCentre2).build());
			itemRepository.save(ItemModel.builder()
					.name("Purse")
					.brand(ItemModel.FashionBrand.DIOR)
					.yearOfCreation(2022)
					.price(1890)
					.createdAt(now)
					.quantity(12)
					.distributionCentre(distributionCentre2).build());
			itemRepository.save(ItemModel.builder()
					.name("Shoes")
					.brand(ItemModel.FashionBrand.BALENCIAGA)
					.yearOfCreation(2023)
					.price(3150)
					.createdAt(now)
					.quantity(36)
					.distributionCentre(distributionCentre1).build());
			itemRepository.save(ItemModel.builder()
					.name("Backpack")
					.brand(ItemModel.FashionBrand.DIOR)
					.yearOfCreation(2023)
					.price(1500)
					.createdAt(now)
					.quantity(20)
					.distributionCentre(distributionCentre3).build());
			itemRepository.save(ItemModel.builder()
					.name("Shorts")
					.brand(ItemModel.FashionBrand.STONE_ISLAND)
					.yearOfCreation(2022)
					.price(1050)
					.createdAt(now)
					.quantity(25)
					.distributionCentre(distributionCentre1).build());


			repository3.save(User.builder()
					.username("admin")
					.password(passwordEncoder.encode("admin")).build());
		};
	}

}
