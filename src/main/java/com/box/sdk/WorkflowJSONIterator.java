package com.box.sdk;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.NoSuchElementException;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import com.eclipsesource.json.ParseException;
import org.jose4j.json.internal.json_simple.JSONObject;

import javax.management.Query;
import java.util.Iterator;

/**
 *
 */
public class WorkflowJSONIterator implements Iterator<JsonObject> {
    private final BoxAPIConnection api;
    private final URL url;

    private long limit;
    private long offset;
    private long totalCount;
    private JSONObject body;
    private boolean hasMorePages;
    private Iterator<JsonValue> currentPage;
    private JsonObject nextJsonObject;
    private Filter<JsonObject> filter;

    public WorkflowJSONIterator(BoxAPIConnection api, URL url, long limit, JSONObject body) {
        this.api = api;
        this.url = url;
        this.limit = limit;
        this.body = body;
    }

    public boolean hasNext() {
        if (this.nextJsonObject == null) {
            this.nextJsonObject = this.loadNextJsonObject();
        }

        return this.nextJsonObject != null;
    }

    public JsonObject next() {
        return null;
    }

    public void remove() { throw new UnsupportedOperationException(); }

    public void setFilter(Filter<JsonObject> filter) { this.filter = filter; }

    public void loadNextPage() {
        String existingQuery = this.url.getQuery();

        URL url = this.url;
        BoxAPIRequest request = new BoxAPIRequest(this.api, url, "POST");
        request.setBody(this.body.toString());
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        String json = response.getJSON();

        JsonObject jsonObject = JsonObject.readFrom(json);
        String totalCountString = jsonObject.get("count").toString();
        this.totalCount = Double.valueOf(totalCountString).longValue();
        String offsetString = jsonObject.get("offset").toString();

        JsonArray jsonArray = jsonObject.get("items").asArray();
        this.currentPage = jsonArray.iterator();
    }

    public JsonObject loadNextJsonObject() {
        if (this.currentPage == null) {
            this.loadNextPage();
        }

        while (this.currentPage.hasNext() || this.hasMorePages) {
            while (this.currentPage.hasNext()) {
                JsonObject jsonObject = this.currentPage.next().asObject();
                if (this.filter == null || this.filter.shouldInclude(jsonObject)) {
                    return jsonObject;
                }
            }

            if (this.hasMorePages) {
                this.loadNextPage();
            }
        }
        return null;
    }
}
