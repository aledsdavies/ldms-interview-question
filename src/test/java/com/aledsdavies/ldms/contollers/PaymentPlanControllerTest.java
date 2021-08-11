package com.aledsdavies.ldms.contollers;

import com.aledsdavies.ldms.models.PaymentPlan;
import com.aledsdavies.ldms.repositories.PaymentPlanRepository;
import com.aledsdavies.ldms.service.interfaces.RepaymentScheduleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(SpringExtension.class)
@WebMvcTest(PaymentPlanController.class)
class PaymentPlanControllerTest {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RepaymentScheduleService repaymentScheduleService;

    /*
        This is a quirk form initialising data in the initialise function in the applications' entry point. Normally
        this wouldn't be required in these unit tests.
     */
    @MockBean
    private PaymentPlanRepository paymentPlanRepository;


    @Test
    void getAll_whenSuccessfulReturnsEmptyArray() throws Exception {
        given(this.repaymentScheduleService.getAll()).willReturn(new ArrayList<>());

        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void getRepaymentSchedule_WhenSuccessfulReturnsAFullPaymentSchedule() throws Exception {

    }

    @Test
    void create_whenSuccessfulReturnsOk() throws Exception {
        this.mockMvc.perform(post("/")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void create_whenCreateIsInvalid() throws Exception {
        var plan = PaymentPlan.builder().build();

        this.mockMvc.perform(
                post("/").content(objectMapper.writeValueAsString(plan))
                        .contentType("application/json")
                ).andDo(print()).andExpect(status().is4xxClientError());
    }

    @Test
    void create_whenNoObjectIsPassed() throws Exception {
        this.mockMvc.perform(post("/").content("")).andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    void update_whenNoObjectIsPassed() throws Exception {
        this.mockMvc.perform(put("/").content("")).andDo(print()).andExpect(status().isBadRequest());
    }

    @Test
    void delete_whenDeleteIsSuccessful() throws Exception {
        this.mockMvc.perform(delete("/test-plan-id")).andDo(print()).andExpect(status().isOk());
    }
}