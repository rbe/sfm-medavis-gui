/*
 * SFM Medavis Tool
 * Copyright (C) 2011-2012 art of coding UG (haftungsbeschränkt).
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 */
package eu.artofcoding.sfm.medavis.gui.helper;

import javax.swing.SwingWorker;
import org.netbeans.api.progress.aggregate.AggregateProgressFactory;
import org.netbeans.api.progress.aggregate.ProgressContributor;

/**
 *
 * @author rbe
 */
public abstract class AbstractSwingWorker<T, V> extends SwingWorker<T, V> {

    protected Exception exception;
    protected ProgressContributor progressContributor;

    public AbstractSwingWorker(String trackingId) {
        progressContributor = AggregateProgressFactory.createProgressContributor(trackingId);
    }

    public final Exception getException() {
        return exception;
    }

    public final ProgressContributor getProgressContributor() {
        return progressContributor;
    }
}
