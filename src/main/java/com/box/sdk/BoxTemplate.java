package com.box.sdk;

import java.text.ParseException;
import java.util.Date;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

import netscape.javascript.JSObject;

/**
 *  The base class for BoxTemplates.
 */
public class BoxTemplate extends BoxResource {

    /**
     * Constructs a BoxTemplate with a given ID.
     * @param api   the API connection to be used by the BoxTemplate.
     * @param id    the ID of the BoxTemplate.
     */
    public BoxTemplate(BoxAPIConnection api, String id) { super(api, id); }

    /**
     * Contains information about a BoxTemplate.
     */
    public abstract class Info extends BoxResource.Info {
        private String name;
        private String version;
        private String referenceName;
        private JSObject description;
        private Date created;
        private BoxUser.Info createdBy;
        private Date modified;
        private BoxUser.Info modifiedBy;
        private Date published;
        private BoxUser.Info publishedBy;
        private String state;
        private JsonArray workflows;
        private String launchMode;

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
         * @param jsonObject the parsed JSON object.
         */
        Info(JsonObject jsonObject) { super(jsonObject); }

        /**
         * Gets the name of the Box Workflow Template.
         * @return the name of the specified template.
         */
        public String getName() { return this.name; }

        /**
         * Gets the version of the Box Workflow Template.
         * @return the version of the specified template.
         */
        public String getVersion() { return this.version; }

        /**
         * Gets the reference name for the Box Workflow Template.
         * @return  the reference name of the specified template.
         */
        public String getReferenceName() { return this.referenceName; }

        /**
         * Gets the created date time for the template.
         * @return  date time of creation for the template.
         */
        public Date getCreated() { return this.created; }

        /**
         * Gets the BoxUser that created the template.
         * @return  the BoxUser info object.
         */
        public BoxUser.Info getCreatedBy() { return this.createdBy; }

        /**
         * Gets the modified date time for the template.
         * @return  date time of creation for the template.
         */
        public Date getModified() { return this.modified; }

        /**
         * Gets the BoxUser that modified the template.
         * @return  the BoxUser info object.
         */
        public BoxUser.Info getModifiedBy() { return this.modifiedBy; }

        /**
         * Gets the created date time for the template.
         * @return  date time of creation for the template.
         */
        public Date getPublished() { return this.published; }

        /**
         * Gets the BoxUser that published the template.
         * @return  the BoxUser info object.
         */
        public BoxUser.Info getPublishedBy() { return this.publishedBy; }

        /**
         * Gets the current state of the workflow template.
         * @return  the state of the template.
         */
        public String getState() { return this.state; }

        /**
         * Gets the launch mode of the workflow state.
         * @return the launch mode of the template.
         */
        public String getLaunchMode() { return this.launchMode; }

        /**
         * Sets the name of the workflow template.
         * @param name  the new name of the template.
         */
        public void setName(String name) {
            this.name = name;
            this.addPendingChange("name", name);
        }

        /**
         * Sets the version of the workflow template.
         * @param version   the version of the template.
         */
        public void setVersion(String version) {
            this.version = version;
            this.addPendingChange("version", version);
        }

        /**
         * Sets the reference name of the workflow template.
         * @param referenceName     the reference name of the template.
         */
        public void setReferenceName(String referenceName) {
            this.referenceName = referenceName;
            this.addPendingChange("referenceName", referenceName);
        }

        /**
         * The published template state.
         * @param state   Set to either "Active", "Inactive", "Pending", or "Deleted".
         */
        public void setState(String state) {
            this.state = state;
            this.addPendingChange("state", state);
        }

        /**
         * The launch mode of the template.
         * @param launchMode    Set to either "ManualLaunch" or "AutoLaunch".
         */
        public void setLaunchMode(String launchMode) {
            this.launchMode = launchMode;
            this.addPendingChange("launchMode", launchMode);
        }

        @Override
        protected void parseJSONMember(JsonObject.Member member) {
            super.parseJSONMember(member);

            try {
                JsonValue value = member.getValue();
                String name = member.getName();
                if(name.equals("name")) {
                    this.name = value.asString();
                } else if (name.equals("version")) {
                    this.version = value.asString();
                } else if (name.equals("referenceName")) {
                    this.referenceName = value.asString();
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
                } else if (name.equals("published")) {
                    this.published = BoxDateFormat.parse(value.asString());
                } else if (name.equals("createdBy")) {
                    JsonObject userJSON = value.asObject();
                    if (this.publishedBy == null) {
                        String userID = userJSON.get("id").asString();
                        BoxUser user = new BoxUser(getAPI(), userID);
                        this.publishedBy = user.new Info(userJSON);
                    } else {
                        this.publishedBy.update(userJSON);
                    }
                } else if (name.equals("state")) {
                    this.state = value.asString();
                } else if (name.equals("launchMode")) {
                    this.launchMode = value.asString();
                }
            } catch(ParseException e) {
                System.out.println("Parse failed with error: " + e.getMessage());
                assert false : "A ParseException indicates a bug in the SDK.";
            }
        }
    }
}
