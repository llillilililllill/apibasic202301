package com.example.apibasic.req;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@Slf4j
public class ApiController2 {

   // 요철 파라미터 읽기
   @GetMapping("/param1")
   public String param1(
           String username, //파라미터 키값과 변수명이 같으면 아노테이션 생략가능
           @RequestParam("age") int userAge
   ) {
       log.info("/param1?username={}&age={} GET", username, userAge);
       log.info("내 이름은 {}이고, 나이는 {}세다", username, userAge);
       return "";
   }

   @GetMapping("/param2")
    public String param2(OrderInfo orderInfo) {
       log.info("/param2?orderNo={}&goodsName={}&goodsAmount={}"
       , orderInfo.getOrderNo()
       , orderInfo.getGoodsName()
       , orderInfo.getGoodsAmount()
       );
       log.info("주문정보 : {}", orderInfo);
       return "";
   }

   // 요청 바디 읽기
    @PostMapping("/req-body")
    public String reqBody(@RequestBody OrderInfo orderInfo) {
       log.info("===== 주문정보 =====");
       log.info("# 주문번호: {}", orderInfo.getOrderNo());
       log.info("# 상품명: {}", orderInfo.getGoodsName());
       log.info("# 수량: {}", orderInfo.getGoodsAmount());
       log.info("===== 주문정보 =====");
       return "";
    }

   // 커맨드 객체 : 클라이언트가 보낸 파라미터 이름과 필드명이 정확이히 일치해야함
    @Setter @Getter
    @NoArgsConstructor
    @EqualsAndHashCode
    public static class OrderInfo {
       private Long orderNo;
       private String goodsName;
       private int goodsAmount;
   }
}
