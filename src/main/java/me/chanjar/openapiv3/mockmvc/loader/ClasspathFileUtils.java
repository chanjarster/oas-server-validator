package me.chanjar.openapiv3.mockmvc.loader;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.io.InputStreamReader;

class ClasspathFileUtils {

  private static final Logger LOGGER = LoggerFactory.getLogger(ClasspathFileUtils.class);

  private ClasspathFileUtils() {
  }

  public static Resource getResource(String resourcePath) {
    PathMatchingResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
    return resourceResolver.getResource("classpath:" + resourcePath);
  }

  public static String getFileContent(String resourcePath) throws IOException {
    Resource resource = getResource(resourcePath);

    try (InputStreamReader reader = new InputStreamReader(resource.getInputStream())) {
      return IOUtils.toString(reader);
    }

  }

}
