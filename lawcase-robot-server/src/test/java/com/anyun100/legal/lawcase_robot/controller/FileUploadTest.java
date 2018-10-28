package com.anyun100.legal.lawcase_robot.controller;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.anyun100.legal.lawcase_robot.service.StorageService;

import static org.assertj.core.api.Assertions.assertThat;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FileUploadTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private StorageService storageService;

	@LocalServerPort
	private int port;

	@Test
	public void shouldUploadFile() throws Exception {
		ClassPathResource resource = new ClassPathResource("testupload.txt", getClass());

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("case_file", resource);
		map.add("openid", "4");
		map.add("case_num", "5");
		map.add("court_datetime", "2017-09-20 10:00");
		map.add("case_content", "my contents");
		ResponseEntity<String> response = this.restTemplate.postForEntity("http://localhost:" + this.port + "/lawrobot/api/case/add", map, String.class);

		assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
		String feedback = response.getBody();
		assertThat(feedback.contains("error code:0"));
	}
	@Ignore
	@Test
	public void shouldDownloadFile() throws Exception {
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("case_id", "3f35e0c558004969b2e02850dcadc52a");

		ResponseEntity<String> response = this.restTemplate
				.postForEntity("http://localhost:" + this.port + "/lawrobot/api/case/content/download",  map, String.class);

		assertThat(response.getStatusCodeValue()).isEqualTo(200);
		assertThat(response.getHeaders().getFirst(HttpHeaders.CONTENT_DISPOSITION))
				.isEqualTo("attachment; filename=\"3f35e0c558004969b2e02850dcadc52a\"");
		String contents = response.getBody();
		assertThat(response.getBody()).isEqualTo("Spring Framework");
	}
}
