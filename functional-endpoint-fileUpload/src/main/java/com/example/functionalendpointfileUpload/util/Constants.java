package com.example.functionalendpointfileUpload.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

public final class Constants {

	@NoArgsConstructor(access=AccessLevel.PRIVATE)
	public static final class responseCodes{
		public static final String SUCCESS="200";
		public static final String INTERNAL_SERVER_ERROR="500";
	}
}
