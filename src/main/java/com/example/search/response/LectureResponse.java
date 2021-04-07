package com.example.search.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LectureResponse {
	private long id;

	private int length;

	private String title;
	private String summary;
	private String videoSrc;
	private String imageSrc;
	private String category;

	private List<Stuff> stuffs;

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public class Stuff {

		private long id;

		private long lectureId;
		private long quantity;

		private String name;
		private String unit;
	}

}
