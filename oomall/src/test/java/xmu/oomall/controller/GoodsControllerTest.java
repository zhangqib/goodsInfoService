package xmu.oomall.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import xmu.oomall.Application;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
@Transactional
class GoodsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getGoodsForSaleById() {

    }

    @Test
    void getGoodsById() {
    }

    @Test
    void isGoodsOnSale() {
    }

    @Test
    void listGoodsByCondition() {
    }

    @Test
    void testListGoodsByCondition() {
    }
}