package com.box.sdk.internal.utils;

/**
 *
 */
public class GraphQLFieldsParser {

    /**
     * This is a helper function to take in the optional fields and create a space separated string to pass into the
     * workflow query string.
     * @param fields    The fields to retrieve from the workflow query.
     * @return  A space separated string for optional fields.
     */
    public static String parseFields(String... fields) {
        StringBuilder builder = new StringBuilder();

        for (String field : fields) {
            builder.append(" " + field);
        }

        return builder.toString();
    }
}
