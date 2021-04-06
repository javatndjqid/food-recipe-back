package com.example.search;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

//import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "market", url = "http://192.168.0.27:8080")
public interface MarketClient {
	@GetMapping("/product")
	String getProduct();
}
