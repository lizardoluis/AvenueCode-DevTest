package com.avenuecode.web;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.avenuecode.DemoApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DemoApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductControllerTest {

	private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext wac;

	@Before
	public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

	}

	@Test
	public void verifyAllProductList() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/products").accept(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$", hasSize(4))).andDo(print());
	}
	
	@Test
	public void verifyProductById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/products/3").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.id").exists())
		.andExpect(jsonPath("$.name").exists())
		.andExpect(jsonPath("$.description").exists())
		.andExpect(jsonPath("$.id").value(3))
		.andExpect(jsonPath("$.name").value("Windows Phone"))
		.andExpect(jsonPath("$.description").value("Windows"))
		.andDo(print());
	}
	
	@Test
	public void verifyInvalidProductArgument() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/products/f").accept(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.errorCode").value(400))
			.andExpect(jsonPath("$.message").value("The request could not be understood by the server due to malformed syntax."))
			.andDo(print());
	}
	
	@Test
	public void verifyInvalidProductId() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/products/0").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.errorCode").value(404))
		.andExpect(jsonPath("$.message").value("Product doesn´t exist"))
		.andDo(print());
	}
	
	@Test
	public void verifyNullProduct() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/products/6").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.errorCode").value(404))
		.andExpect(jsonPath("$.message").value("Product doesn´t exist"))
		.andDo(print());
	}
	
	@Test
	public void verifyDeleteProduct() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/products/4").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.status").value(200))
		.andExpect(jsonPath("$.message").value("Product has been deleted"))
		.andDo(print());
	}
	
	@Test
	public void verifyInvalidProductIdToDelete() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/products/9").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.errorCode").value(404))
		.andExpect(jsonPath("$.message").value("Product to delete doesn´t exist"))
		.andDo(print());
	}
	
	
	@Test
	public void verifySaveProduct() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/products/")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"name\" : \"Samsumg Galaxy\", \"description\" : \"Android System\" }")
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.id").exists())
		.andExpect(jsonPath("$.name").exists())
		.andExpect(jsonPath("$.description").exists())
		.andExpect(jsonPath("$.name").value("Samsumg Galaxy"))
		.andExpect(jsonPath("$.description").value("Android System"))
		.andDo(print());
	}
	
	@Test
	public void verifyMalformedSaveProduct() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/products/")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{ \"id\": \"8\", \"name\" : \"Samsumg Galaxy\", \"description\" : \"Android System\" }")
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.errorCode").value(404))
		.andExpect(jsonPath("$.message").value("Payload malformed, id must not be defined"))
		.andDo(print());
	}
}
