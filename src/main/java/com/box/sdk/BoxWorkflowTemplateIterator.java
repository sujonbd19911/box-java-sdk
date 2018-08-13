package com.box.sdk;

import java.net.URL;
import java.util.Iterator;

import com.eclipsesource.json.JsonObject;
import org.jose4j.json.internal.json_simple.JSONObject;

/**
 *
 */
public class BoxWorkflowTemplateIterator implements Iterator<BoxWorkflowTemplate.Info> {

    private static final long LIMIT = 100;
    private final BoxAPIConnection api;
    private final WorkflowJSONIterator jsonIterator;

    BoxWorkflowTemplateIterator(BoxAPIConnection api, URL url, JSONObject body) {
        this.api = api;
        this.jsonIterator = new WorkflowJSONIterator(api, url, LIMIT, body);
    }

    @Override
    public BoxWorkflowTemplate.Info next() {
        JsonObject nextJSONObject = this.jsonIterator.next();
        String id = nextJSONObject.get("id").asString();

        BoxWorkflowTemplate template = new BoxWorkflowTemplate(this.api, id);
        return template.new Info(nextJSONObject);
    }

    @Override
    public boolean hasNext() { return this.jsonIterator.hasNext(); }

    /**
     * Remove operation is not supported.
     */
    @Override
    public void remove() { throw new UnsupportedOperationException(); }
}
