package me.chanjar.oas.server.validator.springmockmvc;

import me.chanjar.oas.server.validator.core.ValidationErrorException;
import me.chanjar.oas.server.validator.core.engine.OASResponseValidator;
import me.chanjar.oas.server.validator.core.interaction.OASResponseSpec;
import org.springframework.test.web.servlet.ResultActions;

import java.io.StringWriter;
import java.text.MessageFormat;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class SpringMockMvcResponseValidator implements OASResponseValidator<ResultActions> {

  @Override
  public void validate(ResultActions resultActions, OASResponseSpec oasResponseSpec) {

    String requestInfo = null;

    try (StringWriter sw = new StringWriter()) {

      resultActions.andDo(print(sw));
      requestInfo = sw.toString();

      doValidation(resultActions, oasResponseSpec);

    } catch (AssertionError e) {

      throw new AssertionError(
          MessageFormat.format("Assertion Error: {}\nRequest info: {}", e.getMessage(), requestInfo),
          e
      );
    } catch (Exception e) {

      throw new ValidationErrorException(
          MessageFormat.format("Exception: {}\nRequest info: {}", e.getMessage(), requestInfo),
          e
      );

    }

  }

  private void doValidation(ResultActions resultActions, OASResponseSpec oasResponseSpec) throws Exception {

    // TODO status code
    // TODO Content-Type header
    // TODO other headers
    // TODO body schema validation

  }

}
