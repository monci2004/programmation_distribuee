package com.example.MonsefMLSD;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import tools.jackson.databind.ObjectMapper;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class MonsefMlsdApplicationTests {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void contextLoads() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	void shouldRentCarWhenRentParameterIsTrue() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		Map<String, String> rentRequest = Map.of(
				"begin", "11/11/2017",
				"end", "1/1/2018"
		);

		mockMvc.perform(put("/cars/11AA22?rent=true")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(rentRequest)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.plateNumber").value("11AA22"))
				.andExpect(jsonPath("$.begin").value("11/11/2017"))
				.andExpect(jsonPath("$.end").value("1/1/2018"));
	}

	@Test
	void shouldReturnCarWhenRentParameterIsFalse() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

		mockMvc.perform(put("/cars/11AA22?rent=false")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.plateNumber").value("11AA22"))
				.andExpect(jsonPath("$.begin").doesNotExist())
				.andExpect(jsonPath("$.end").doesNotExist());
	}

}
