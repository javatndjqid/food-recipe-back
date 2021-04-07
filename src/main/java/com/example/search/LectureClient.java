package com.example.search;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "lecture", url = "http://192.168.0.31:8888")
//@FeignClient(name = "lecture", url = "http://ec2-15-164-200-213.ap-northeast-2.compute.amazonaws.com:8888")
public interface LectureClient {
	@GetMapping("/lectures")
	String getLecture();
}
