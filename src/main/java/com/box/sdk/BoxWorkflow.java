package com.box.sdk;

import org.jose4j.json.internal.json_simple.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 */
@BoxResourceType("workflow")
public class BoxWorkflow extends BoxResource{

    /**
     * Workflow URL Template
     */
    public static final URLTemplate WORKFLOW_URL_TEMPLATE =
            new URLTemplate("https://publicapi-sandbox.ibmbrsandbox.com");

    public BoxWorkflow(BoxAPIConnection api, String id) {
        super(api, id);
    }

    public static void getAllTemplates(BoxAPIConnection api) {
        try{
            URL url = new URL("https://publicapi-sandbox.ibmbrsandbox.com");
            BoxJSONRequest request = new BoxJSONRequest(api, url, "POST");

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("query", "{templates (first:3) {items {id name}}}");

            request.setBody(jsonObject.toString());
            BoxJSONResponse response = (BoxJSONResponse) request.send();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
