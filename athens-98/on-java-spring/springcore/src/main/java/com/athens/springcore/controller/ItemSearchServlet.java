//package com.athens.springcore.controller;
//
//import com.athens.springcore.dto.ItemDto;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.client.RestTemplate;
//
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.List;
//
//@WebServlet(urlPatterns = "/api/search")
//public class ItemSearchServlet extends HttpServlet {
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String query = request.getParameter("query");
//
//        RestTemplate rest = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("X-Naver-Client-Id", "K6szJpGr1xNJshdH9AKU");
//        headers.add("X-Naver-Client-Secret", "igR34dCWLl");
//        String body = "";
//        HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);
//
//        ResponseEntity<String> responseEntity = rest.exchange("https://openapi.naver.com/v1/search/shop.json?query=" + query, HttpMethod.GET, requestEntity, String.class);
//        String naverApiResponseJson = responseEntity.getBody();
//
//        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        JsonNode itemsNode = objectMapper.readTree(naverApiResponseJson).get("items");
//        List<ItemDto> itemDtoList = objectMapper.readerFor(new TypeReference<List<ItemDto>>() {}).readValue(itemsNode);
//
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
//        PrintWriter out = response.getWriter();
//        String itemDtoListJson = objectMapper.writeValueAsString(itemDtoList);
//        out.print(itemDtoListJson);
//        out.flush();
//    }
//
//}
