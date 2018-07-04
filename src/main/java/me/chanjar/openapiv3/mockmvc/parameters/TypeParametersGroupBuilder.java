package me.chanjar.openapiv3.mockmvc.parameters;

import io.swagger.v3.oas.models.parameters.*;

import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

/**
 * According to https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.1.md#parameter-locations
 */
public class TypeParametersGroupBuilder {

  public TypeParametersGroup build(List<Parameter> effectiveParameters) {

    Map<String, List<Parameter>> paramMap = effectiveParameters.stream().collect(
        groupingBy(Parameter::getIn)
    );

    List<PathParameter> pathParams = paramMap.getOrDefault("path", emptyList())
        .stream().map(p -> (PathParameter) p).collect(toList());

    List<QueryParameter> queryParams = paramMap.getOrDefault("query", emptyList())
        .stream().map(p -> (QueryParameter) p).collect(toList());

    List<CookieParameter> cookieParams = paramMap.getOrDefault("cookie", emptyList())
        .stream().map(p -> (CookieParameter) p).collect(toList());

    List<HeaderParameter> headerParams = paramMap.getOrDefault("header", emptyList())
        .stream().map(p -> (HeaderParameter) p).collect(toList());

    TypeParametersGroup typeParametersGroup = new TypeParametersGroup();
    typeParametersGroup.setPathParameters(pathParams);
    typeParametersGroup.setQueryParameters(queryParams);
    typeParametersGroup.setCookieParameters(cookieParams);
    typeParametersGroup.setHeaderParameters(headerParams);
    return typeParametersGroup;

  }

}
