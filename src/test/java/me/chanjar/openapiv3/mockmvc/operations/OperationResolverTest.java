package me.chanjar.openapiv3.mockmvc.operations;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import me.chanjar.openapiv3.mockmvc.loader.OpenAPIV3Loader;
import org.junit.Test;
import org.springframework.http.HttpMethod;

import static org.junit.Assert.*;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.OPTIONS;
import static org.springframework.http.HttpMethod.TRACE;

public class OperationResolverTest {

  @Test
  public void test_resolve() {

    OpenAPI openAPI = OpenAPIV3Loader.loadFromClasspath("petstore.yaml");
    OperationResolver resolver = new OperationResolver(openAPI, "/pets", GET);

    Operation operation = resolver.resolve();
    assertNotNull(operation);

  }

  @Test
  public void test_resolve_bad() {

    OpenAPI openAPI = OpenAPIV3Loader.loadFromClasspath("petstore.yaml");
    OperationResolver resolver = new OperationResolver(openAPI, "/pets", OPTIONS);

    Operation operation = resolver.resolve();
    assertNull(operation);

  }

}
