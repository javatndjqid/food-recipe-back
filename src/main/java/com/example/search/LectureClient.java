package com.example.search;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

//@FeignClient(name = "lecture", url = "http://192.168.0.31:8888")
@FeignClient(name = "lecture", url = "https://p31ybo8hml.execute-api.ap-northeast-2.amazonaws.com/v1/lecture")
public interface LectureClient {
	@GetMapping("/lectures")
	String getLecture();
}
