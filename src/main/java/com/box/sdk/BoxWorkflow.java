package com.box.sdk;

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
        HttpPost httpPost = new HttpPost("https://publicapi-sandbox.ibmbrsandbox.com/");
    }
}
