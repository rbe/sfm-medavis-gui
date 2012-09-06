/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.artofcoding.sfm.medavis.gui.manager;

import eu.artofcoding.sfm.medavis.gui.helper.AbstractSwingWorker;
import eu.artofcoding.sfm.medavis.gui.helper.GuiHelper;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 *
 * @author rbe
 */
public class ExportCSVSwingWorker extends AbstractSwingWorker<Boolean, File> {

    private final String viewName;
    private final File file;
    private final String url;
    
    private HttpResponse httpResponse;
    private HttpClient httpClient;
    private HttpGet httpGET;

    public ExportCSVSwingWorker(String viewName, File file, String url) {
        super(viewName);
        this.viewName = viewName;
        this.file = file;
        this.url = url;
        // HttpClient
        httpClient = new DefaultHttpClient();
    }

    @Override
    protected Boolean doInBackground() throws Exception {
        boolean b = false;
        progressContributor.start(3);
        // GET file
        httpGET = new HttpGet(url);
        try {
            progressContributor.progress("Downloading from " + url, 1);
            // Execute request and get response
            httpResponse = httpClient.execute(httpGET);
            HttpEntity entity = httpResponse.getEntity();
            if (httpResponse.getStatusLine().getStatusCode() == 200 && null != entity) {
                progressContributor.progress("Writing file " + file.getAbsolutePath(), 1);
                OutputStream fos = new FileOutputStream(file);
                entity.writeTo(fos);
                fos.close();
                progressContributor.progress("Data written to file " + file.getAbsolutePath(), 1);
                GuiHelper.errorBubble("Export CSV", "CSV-Datei wurde erfolgreich erstellt und heruntergeladen (" + file.getName() + ")!", new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }
                });
            }
        } catch (Exception ex) {
            exception = ex;
            GuiHelper.errorBubble("Export CSV", "Leider konnte die CSV-Datei (" + file.getName() + ") nicht erstellt werden: " + ex.getMessage(), new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    throw new UnsupportedOperationException("Not supported yet.");
                }
            });
        } finally {
            progressContributor.finish();
            // When HttpClient instance is no longer needed, shut down the connection manager to ensure immediate deallocation of all system resources
            httpClient.getConnectionManager().shutdown();
        }
        return b;
    }

    @Override
    protected void process(List<File> chunks) {
        super.process(chunks);
        for (File f : chunks) {
            GuiHelper.infoBubble(viewName, "Datei " + f.getName() + " wurde erfolgreich heruntergeladen!", null);
        }
    }

}
