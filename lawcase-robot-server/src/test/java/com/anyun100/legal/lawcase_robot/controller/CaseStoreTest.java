package com.anyun100.legal.lawcase_robot.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CaseStoreTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;
	
	@Test
	public void TestCaseContents() {
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("case_id", "0");
		ResponseEntity<String> response = this.restTemplate.postForEntity("http://localhost:" + this.port + "/lawrobot/api/casestore/content", map,
				String.class);
		String json = response.getBody();
		assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
	}
	
	@Test
	public void TestAnalysisIdSearch() {
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("category_id", 12);
		map.add("searchkeys", "[{\"option_id\":4,\"value\":5,\"type\":\"select\"},{\"option_id\":16,\"value\":5000, \"type\":\"int\"}]");
		ResponseEntity<String> response = this.restTemplate.postForEntity("http://localhost:" + this.port + "/lawrobot/api/casestore/search", map,
				String.class);
		String json = response.getBody();
		assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
	}
	
}
