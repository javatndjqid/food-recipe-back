package com.example.search;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "market", url = "http://192.168.0.27:8080")
//@FeignClient(name = "market", url = "http://ec2-13-124-253-19.ap-northeast-2.compute.amazonaws.com:8080")
public interface MarketClient {
	@GetMapping("/product")
	String getProduct();
}
