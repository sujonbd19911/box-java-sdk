package com.box.sdk.internal.utils;

/**
 *
 */
public class GraphQLFieldsParser {

    public String fieldsParser(String... fields) {
        StringBuilder builder = new StringBuilder();

        for (String field : fields) {
            builder.append(field);
        }

        return builder.toString();
    }
}
