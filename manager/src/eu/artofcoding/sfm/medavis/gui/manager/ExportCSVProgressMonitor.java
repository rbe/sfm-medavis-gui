/*
 * SFM Medavis Tool
 * Copyright (C) 2011-2012 art of coding UG (haftungsbeschränkt).
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 */
package eu.artofcoding.sfm.medavis.gui.manager;

import org.netbeans.api.progress.aggregate.ProgressContributor;
import org.netbeans.api.progress.aggregate.ProgressMonitor;

/**
 *
 * @author rbe
 */
public class ExportCSVProgressMonitor implements ProgressMonitor {

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
