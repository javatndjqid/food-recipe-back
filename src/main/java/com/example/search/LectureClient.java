package com.example.search;

//
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

//
@FeignClient(name = "lecture", url = "http://192.168.0.31:8888")
public interface LectureClient {
	@GetMapping("/lectures")
	String getLecture();
}
