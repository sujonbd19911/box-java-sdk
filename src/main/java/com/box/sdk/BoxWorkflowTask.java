package com.box.sdk;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import com.eclipsesource.json.ParseException;

/**
 * The base class for BoxWorkflowTasks.
 */
public class BoxWorkflowTask extends  BoxResource{

    /**
     * Constructs a BoxWorkflowTasj with a given ID.
     * @param api   the API connection to be used by the BoxWorkflowTask.
     * @param id    the ID of the BoxWorkflowTask.
     */
    public BoxWorkflowTask(BoxAPIConnection api, String id) { super(api, id); }

    /**
     * Contains information about a BoxWorkflowTask.
     */
    public abstract class Info extends BoxResource.Info {
        private String name;
        private String type;
        private String created;
        private BoxUser.Info completedBy;
        private String completed;
        private String state;

        /**
         * Constructs an empty Info object.
         */
        public Info() { super(); }

        /**
         * Constructs an Info object by parsing information from a JSON string.
         * @param json the JSON string to parse.
         */
        public Info(String json) { super(json); }

        /**
         * Constructs an Info object using an already parsed JSON object.
         * @param jsonObject    the parsed JSON object.
         */
        Info(JsonObject jsonObject) { super(jsonObject); }

        /**
         * Returns the name of the Box Workflow Task.
         * @return
         */
        public String getName() { return this.name; }

        /**
         * Returns the BoxUser the workflow task was completed by.
         * @return  BoxUser that completed the workflow task.
         */
        public BoxUser.Info getCompletedBy() { return this.completedBy; }

        /**
         * Returns the workflow task state.
         * @return  the state of the workflow state.
         */
        public String getState() { return this.state; }

        /**
         * Sets the name of the workflow task.
         * @param name  the new task name for the workflow task.
         */
        public void setName(String name) {
            this.name = name;
            this.addPendingChange("name", name);
        }

        /**
         * Sets the state of the workflow task.
         * @param state Set to "Pending", "Working", "Failed", "Superseded", "Complete", or "Canceled".
         */
        public void setState(String state) {
            this.state = state;
            this.addPendingChange("state", state);
        }

        @Override
        protected void parseJSONMember(JsonObject.Member member) {
            super.parseJSONMember(member);

            try {
                JsonValue value = member.getValue();
                String name = member.getName();
                if (name.equals("name")) {
                    this.name = value.asString();
                } else if (name.equals("completedBy")) {
                    JsonObject userJSON = value.asObject();
                    if (this.completedBy == null) {
                        String userID = userJSON.get("id").asString();
                        BoxUser user = new BoxUser(getAPI(), userID);
                        this.completedBy = user.new Info(userJSON);
                    } else {
                        this.completedBy.update(userJSON);
                    }
                } else if (name.equals("completed")) {
                    this.completed = value.asString();
                } else if (name.equals("state")) {
                    this.state = value.asString();
                }
            } catch (ParseException e) {
                System.out.println("Parse failed with error: " + e.getMessage());
                assert false : "A ParseException indicates a bug in the SDK.";
            }
        }
    }
}
