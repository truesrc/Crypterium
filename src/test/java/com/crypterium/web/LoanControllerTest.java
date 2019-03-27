package com.crypterium.web;

import com.crypterium.domain.Country;
import com.crypterium.domain.Loan;
import com.crypterium.domain.Person;
import com.crypterium.repository.CountryRepository;
import com.crypterium.service.BlackListService;
import com.crypterium.service.CountryService;
import com.crypterium.service.LimitService;
import com.crypterium.service.LoanService;
import com.crypterium.web.forms.Error;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author truesrc
 * @since 27.03.2019
 */
@RunWith(SpringRunner.class)
@WebMvcTest(LoanController.class)
public class LoanControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private BlackListService blacks;

    @MockBean
    private LoanService loans;

    @MockBean
    private LimitService limit;

    @MockBean
    private CountryService countryService;

    @MockBean
    private CountryRepository countryRepository;
    ;


    @Test
    public void whenPersonNotInBlackListThenApplyLoan() throws Exception {
        List<Loan> list = Collections.singletonList(
                new Loan("test", 1D, new Country("RUS"), new Person("Алёна", "Надеждина"))
        );
        ObjectMapper mapper = new ObjectMapper();
        given(loans.getAll()).willReturn(list);
        mvc.perform(
                get("/").accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(
                status().isOk()
        ).andExpect(
                content().string(mapper.writeValueAsString(list))
        );
    }

    @Test
    public void whenLoadThenApplyLoan() throws Exception {
        List<Loan> list = Collections.singletonList(
                new Loan("test", 1D, new Country("RUS"), new Person("Алёна", "Надеждина"))
        );
        ObjectMapper mapper = new ObjectMapper();
        given(loans.getByPersonId(0)).willReturn(list);
        mvc.perform(
                get("/0").accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(
                status().isOk()
        ).andExpect(
                content().string(mapper.writeValueAsString(list))
        );
    }

    @Test
    public void whenInBlacklistThenError() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        given(blacks.isBlackListPerson(0)).willReturn(true);
        mvc.perform(
                post("/").contentType(MediaType.APPLICATION_JSON_UTF8).content(
                        mapper.writeValueAsString(
                                new Loan("test", 1D, new Country("RUS"), new Person("Алёна", "Надеждина"))
                        )
                )
        ).andExpect(
                status().isOk()
        ).andExpect(
                content().string(mapper.writeValueAsString(new Error("User 0 in blacklist")))
        );
    }

}