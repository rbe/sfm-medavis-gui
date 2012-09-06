/*
 * SFM Medavis Tool
 * Copyright (C) 2011-2012 art of coding UG (haftungsbeschränkt).
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 */
package eu.artofcoding.sfm.medavis.gui.helper;

import org.netbeans.api.progress.aggregate.AggregateProgressFactory;
import org.netbeans.api.progress.aggregate.ProgressContributor;

/**
 *
 * @author rbe
 */
public abstract class AbstractTask extends Thread {

    protected ProgressContributor progressContributor;

    protected Exception exception;

    public AbstractTask(String trackingId) {
        progressContributor = AggregateProgressFactory.createProgressContributor(trackingId);
   }

    public final Exception getException() {
        return exception;
    }

    public final ProgressContributor getProgressContributor() {
        return progressContributor;
    }
}
