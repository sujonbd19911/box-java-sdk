package com.box.sdk;

import org.jose4j.json.internal.json_simple.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.Date;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

import netscape.javascript.JSObject;

/**
 *
 */
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
            System.out.println("Response: " + response);
        } catch (MalformedURLException e) {
            System.out.println("Error: "+ e);
        }
    }

    /**
     * Contains information about a BoxWorkflow.
     */
    public abstract class Info extends BoxResource.Info {
        private String name;
        private Date created;
        private BoxUser.Info createdBy;
        private Date modified;
        private BoxUser.Info modifiedBy;
        private Date completed;
        private BoxUser.Info completedBy;
        private BoxWorkflowTask[] tasks;
        private String state;
        private String nextMilestone;
        private BoxTemplate template;
        private String timeZone;
        private boolean isFlagged;
        private boolean isStalled;
        private boolean isOverdue;

        /**
         * Constructs an empty Info object.
         */
        public Info() { super(); }

        /**
         * Constructs an Info object by parsing information from a JSON string.
         * @param json  the JSON string to parse.
         */
        public Info(String json) { super(json); }

        /**
         * Constructs an Info object using an already parsed JSON object.
         * @param jsonObject    the parsed JSON object.
         */
        Info(JsonObject jsonObject) { super(jsonObject); }

        @Override
        protected void parseJSONMember(JsonObject.Member member) {
            super.parseJSONMember(member);

            try {
                JsonValue value = member.getValue();
                String name = member.getName();
                if (name.equals("name")) {
                    this.name = value.asString();
                } else if (name.equals("created")) {
                    this.created = BoxDateFormat.parse(value.asString());
                } else if (name.equals("createdBy")) {
                    JsonObject userJSON = value.asObject();
                    if (this.createdBy == null) {
                        String userID = userJSON.get("id").asString();
                        BoxUser user = new BoxUser(getAPI(), userID);
                        this.createdBy = user.new Info(userJSON);
                    } else {
                        this.createdBy.update(userJSON);
                    }
                } else if (name.equals("modified")) {
                    this.modified = BoxDateFormat.parse(value.asString());
                } else if (name.equals("modifiedBy")) {
                    JsonObject userJSON = value.asObject();
                    if (this.modifiedBy == null) {
                        String userID = userJSON.get("id").asString();
                        BoxUser user = new BoxUser(getAPI(), userID);
                        this.modifiedBy = user.new Info(userJSON);
                    } else {
                        this.modifiedBy.update(userJSON);
                    }
                } else if (name.equals("completed")) {
                    this.completed = BoxDateFormat.parse(value.asString());
                } else if (name.equals("completedBy")) {
                    JsonObject userJSON = value.asObject();
                    if (this.completedBy == null) {
                        String userID = userJSON.get("id").asString();
                        BoxUser user = new BoxUser(getAPI(), userID);
                        this.completedBy = user.new Info(userJSON);
                    } else {
                        this.completedBy.update(userJSON);
                    }
                }
            } catch(ParseException e) {
                System.out.println("Parsing failed with error: " + e.getMessage());
                assert false : "A ParseException indicates a bug in the SDK.";
            }
        }
    }
}
