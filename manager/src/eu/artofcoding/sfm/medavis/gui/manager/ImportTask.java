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
import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 *
 * @author rbe
 */
public class ImportTask extends AbstractTask {

    private final File file;
    private final String url;
    private HttpResponse httpResponse;
    private HttpClient httpClient;
    private HttpPost httpPOST;

    public ImportTask(String trackingId, File file, String url) {
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
        progressContributor.start(2);
        // POST file
        httpPOST = new HttpPost(url);
        try {
            progressContributor.progress("Sende Datei (" + file.getName() + ") an " + url, 1);
            // Execute request and get response
            FileEntity reqEntity = new FileEntity(file, "binary/octet-stream");
            reqEntity.setContentType("binary/octet-stream");
            //reqEntity.setChunked(true);
            httpPOST.setEntity(reqEntity);
            // Post file and get response
            httpResponse = httpClient.execute(httpPOST);
            progressContributor.progress("Datei (" + file.getName() + ") erfolgreich an " + url + " gesendet", 1);
            GuiHelper.infoBubble("Export CSV", "<html>Die Datei (" + file.getName() + ") wurde erfolgreich hochgeladen</html>", null);
        } catch (IOException ex) {
            exception = ex;
            GuiHelper.errorBubble("Export CSV", "<html>Leider konnte die Datei (" + file.getName() + ") nicht hochgeladen werden:<br/>" + ex.getMessage() + "</html>", null);
        } finally {
            progressContributor.finish();
            // When HttpClient instance is no longer needed, shut down the connection manager to ensure immediate deallocation of all system resources
            httpClient.getConnectionManager().shutdown();
        }
    }

    @Override
    public void interrupt() {
        super.interrupt();
        httpPOST.abort();
    }
}
