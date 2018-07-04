package me.chanjar.openapiv3.mockmvc;

import org.springframework.http.HttpMethod;

public class OpenAPIV3MockMvcValidator {


  /**
   * Build requests deduced from path definition, of which headers/parameters are all satisfies with the definition in spec.<br>
   * Currently this method only supports:
   * <ul>
   * <li>produces: application/json</li>
   * <li>consumes: application/json, text/plain, none</li>
   * <li>parameters in: body, form, query</li>
   * </ul>
   *
   * @param path             the path in OpenAPI v3 spec will be tested.
   * @param method           test specific method in path.
   * @param expectedResponse response specified in path definition. this method will check if the actual response satisfies with the definition.
   */
  public void buildGoodRequestsAndFire(String path, HttpMethod method, String expectedResponse) {

    /// TODO

  }


  /**
   * Build requests deduced from path definition, of which headers/parameters are all/partially satisfies with the definition in spec.<br>
   * For example, if a parameter is defined as <strong>required</strong>, then the request will not containsthat parameter.<br>
   * This method will calculate all possible invalid parameter/headerss combination and fire them with the requests.<br>
   * Currently this method only supports:
   * <ul>
   * <li>produces: application/json</li>
   * <li>consumes: application/json, text/plain</li>
   * <li>parameters in: body, form, query</li>
   * </ul>
   *
   * @param path             the path in OpenAPI v3 spec will be tested.
   * @param method           test specific method in path.
   * @param expectedResponse response specified in path definition. this method will check if the actual response satisfies with the definition.
   */
  public void buildBadRequestsAndFire(String path, HttpMethod method, String expectedResponse) {
    // TODO
  }

}
