package guru.sfg.brewery.web.controllers.api;

import guru.sfg.brewery.services.BeerService;
import guru.sfg.brewery.web.controllers.BaseIT;
import guru.sfg.brewery.web.model.BeerDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;


import java.util.UUID;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
public class BeerRestControllerIT extends BaseIT{
    @Mock
    BeerService beerService;

    @Mock
    BeerDto beetDto =new BeerDto();

    @Test
    void findBeers () throws Exception {
        mockMvc.perform(get("/api/v1/beer")).andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /api/v1/beer/{id}")
    void findBeerById () throws Exception {

        UUID id = UUID.fromString("b4c6f61e-fbef-11eb-9a03-0242ac130003");
        doReturn(beetDto).when(beerService).findBeerById(id,true);
        mockMvc.perform(get("/api/v1/beer/{id}",id)).andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /api/v1/beerUpc/{upc}")
    void  findBeerByUpc() throws Exception {
        String upc = "testUPC";
        doReturn(beetDto).when(beerService).findBeerByUpc(upc);
        mockMvc.perform(get("/api/v1/beerUpc/{upc}",upc)).andExpect(status().isOk());
    }
}
