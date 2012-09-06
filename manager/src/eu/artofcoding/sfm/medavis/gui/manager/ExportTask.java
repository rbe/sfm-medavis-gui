/*
 * SFM Medavis Tool
 * Copyright (C) 2011-2012 art of coding UG (haftungsbeschränkt).
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 */
package eu.artofcoding.sfm.medavis.gui.manager;

import eu.artofcoding.sfm.medavis.gui.helper.AbstractTask;
import eu.artofcoding.sfm.medavis.gui.helper.GuiHelper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 *
 * @author rbe
 */
public class ExportTask extends AbstractTask {

    private final File file;
    private final String url;
    private HttpResponse httpResponse;
    private HttpClient httpClient;
    private HttpGet httpGET;

    public ExportTask(String trackingId, File file, String url) {
        super(trackingId);
        this.file = file;
        this.url = url;
        // HttpClient
        httpClient = new DefaultHttpClient();
    }

    public HttpResponse getHttpResponse() {
        return httpResponse;
    }

    @Override
    public void run() {
        progressContributor.start(3);
        // GET file
        httpGET = new HttpGet(url);
        try {
            progressContributor.progress("Lade von " + url, 1);
            // Execute request and get response
            httpResponse = httpClient.execute(httpGET);
            HttpEntity entity = httpResponse.getEntity();
            if (httpResponse.getStatusLine().getStatusCode() == 200 && null != entity) {
                progressContributor.progress("Schreibe Datei " + file.getName(), 1);
                OutputStream fos = new FileOutputStream(file);
                entity.writeTo(fos);
                fos.close();
                progressContributor.progress("Daten in Datei " + file.getName() + " geschrieben", 1);
                GuiHelper.infoBubble("CSV Export", "<html>Die CSV-Datei (" + file.getName() + ") wurde erfolgreich heruntergeladen!</html>", null);
            }
        } catch (IOException ex) {
            exception = ex;
            GuiHelper.errorBubble("CSV Export", "<html>Leider konnte die CSV-Datei (" + file.getName() + ") nicht erstellt werden:<br/>" + ex.getMessage() + "</html>", null);
        } finally {
            progressContributor.finish();
            // When HttpClient instance is no longer needed, shut down the connection manager to ensure immediate deallocation of all system resources
            httpClient.getConnectionManager().shutdown();
        }
    }

    @Override
    public void interrupt() {
        super.interrupt();
        httpGET.abort();
    }
}
