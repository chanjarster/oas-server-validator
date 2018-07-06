package me.chanjar.oas.server.validator.core.loader;

import io.swagger.v3.oas.models.OpenAPI;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static org.testng.Assert.assertNotNull;

public class OpenAPIV3LoaderTest {

  @Test
  public void test_loadFromClasspath_good() {
    OpenAPI openAPI = OpenAPIV3Loader.loadFromClasspath("petstore.yaml");
    assertNotNull(openAPI);
  }

  @Test
  public void test_loadFromUrl_good() {
    OpenAPI openAPI = OpenAPIV3Loader.loadFromLocation(
        "https://raw.githubusercontent.com/OAI/OpenAPI-Specification/master/examples/v3.0/petstore.yaml");
    assertNotNull(openAPI);
  }

  @Test
  public void test_loadFromLocalDisk_good() throws IOException {

    String specContent = ClasspathFileUtils.getFileContent("petstore.yaml");

    File tempFile = File.createTempFile("openapi-", ".yaml");
    tempFile.deleteOnExit();
    FileUtils.write(tempFile, specContent);

    OpenAPI openAPI = OpenAPIV3Loader.loadFromLocation(tempFile.getAbsolutePath());
    assertNotNull(openAPI);

  }

  @Test(expectedExceptions = OpenAPIV3LoaderException.class)
  public void test_loadFromClasspath_bad() {
    OpenAPI openAPI = OpenAPIV3Loader.loadFromClasspath("petstore.yaml2");
    assertNotNull(openAPI);
  }

  @Test(expectedExceptions = OpenAPIV3LoaderException.class)
  public void test_loadFromUrl_bad() {
    OpenAPI openAPI = OpenAPIV3Loader.loadFromLocation(
        "https://raw.githubusercontent.com/OAI/OpenAPI-Specification/master/examples/v3.0/petstore.yaml2");
    assertNotNull(openAPI);
  }

  @Test(expectedExceptions = OpenAPIV3LoaderException.class)
  public void test_loadFromLocalDisk_bad() throws IOException {

    OpenAPI openAPI = OpenAPIV3Loader.loadFromLocation("/not/exists/file");
    assertNotNull(openAPI);

  }

}
