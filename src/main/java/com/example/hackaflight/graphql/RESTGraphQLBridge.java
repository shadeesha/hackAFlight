package com.example.hackaflight.graphql;

import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.graphql.execution.GraphQlSource;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@RestController
public class RESTGraphQLBridge {

    @Autowired
    private GraphQlSource graphQlSource;

    @Autowired
    private ResourceLoader resourceLoader;

    Logger log = LoggerFactory.getLogger(RESTGraphQLBridge.class);

    @GetMapping("/{queryName}")
    public Object bridge(@PathVariable String queryName, @RequestParam Map<String, Object> params, HttpServletRequest request) throws Exception {
        try {
            log.info("REQ::BEGIN {}", request.getRequestURL());
            String path = "classpath:queries/" + queryName + ".gql";
            Resource resource = resourceLoader.getResource(path);
            InputStream inputStream = resource.getInputStream();
            String query = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
            log.info("Executing Query : {} with params {}", queryName, params);
            ExecutionInput executionInput = ExecutionInput.newExecutionInput()
                    .query(query)
                    .variables(params)
                    .build();

            GraphQL graphQL = graphQlSource.graphQl();
            ExecutionResult result = graphQL.execute(executionInput);
            log.info("Got query results successfully");
            log.info("REQ::END");
            return result.toSpecification();
        }catch (IOException e) {
            log.info("Error occurred during query execution", e);

            Map<String, Object> errorResponse = Map.of(
                    "status", HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "message", "Internal Server Error",
                    "details", "Cannot find the supported query."
            );

            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
