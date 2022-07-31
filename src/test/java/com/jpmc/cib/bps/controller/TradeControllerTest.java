package com.jpmc.cib.bps.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpmc.cib.bps.dto.EventType;
import com.jpmc.cib.bps.dto.TradeEventDto;
import com.jpmc.cib.bps.service.TradeService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(MockitoExtension.class)
class TradeControllerTest {

    private MockMvc mvc;

    @Mock
    private TradeService tradeService;

    @InjectMocks
    private TradeController tradeController;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
        mvc = MockMvcBuilders.standaloneSetup(tradeController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    public void testShouldSaveTradeEvent() throws Exception {
        TradeEventDto tradeEventDto = new TradeEventDto();
        tradeEventDto.setEventId(1l);
        tradeEventDto.setAccountNumber("ACC1");
        tradeEventDto.setSecurityName("SEC1");
        tradeEventDto.setEventType(EventType.BUY);
        tradeEventDto.setQuantity(100);

        String json = objectMapper.writeValueAsString(tradeEventDto);

        Mockito.when(tradeService.saveTradeEvent(tradeEventDto)).thenReturn(tradeEventDto);

        MockHttpServletResponse response = mvc.perform(post("/tradeEvent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andReturn().getResponse();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        TradeEventDto actualTradeEventDto = objectMapper.readValue(response.getContentAsString(), TradeEventDto.class);
        Assertions.assertThat(actualTradeEventDto).isEqualTo(tradeEventDto);
    }

    @Test
    public void testShouldReturn400BadRequestWhileSaveTradeEvent() throws Exception {
        MockHttpServletResponse response = mvc.perform(post("/tradeEvent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new TradeEventDto())))
                .andReturn().getResponse();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void testShouldReturnTradeEvents() throws Exception {
        String accountNumber = "ACC1";
        String securityName = "SEC1";
        TradeEventDto tradeEvent1 = new TradeEventDto();
        tradeEvent1.setEventId(1l);
        tradeEvent1.setAccountNumber(accountNumber);
        tradeEvent1.setSecurityName(securityName);
        tradeEvent1.setEventType(EventType.BUY);
        tradeEvent1.setQuantity(100);

        TradeEventDto tradeEvent2 = new TradeEventDto();
        tradeEvent1.setEventId(2l);
        tradeEvent1.setAccountNumber(accountNumber);
        tradeEvent1.setSecurityName(securityName);
        tradeEvent1.setEventType(EventType.CANCEL);
        tradeEvent1.setQuantity(50);

        List<TradeEventDto> expectedTradeEventsDtos = new ArrayList<>();
        expectedTradeEventsDtos.add(tradeEvent1);
        expectedTradeEventsDtos.add(tradeEvent2);

        Mockito.when(tradeService.getTradeEvents(accountNumber, securityName)).thenReturn(expectedTradeEventsDtos);

        MockHttpServletResponse response = mvc.perform(get("/tradeEvents?accountNumber=" + accountNumber + "&securityName=" + securityName)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        TradeEventDto[] actualTradeEventDtos = objectMapper.readValue(response.getContentAsString(), TradeEventDto[].class);
        Assertions.assertThat(actualTradeEventDtos.length).isEqualTo(2);
        Assertions.assertThat(actualTradeEventDtos[0]).isEqualTo(tradeEvent1);
        Assertions.assertThat(actualTradeEventDtos[1]).isEqualTo(tradeEvent2);
    }
}