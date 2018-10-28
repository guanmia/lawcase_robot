package com.anyun100.legal.lawcase_robot.controller;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CaseCheckTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;
	
	private String oneDayAfter;
	

	@Before
	public void setup() throws Exception {

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("openid", "4");
		map.add("case_num", "5");
		map.add("court_datetime", "2017-09-20 10:00");
		map.add("case_content", "my contents");
		ResponseEntity<String> response_1 = this.restTemplate.postForEntity("http://localhost:" + this.port + "/lawrobot/api/case/add", map,
				String.class);

		assertThat(response_1.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
		map.clear();
		map.add("openid", "4");
		map.add("case_num", "6");
		map.add("court_datetime", "2017-09-20 10:00");
		map.add("case_content", "my contents");
		ResponseEntity<String> response_2 = this.restTemplate.postForEntity("http://localhost:" + this.port + "/lawrobot/api/case/add", map,
				String.class);

		assertThat(response_2.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		oneDayAfter = LocalDateTime.now().plusDays(1).format(formatter);
		map.clear();
		map.add("openid", "4");
		map.add("case_num", "7");
		map.add("court_datetime", oneDayAfter);
		map.add("case_content", "my contents");
		ResponseEntity<String> response_3 = this.restTemplate.postForEntity("http://localhost:" + this.port + "/lawrobot/api/case/add", map,
				String.class);

		assertThat(response_3.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
	}
	
	@Test
	public void TestCaseAlert() {
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("openid", "4");
		ResponseEntity<String> response = this.restTemplate.postForEntity("http://localhost:" + this.port + "/lawrobot/api/case/alert", map,
				String.class);
		assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
		assertThat(response.getBody().contains("count_day:" + oneDayAfter));
		//assertThat(response.getBody().contains("count_count:1"));
		
	}
	
	@Test
	public void TestCaseList() {
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("openid", "4");
		ResponseEntity<String> response = this.restTemplate.postForEntity("http://localhost:" + this.port + "/lawrobot/api/case/list", map,
				String.class);
		assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
		String json = response.getBody();
		assertThat(response.getBody().contains("page_count:" + 1));
		assertThat(response.getBody().contains("count:1"));
		
	}
	
	@Test
	public void TestSimilarcase() {
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("case_id", "22244c8728804a84b8a4a0268fba695b");
		map.add("page", "0");
		map.add("page_count", "20");
		ResponseEntity<String> response = this.restTemplate.postForEntity("http://localhost:" + this.port + "/lawrobot/api/case/analysis/similarcase", map,
				String.class);
		String json = response.getBody();
		assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
		
		
	}
	
	@Test
	public void caseContentsTest() throws Exception {
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("case_id", "3f35e0c558004969b2e02850dcadc52a");

		ResponseEntity<String> response = this.restTemplate
				.postForEntity("http://localhost:" + this.port + "/lawrobot/api/case/content", map, String.class);
		String contents = response.getBody();
		assertThat(response.getStatusCodeValue()).isEqualTo(200);
	}
	
	@Test
	public void caseAttentionTest() throws Exception {
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("case_id", "3f35e0c558004969b2e02850dcadc52a");

		ResponseEntity<String> response = this.restTemplate
				.postForEntity("http://localhost:" + this.port + "/lawrobot/api/case/analysis/attention", map, String.class);
		String contents = response.getBody();
		assertThat(response.getStatusCodeValue()).isEqualTo(200);
	}

	@Test
	public void caseInfluencedetailTest() throws Exception {
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("influence_id", 1);

		ResponseEntity<String> response = this.restTemplate
				.postForEntity("http://localhost:" + this.port + "/lawrobot/api/case/analysis/influencedetail", map, String.class);
		String contents = response.getBody();
		assertThat(response.getStatusCodeValue()).isEqualTo(200);
	}
}
