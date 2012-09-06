/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.artofcoding.sfm.medavis.gui.manager;

import org.netbeans.api.progress.aggregate.ProgressContributor;
import org.netbeans.api.progress.aggregate.ProgressMonitor;

/**
 *
 * @author rbe
 */
public class ImportCSVProgressMonitor implements ProgressMonitor {

    @Override
    public void progressed(ProgressContributor contributor) {
        System.out.println(contributor.getTrackingId() + " progressed");
    }

    @Override
    public void started(ProgressContributor contributor) {
        System.out.println(contributor.getTrackingId() + " started");
    }

    @Override
    public void finished(ProgressContributor contributor) {
        System.out.println(contributor.getTrackingId() + " finished");
    }
}
