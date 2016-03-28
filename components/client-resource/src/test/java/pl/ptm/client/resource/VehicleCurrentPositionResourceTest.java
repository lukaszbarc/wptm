package pl.ptm.client.resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.ptm.client.service.api.VehicleCurrentPositionService;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Configuration
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {VehicleCurrentPositionResourceTest.class})
public class VehicleCurrentPositionResourceTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private VehicleCurrentPositionService service;

    private MockMvc mockMvc;

    @Bean
    public VehicleCurrentPositionResource vehicleCurrentPositionResource() {
        return new VehicleCurrentPositionResource(vehicleCurrentPositionService());
    }

    @Bean
    public VehicleCurrentPositionService vehicleCurrentPositionService() {
        return Mockito.mock(VehicleCurrentPositionService.class);
    }

    @Before
    public void init() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testServiceExecutedProperly() throws Exception {
        mockMvc.perform(get("/{providerId}/position/current", "warsaw-trams")).andExpect(status().is(200));
        mockMvc.perform(get("/{providerId}/position/current/20", "warsaw-trams", 20)).andExpect(status().is(200));
        mockMvc.perform(get("/{providerId}/position/current/20/8", "warsaw-trams", 20, 8)).andExpect(status().is(200));

        verify(service, times(1)).getVehiclePositionData("warsaw-trams");
        verify(service, times(1)).getVehiclePositionData("warsaw-trams", "20");
        verify(service, times(1)).getVehiclePositionData("warsaw-trams", "20", 8);
    }


}