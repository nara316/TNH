package go.travel.dnh.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class AirControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void sortListOneWay() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/air/sort-oneway")
                        .param("sortValue", "priceAsc")
                        .param("sch.oneFrom", "140")
                        .param("sch.oneTo", "110")
                        .param("sch.ea", "1")
                        .param("sch.oneFromDate", "2023-04-22")
                        .param("sch.airGrade", "A01"))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        Assertions.assertThat(content).isNotEmpty();
        // 결과값에 대한 추가적인 검증 로직을 작성할 수 있습니다.
    }

}