package com.example.functionalendpointfileUpload.controller;

import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import com.example.functionalendpointfileUpload.entity.Employee;
import com.example.functionalendpointfileUpload.service.UploadService;
import com.example.functionalendpointfileUpload.util.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Controller
public class UploadController {

	@Autowired
	UploadService service;

	@Bean
	@RouterOperations({

		@RouterOperation(path = "/upload", produces = {
				MediaType.APPLICATION_JSON_VALUE }, consumes = "multipart/form-data", method = RequestMethod.POST, operation = @Operation(operationId = "Upload", responses = {
						@ApiResponse(responseCode = Constants.responseCodes.SUCCESS, description = "File Upload Success", content = @Content(schema = @Schema(implementation = Employee.class))),
						@ApiResponse(responseCode = Constants.responseCodes.INTERNAL_SERVER_ERROR, description = "File Upload Failed") }, requestBody = @RequestBody(required = true, content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE, schemaProperties = {
								@SchemaProperty(name = "file", schema = @Schema(type = "string", format = "binary", contentMediaType = MediaType.MULTIPART_FORM_DATA_VALUE)) }))

						)) })

	RouterFunction<ServerResponse> routeUpload() {

		return RouterFunctions.route(
				RequestPredicates.POST("/upload").and(RequestPredicates.contentType(MediaType.MULTIPART_FORM_DATA)),
				req -> req.body(BodyExtractors.toMultipartData()).flatMap(file -> service.upload(file).collectList())
				.flatMap(data -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(data)));
	}
}
