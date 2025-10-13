package com.codedifferently.lesson23.web;

import java.util.Map;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomErrorController implements ErrorController {

  @RequestMapping("/error")
  public ResponseEntity<Map<String, Object>> handleError() {
    Map<String, Object> errorResponse =
        Map.of(
            "error",
            "Not Found",
            "message",
            "The requested endpoint was not found",
            "status",
            404,
            "availableEndpoints",
            Map.of(
                "GET /items", "Get all media items",
                "GET /items/{id}", "Get a specific media item",
                "POST /items", "Create a new media item",
                "DELETE /items/{id}", "Delete a media item",
                "GET /swagger-ui.html", "API Documentation"));

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
  }
}
