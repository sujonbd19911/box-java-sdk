package com.box.sdk;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.net.MalformedURLException;
import java.util.Iterator;

/**
 *
 */
public class BoxWorkflowTest {

    @Test
    @Category(IntegrationTest.class)
    public void testGetAllTemplates() throws MalformedURLException {
        BoxAPIConnection api = new BoxAPIConnection("x01q5H8mgQUQesbsNRuNa6pxDSn2i3J8");
        BoxWorkflow.getAllTemplates(api);

        try {
            Iterable<BoxWorkflow.Info> iterable = BoxWorkflow.getAllTemplates(api);
            Iterator<BoxWorkflow.Info> iterator = iterable.iterator();
            if (iterator.hasNext()) {
                BoxWorkflow.Info workflowInfo = iterator.next();
            }
        } finally {
            System.out.println("All done.");
        }

    }
}
