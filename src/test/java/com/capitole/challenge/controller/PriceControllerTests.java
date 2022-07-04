package com.capitole.challenge.controller;

import com.capitole.challenge.dto.PriceResponseDTO;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Description;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.ResponseActions;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PriceControllerTests {

	@Autowired
	private PriceController controller;
	private int port;
	private String priceUrl;

	private TestRestTemplate restTemplate;

	private HttpHeaders headers;

	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

	@BeforeAll
	public void setUp(){
		restTemplate= new TestRestTemplate();
		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		port= 8080;
		priceUrl= "http://localhost:" + port + "/price";
	}

	@Test
	public void PriceFound() throws Exception {
		JSONObject requestBodyPriceJSON = new JSONObject()
				.put("productId", 35455)
				.put("brandId", 1)
				.put("dateTime","2020-06-14T00:00:00");


		HttpEntity<String> request =
				new HttpEntity<String>(requestBodyPriceJSON.toString(), headers);


		ResponseEntity<PriceResponseDTO> response =this.restTemplate.postForEntity(priceUrl,request, PriceResponseDTO.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertThat(response.getBody()).isNotNull();

		assertEquals(1,response.getBody().getBrand().getId());
		assertEquals("ZARA", response.getBody().getBrand().getName());
		assertEquals(35455, response.getBody().getProductId());
		assertEquals(35.5, response.getBody().getPrice());
		assertEquals("EUR", response.getBody().getCurrency());
		assertEquals(LocalDateTime.parse("2020-06-14T00:00:00"), response.getBody().getStartDate());
		assertEquals(LocalDateTime.parse("2020-12-31T23:59:59"), response.getBody().getEndDate());
	}

	@Test
	public void PriceNotFound() throws Exception {
		JSONObject requestBodyPriceJSON = new JSONObject()
				.put("productId", 35455)
				.put("brandId", 2)
				.put("dateTime","2020-06-14T00:00:00");

		HttpEntity<String> request =
				new HttpEntity<String>(requestBodyPriceJSON.toString(), headers);

		ResponseEntity<String> response =this.restTemplate.postForEntity(priceUrl,request, String.class);

		assertThat(response).isNotNull();
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	/*
	Desarrollar unos test al endpoint rest que  validen las siguientes peticiones al servicio con los datos del ejemplo:

-          Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)
-          Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
-          Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
-          Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
-          Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)
 */

	/**
	 * Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)
	 * @throws Exception
	 */
	@Test
	public void Test1_WHEN_Prod_35455_Day_14_AT_10_MUST_RETURN_35_5() throws Exception {
		JSONObject requestBodyPriceJSON = new JSONObject()
				.put("productId", 35455)
				.put("brandId", 1)
				.put("dateTime","2020-06-14T10:00:00");

		HttpEntity<String> request =
				new HttpEntity<String>(requestBodyPriceJSON.toString(), headers);


		ResponseEntity<PriceResponseDTO> response =this.restTemplate.postForEntity(priceUrl,request, PriceResponseDTO.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertThat(response.getBody()).isNotNull();

		assertEquals(1,response.getBody().getBrand().getId());
		assertEquals("ZARA", response.getBody().getBrand().getName());
		assertEquals(35455, response.getBody().getProductId());
		assertEquals(35.5, response.getBody().getPrice());
		assertEquals("EUR", response.getBody().getCurrency());
		assertEquals(LocalDateTime.parse("2020-06-14T00:00:00"), response.getBody().getStartDate());
		assertEquals(LocalDateTime.parse("2020-12-31T23:59:59"), response.getBody().getEndDate());
	}

	/**
	 * Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
	 * @throws Exception
	 */
	@Test
	public void Test2_WHEN_Prod_35455_Day_14_AT_16_MUST_RETURN_25_45() throws Exception {
		JSONObject requestBodyPriceJSON = new JSONObject()
				.put("productId", 35455)
				.put("brandId", 1)
				.put("dateTime","2020-06-14T16:00:00");

		HttpEntity<String> request =
				new HttpEntity<String>(requestBodyPriceJSON.toString(), headers);


		ResponseEntity<PriceResponseDTO> response =this.restTemplate.postForEntity(priceUrl,request, PriceResponseDTO.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertThat(response.getBody()).isNotNull();

		assertEquals(1,response.getBody().getBrand().getId());
		assertEquals("ZARA", response.getBody().getBrand().getName());
		assertEquals(35455, response.getBody().getProductId());
		assertEquals(25.45, response.getBody().getPrice());
		assertEquals("EUR", response.getBody().getCurrency());
		assertEquals(LocalDateTime.parse("2020-06-14T15:00"), response.getBody().getStartDate());
		assertEquals(LocalDateTime.parse("2020-06-14T18:30"), response.getBody().getEndDate());
	}

	/**
	 * Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
	 * @throws Exception
	 */
	@Test
	public void Test3_WHEN_Prod_35455_Day_14_AT_21_MUST_RETURN_35_5() throws Exception {
		JSONObject requestBodyPriceJSON = new JSONObject()
				.put("productId", 35455)
				.put("brandId", 1)
				.put("dateTime","2020-06-14T21:00:00");

		HttpEntity<String> request =
				new HttpEntity<String>(requestBodyPriceJSON.toString(), headers);


		ResponseEntity<PriceResponseDTO> response =this.restTemplate.postForEntity(priceUrl,request, PriceResponseDTO.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertThat(response.getBody()).isNotNull();

		assertEquals(1,response.getBody().getBrand().getId());
		assertEquals("ZARA", response.getBody().getBrand().getName());
		assertEquals(35455, response.getBody().getProductId());
		assertEquals(35.5, response.getBody().getPrice());
		assertEquals("EUR", response.getBody().getCurrency());
		assertEquals(LocalDateTime.parse("2020-06-14T00:00:00"), response.getBody().getStartDate());
		assertEquals(LocalDateTime.parse("2020-12-31T23:59:59"), response.getBody().getEndDate());
	}

	/**
	 * Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
	 * @throws Exception
	 */
	@Test
	public void Test4_WHEN_Prod_35455_Day_15_AT_10_MUST_RETURN_30_5() throws Exception {
		JSONObject requestBodyPriceJSON = new JSONObject()
				.put("productId", 35455)
				.put("brandId", 1)
				.put("dateTime","2020-06-15T10:00:00");

		HttpEntity<String> request =
				new HttpEntity<String>(requestBodyPriceJSON.toString(), headers);


		ResponseEntity<PriceResponseDTO> response =this.restTemplate.postForEntity(priceUrl,request, PriceResponseDTO.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertThat(response.getBody()).isNotNull();

		assertEquals(1,response.getBody().getBrand().getId());
		assertEquals("ZARA", response.getBody().getBrand().getName());
		assertEquals(35455, response.getBody().getProductId());
		assertEquals(30.5, response.getBody().getPrice());
		assertEquals("EUR", response.getBody().getCurrency());
		assertEquals(LocalDateTime.parse("2020-06-15T00:00"), response.getBody().getStartDate());
		assertEquals(LocalDateTime.parse("2020-06-15T11:00"), response.getBody().getEndDate());
	}

	/**
	 * Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)
	 * @throws Exception
	 */
	@Test
	public void Test5_WHEN_Prod_35455_Day_16_AT_21_MUST_RETURN_38_95() throws Exception {
		JSONObject requestBodyPriceJSON = new JSONObject()
				.put("productId", 35455)
				.put("brandId", 1)
				.put("dateTime","2020-06-16T21:00:00");

		HttpEntity<String> request =
				new HttpEntity<String>(requestBodyPriceJSON.toString(), headers);


		ResponseEntity<PriceResponseDTO> response =this.restTemplate.postForEntity(priceUrl,request, PriceResponseDTO.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertThat(response.getBody()).isNotNull();

		assertEquals(1,response.getBody().getBrand().getId());
		assertEquals("ZARA", response.getBody().getBrand().getName());
		assertEquals(35455, response.getBody().getProductId());
		assertEquals(38.95, response.getBody().getPrice());
		assertEquals("EUR", response.getBody().getCurrency());
		assertEquals(LocalDateTime.parse("2020-06-15T16:00"), response.getBody().getStartDate());
		assertEquals(LocalDateTime.parse("2020-12-31T23:59:59"), response.getBody().getEndDate());
	}
}


