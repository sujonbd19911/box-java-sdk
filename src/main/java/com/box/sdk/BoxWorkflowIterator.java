package com.box.sdk;

import java.net.URL;
import java.util.Iterator;

import com.eclipsesource.json.JsonObject;

/**
 *
 */
public class BoxWorkflowIterator implements Iterator<BoxWorkflow.Info> {

    private static final long LIMIT = 100;
    private final BoxAPIConnection api;
    private final JSONIterator jsonIterator;

    BoxWorkflowIterator(BoxAPIConnection api, URL url) {
        this.api = api;
        this.jsonIterator = new JSONIterator(api, url, LIMIT);
    }

    @Override
    public BoxWorkflow.Info next() {

    }

    @Override
    public boolean hasNext() { return this.jsonIterator.hasNext(); }
}
