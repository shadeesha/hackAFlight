package com.example.hackaflight.graphql;

import graphql.ExecutionInput;
import graphql.GraphQL;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.graphql.execution.GraphQlSource;

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
    public Object bridge(@PathVariable String queryName, @RequestParam Map<String, Object> params) throws Exception {

        String path = "classpath:queries/" + queryName + ".gql";
        Resource resource = resourceLoader.getResource(path);
        InputStream inputStream = resource.getInputStream();
        String query = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("Executing Query : " + queryName + " with params " + params);
        ExecutionInput executionInput = ExecutionInput.newExecutionInput()
                .query(query)
                .variables(params)
                .build();

        GraphQL graphQL = graphQlSource.graphQl();
        return graphQL.execute(executionInput).toSpecification();
    }
}
