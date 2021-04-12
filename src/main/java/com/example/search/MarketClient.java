package com.example.search;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

//@FeignClient(name = "market", url = "http://192.168.0.27:8080")
@FeignClient(name = "market", url = "https://p31ybo8hml.execute-api.ap-northeast-2.amazonaws.com/v1/market")
public interface MarketClient {
	@GetMapping("/product")
	String getProduct();
}
