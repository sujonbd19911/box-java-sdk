package com.box.sdk;

import java.net.URL;
import java.util.Iterator;

import com.eclipsesource.json.JsonObject;
import org.jose4j.json.internal.json_simple.JSONObject;

/**
 *
 */
public class BoxWorkflowIterator implements Iterator<BoxWorkflow.Info> {

    private static final long LIMIT = 100;
    private final BoxAPIConnection api;
    private final JSONObject body;
    private final JSONIterator jsonIterator;

    BoxWorkflowIterator(BoxAPIConnection api, URL url, JSONObject body) {
        this.api = api;
        this.jsonIterator = new JSONIterator(api, url, LIMIT);
        this.body = body;
    }

    @Override
    public BoxWorkflow.Info next() {
        JsonObject nextJSONObject = this.jsonIterator.next();
        String id = nextJSONObject.get("id").asString();

        BoxWorkflow workflow = new BoxWorkflow(this.api, id);
        return workflow.new Info(nextJSONObject);
    }

    @Override
    public boolean hasNext() { return this.jsonIterator.hasNext(); }

    /**
     * Remove operation is not supported.
     */
    @Override
    public void remove() { throw new UnsupportedOperationException(); }
}
