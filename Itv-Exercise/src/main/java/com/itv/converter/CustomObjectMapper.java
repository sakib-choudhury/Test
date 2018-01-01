package com.itv.converter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * Created by sakibchoudhury on 29/12/17.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomObjectMapper {

    public static ObjectMapper buildObjectMapper() {
         ObjectMapper objectMapper
                 = Jackson2ObjectMapperBuilder.json()
                                            .serializationInclusion(JsonInclude.Include.NON_NULL) // Don’t include null values
                                            .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS) //ISODate
                                            .modules(new JSR310Module())
                                            .build();

        return objectMapper;
    }
}
