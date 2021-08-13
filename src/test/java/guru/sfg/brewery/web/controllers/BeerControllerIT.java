package guru.sfg.brewery.web.controllers;


import org.junit.jupiter.api.Test;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.security.test.context.support.WithMockUser;


import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest
public class BeerControllerIT extends BaseIT {


    @Test
    void initCreationForm() throws Exception {
        mockMvc.perform(get("/beers/new")
                .with(httpBasic("testuser", "pass")))
                .andExpect(status().isOk())
                .andExpect(view().name("beers/createBeer"))
                .andExpect(model().attributeExists("beer"));
    }

    @WithMockUser("user")
    @Test
    void findBeers() throws Exception {
        mockMvc.perform(get("/beers/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("beers/findBeers"))
                .andExpect(model().attributeExists("beer"));
    }

    @Test
    void Should_LoginError_When_findBeer_WithHttpBasic() throws Exception {
        mockMvc.perform(get("/beer/find").with(httpBasic("foo", "bar")))
                .andExpect(status().is4xxClientError());
    }

}

