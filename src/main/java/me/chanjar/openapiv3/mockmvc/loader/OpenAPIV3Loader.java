package me.chanjar.openapiv3.mockmvc.loader;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;
import io.swagger.v3.parser.core.models.ParseOptions;
import io.swagger.v3.parser.core.models.SwaggerParseResult;
import org.apache.commons.lang3.StringUtils;

public class OpenAPIV3Loader {

  /**
   * Read OpenAPI V3 yaml file from classpath, and get parse result.
   *
   * @param classpath classpath to OpenAPI V3 spec file. no need to start with <code>classpath:</code>
   */
  public static OpenAPI loadFromClasspath(String classpath) {

    SwaggerParseResult swaggerParseResult;
    try {
      String specString = ClasspathFileUtils.getFileContent(classpath);

      OpenAPIV3Parser parser = new OpenAPIV3Parser();

      swaggerParseResult = parser.readContents(specString);

    } catch (Exception e) {

      throw new OpenAPIV3LoaderException("Exception while loading", e);

    }

    assertResultNotNull(swaggerParseResult);
    return swaggerParseResult.getOpenAPI();

  }

  /**
   * Read OpenAPI V3 yaml file from an url or local disk, and get parse result.
   *
   * @param location
   * @return
   */
  public static OpenAPI loadFromLocation(String location) {

    ParseOptions options = new ParseOptions();
    options.setResolve(true);

    OpenAPIV3Parser parser = new OpenAPIV3Parser();
    SwaggerParseResult swaggerParseResult = parser.readLocation(location, null, options);

    assertResultNotNull(swaggerParseResult);
    return swaggerParseResult.getOpenAPI();

  }

  private static void assertResultNotNull(SwaggerParseResult swaggerParseResult) {

    OpenAPI openAPI = swaggerParseResult.getOpenAPI();
    if (openAPI == null) {

      throw new OpenAPIV3LoaderException(
          "Exception while loading: " + StringUtils.join(swaggerParseResult.getMessages(), '\n'));

    }

  }

}
