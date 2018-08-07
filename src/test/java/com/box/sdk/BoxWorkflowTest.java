package com.box.sdk;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.net.MalformedURLException;

/**
 *
 */
public class BoxWorkflowTest {

    @Test
    @Category(IntegrationTest.class)
    public void testGetAllTemplates() throws MalformedURLException {
        BoxAPIConnection api = new BoxAPIConnection("o6kJhZq0S1G3iyzPYq3Lj1FT9dciWjkS");
        BoxWorkflow.getAllTemplates(api);
    }
}
