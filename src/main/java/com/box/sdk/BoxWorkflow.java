package com.box.sdk;

import com.apollographql.apollo.*;
/**
 *
 */
@BoxResourceType("workflow")
public class BoxWorkflow {

    /**
     * Workflow URL Template
     */
    public static final URLTemplate WORKFLOW_URL_TEMPLATE =
            new URLTemplate("https://publicapi-sandbox.ibmbrsandbox.com/");

    public void getAllTemplates() {
        apolloClient.query(
          getAllTemplates().builder()
            .build()
        ).enqueue(new ApolloCall.Callback<FeedQuery.Data>() {
          }
        });
    }
}
