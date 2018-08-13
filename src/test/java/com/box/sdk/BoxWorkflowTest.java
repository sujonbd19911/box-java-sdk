package com.box.sdk;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import sun.java2d.pipe.SpanShapeRenderer;

import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Iterator;

/**
 *
 */
public class BoxWorkflowTest {

    @Test
    @Category(IntegrationTest.class)
    public void testGetAllTemplates() throws MalformedURLException {
        BoxAPIConnection api = new BoxAPIConnection("VG4iWTbQSIEUXEDgBdUkh01oBvEEB2Rn");

        try {
            Iterable<BoxWorkflowTemplate.Info> iterable = BoxWorkflowTemplate.getAllTemplates(api, 3, "referenceName", "version");
            Iterator<BoxWorkflowTemplate.Info> iterator = iterable.iterator();
            for (int i = 0; i < 5; i++){
                BoxWorkflowTemplate.Info workflowTemplateInfo = iterator.next();
                System.out.println("Workflow Template Info: " + workflowTemplateInfo);
            }
        } finally {
            System.out.println("All done.");
        }

    }
}
