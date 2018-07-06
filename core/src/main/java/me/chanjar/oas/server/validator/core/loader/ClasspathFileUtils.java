package me.chanjar.oas.server.validator.core.loader;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

class ClasspathFileUtils {

  private ClasspathFileUtils() {
  }

  public static String getFileContent(String resourcePath) throws IOException {
    InputStream inputStream = ClassLoader.getSystemResourceAsStream(resourcePath);

    try (InputStreamReader reader = new InputStreamReader(inputStream)) {
      return IOUtils.toString(reader);
    }

  }

}
