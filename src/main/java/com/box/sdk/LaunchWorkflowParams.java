package com.box.sdk;

/**
 * Contains optional parameters for launching a workflow on Box.
 */
public class LaunchWorkflowParams {
    private String name;
    private String description;
    private String owner;
    private String fileID;
    private String triggerID;
    private String timeZone;

    /**
     * Gets the name of the workflow.
     * @return The name of the workflow.
     */
    public String getName() { return this.name; }

    /**
     * Sets the name of the workflow.
     * @param name  The name of workflow.
     * @return      This creates a LaunchWorkflowParams object for chaining.
     */
    public LaunchWorkflowParams setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() { return this.description; }

    public LaunchWorkflowParams setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getOwner() { return this.owner; }

    public LaunchWorkflowParams setOwner(String owner) {
        this.owner = owner;
        return this;
    }

    public String getFileID() { return this.fileID; }

    public LaunchWorkflowParams setFileID(String fileID) {
        this.fileID = fileID;
        return this;
    }

    public String getTriggerID() { return this.triggerID; }

    public LaunchWorkflowParams setTriggerID(String triggerID) {
        this.triggerID = triggerID;
        return this;
    }

    public String getTimeZone() { return this.timeZone; }

    public LaunchWorkflowParams setTimeZone() {
        this.timeZone = timeZone;
        return this;
    }
}
