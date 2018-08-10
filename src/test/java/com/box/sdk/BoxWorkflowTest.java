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
        BoxAPIConnection api = new BoxAPIConnection("");
        BoxWorkflow.getAllTemplates(api);
    }
}
