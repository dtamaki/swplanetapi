package com.dtamaki.swplanetapi;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.dtamaki.swplanetapi.repositories.PlanetRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SwplanetapiApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private PlanetRepository planetRepository;

	@Before
	public void deleteAllBeforeTests() throws Exception {
		planetRepository.deleteAll();
	}

	@Test
	public void shouldReturnAllPlanets() throws Exception {

		mockMvc.perform(post("/swplanetapi/planets")
				.content("{\"name\": \"Hoth\", \"climate\":\"Frozen\", \"terrain\":\"snow\"}")
				.contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
				.andExpect(jsonPath("$.errors", nullValue()));
		
		mockMvc.perform(post("/swplanetapi/planets")
				.content("{\"name\": \"Mustafar\", \"climate\":\"Hot\", \"terrain\":\"lava\"}")
				.contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
				.andExpect(jsonPath("$.errors", nullValue()));
		
		MvcResult mvcResult = mockMvc.perform(get("/swplanetapi/planets")).andDo(print()).andExpect(status().isOk())
				.andReturn();
		String result = mvcResult.getResponse().getContentAsString();
		assertFalse(result.isEmpty());
	}

	@Test
	public void shouldCreatePlanet() throws Exception {

		mockMvc.perform(post("/swplanetapi/planets")
				.content("{\"name\": \"Hoth\", \"climate\":\"Frozen\", \"terrain\":\"snow\"}")
				.contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
				.andExpect(jsonPath("$.errors", nullValue()));
	}

	@Test
	public void shouldNotCreatePlanetNoName() throws Exception {

		mockMvc.perform(post("/swplanetapi/planets").content("{\"climate\":\"Frozen\", \"terrain\":\"snow\"}")
				.contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errors").value("Name can't be empty"));
	}

	@Test
	public void shouldNotCreatePlanetNoClimate() throws Exception {

		mockMvc.perform(post("/swplanetapi/planets").content("{\"name\": \"Hoth\", \"terrain\":\"snow\"}")
				.contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errors").value("Climate can't be empty"));
	}

	@Test
	public void shouldNotCreatePlanetNoTerrain() throws Exception {

		mockMvc.perform(post("/swplanetapi/planets").content("{\"name\": \"Hoth\", \"climate\":\"Frozen\"}")
				.contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errors").value("Terrain can't be empty"));
	}

	@Test
	public void shouldFindPlanetName() throws Exception {

		mockMvc.perform(post("/swplanetapi/planets")
				.content("{\"name\": \"Hoth\", \"climate\":\"Frozen\", \"terrain\":\"snow\"}")
				.contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
				.andExpect(jsonPath("$.errors", nullValue()));

		mockMvc.perform(get("/swplanetapi/planets/name/{name}", "Hoth")).andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("Hoth"));
	}

	@Test
	public void shouldFindPlanetId() throws Exception {

		MvcResult mvcResult = mockMvc
				.perform(post("/swplanetapi/planets")
						.content("{\"name\": \"Hoth\", \"climate\":\"Frozen\", \"terrain\":\"snow\"}")
						.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andExpect(jsonPath("$.errors", nullValue())).andReturn();
		String mvcBodyResult = mvcResult.getResponse().getContentAsString();
		Integer resultIdpos = mvcBodyResult.indexOf("id\":\"");
		String id = mvcBodyResult.substring(resultIdpos + 5, resultIdpos + 29);

		mockMvc.perform(get("/swplanetapi/planets/id/{id}", id)).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(id));
	}

	@Test
	public void shouldDeletePlanetId() throws Exception {

		MvcResult mvcResult = mockMvc
				.perform(post("/swplanetapi/planets")
						.content("{\"name\": \"Hoth\", \"climate\":\"Frozen\", \"terrain\":\"snow\"}")
						.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andExpect(jsonPath("$.errors", nullValue())).andReturn();
		String mvcBodyResult = mvcResult.getResponse().getContentAsString();
		Integer resultIdpos = mvcBodyResult.indexOf("id\":\"");
		String id = mvcBodyResult.substring(resultIdpos + 5, resultIdpos + 29);

		mockMvc.perform(delete("/swplanetapi/planets/id/{id}", id)).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(id));
	}
	
}
