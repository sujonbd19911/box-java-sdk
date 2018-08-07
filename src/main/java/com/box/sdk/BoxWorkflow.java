package com.box.sdk;

import org.jose4j.json.internal.json_simple.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.Date;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

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

    public static BoxWorkflow.Info getAllTemplates(BoxAPIConnection api) {
        BoxWorkflow createdWorkflow = null;
        JsonObject responseJSON = null;
        try{
            URL url = new URL("https://publicapi-sandbox.ibmbrsandbox.com");
            BoxJSONRequest request = new BoxJSONRequest(api, url, "POST");

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("query", "{templates (first:3) {items {id name}}}");

            request.setBody(jsonObject.toString());
            BoxJSONResponse response = (BoxJSONResponse) request.send();
            responseJSON = JsonObject.readFrom(response.getJSON());

            createdWorkflow = new BoxWorkflow(api, responseJSON.get("id").asString());
        } catch (MalformedURLException e) {
            System.out.println("Error: "+ e);
        }
        return createdWorkflow.new Info(responseJSON);
    }

    /**
     * Contains information about a BoxWorkflow.
     */
    public class Info extends BoxResource.Info {
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
        private BoxWorkflowTemplate template;
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

        /**
         * Get the name of the Box Workflow specified.
         * @return  the name of the workflow.
         */
        public String getName() { return this.name; }

        /**
         * Get the date and time the workflow was created.
         * @return  the Date and time of the creation of the workflow.
         */
        public Date getCreated() { return this.created; }

        /**
         * Get the BoxUser that created the workflow.
         * @return  BoxUser that created the workflow.
         */
        public BoxUser.Info getCreatedBy() { return this.createdBy; }

        /**
         * The date and time the workflow was last modified.
         * @return  Date and time the workflow was modified.
         */
        public Date getModified() { return this.modified; }

        /**
         * The BoxUser that modified the workflow.
         * @return  BoxUser that modified the workflow.
         */
        public BoxUser.Info getModifiedBy() { return this.modifiedBy; }

        /**
         * The date and time the workflow was completed.
         * @return  Date and time of workflow completion.
         */
        public Date getCompleted() { return this.completed; }

        /**
         * The BoxUser that completed the workflow.
         * @return  BoxUser that completed workflow.
         */
        public BoxUser.Info getCompletedBy() { return this.completedBy; }

        /**
         * The tasks associated with the workflow.
         * @return  An array of BoxWorkflowTasks.
         */
        public BoxWorkflowTask[] getTasks() {
            return this.tasks;
        }

        /**
         * Get the state of the workflow.
         * @return  Return either 'Working', 'CancelPending', 'Pending', 'Complete', 'Canceled', or 'Failed'.
         */
        public String getState() { return this.state; }

        /**
         * Workflow next milestone.
         * @return  The next milstone for the workflow.
         */
        public String getNextMilestone() { return this.nextMilestone; }

        /**
         * The template for the given workflow.
         * @return  The template for the given workflow.
         */
        public BoxWorkflowTemplate getTemplate() { return this.template; }

        /**
         * The timezone name.
         * @return The timezone name for the workflow.
         */
        public String getTimeZone() { return this.timeZone; }

        /**
         * Indicates if the workflow is flagged.
         * @return  Whether the workflow is flagged.
         */
        public boolean getIsFlagged() { return this.isFlagged; }

        /**
         * Indicates if the workflow is stalled.
         * @return  Whether the workflow is stalled.
         */
        public boolean getIsStalled() { return this.isStalled; }

        /**
         * Indicates if the workflow is overdue.
         * @return  Whether the workflow is overdue.
         */
        public boolean getIsOverDue() { return this.isOverdue; }

        public void setName(String name) {
            this.name = name;
        }

        /**
         * Sets the state of the workflow.
         * @param state The state of the workflow.
         */
        public void setState(String state) {
            this.state = state;
        }

        /**
         * Sets the next milestone for the workflow.
         * @param nextMilestone The next milestone for the workflow.
         */
        public void setNextMilestone(String nextMilestone) {
            this.nextMilestone = nextMilestone;
        }

        /**
         * Sets the template for the workflow.
         * @param template  The template for the workflow.
         */
        public void setTemplate(BoxWorkflowTemplate template) {
            this.template = template;
        }

        /**
         * Sets the time zone for the workflow.
         * @param timeZone  The time zone of the workflow.
         */
        public void setTimeZone(String timeZone) {
            this.timeZone = timeZone;
        }

        /**
         * Indicates if the workflow is flagged.
         * @param isFlagged Flag to indicate if workflow is flagged.
         */
        public void setIsFlagged(boolean isFlagged) {
            this.isFlagged = isFlagged;
        }

        /**
         * Indicates if the workflow is installed.
         * @param isStalled Flag to indicate if workflow is installed.
         */
        public void setIsStalled(boolean isStalled) {
            this.isStalled = isStalled;
        }

        /**
         * Indicates if the workflow is overdue.
         * @param isOverdue Flag to indicate if workflow is overdue.
         */
        public void setIsOverdue(boolean isOverdue) {
            this.isOverdue = isOverdue;
        }

        @Override
        public BoxResource getResource() { return BoxWorkflow.this; }

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
                } else if (name.equals("state")) {
                    this.state = value.asString();
                } else if (name.equals("nextMilestone")) {
                    this.nextMilestone = value.asString();
                } else if (name.equals("template")) {

                } else if (name.equals("timeZone")) {
                    this.timeZone = value.asString();
                } else if (name.equals("isFlagged")) {
                    this.isFlagged = value.asBoolean();
                } else if (name.equals("isStalled")) {
                    this.isStalled = value.asBoolean();
                } else if (name.equals("isOverdue")) {
                    this.isOverdue = value.asBoolean();
                }
            } catch(ParseException e) {
                System.out.println("Parsing failed with error: " + e.getMessage());
                assert false : "A ParseException indicates a bug in the SDK.";
            }
        }
    }
}
