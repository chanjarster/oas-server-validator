package me.chanjar.openapiv3.mockmvc.utils;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;

import java.io.IOException;

public class OpenAPIV3Loader {

  /**
   * Read OpenAPI V3 spec from a file in classpath, and get parse result.
   *
   * @param classpath classpath to OpenAPI V3 spec file. no need to start with <code>classpath:</code>
   */
  public static OpenAPI loadFromClasspath(String classpath) throws IOException {

    String specString = ClasspathFileUtils.getFileContent(classpath);

    OpenAPIV3Parser parser = new OpenAPIV3Parser();
    return parser.readContents(specString).getOpenAPI();

  }

  /**
   * Read OpenAPI V3 from an url or local disk, and get parse result.
   * @param location
   * @return
   */
  public static OpenAPI loadFromLocation(String location) {

    OpenAPIV3Parser parser = new OpenAPIV3Parser();
    return parser.read(location);

  }
}
