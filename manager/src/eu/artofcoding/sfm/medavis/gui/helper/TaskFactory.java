/*
 * SFM Medavis Tool
 * Copyright (C) 2011-2012 art of coding UG (haftungsbeschränkt).
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 */
package eu.artofcoding.sfm.medavis.gui.helper;

import java.util.List;
import org.netbeans.api.progress.aggregate.AggregateProgressFactory;
import org.netbeans.api.progress.aggregate.AggregateProgressHandle;
import org.netbeans.api.progress.aggregate.ProgressMonitor;

/**
 *
 * @author rbe
 */
public class TaskFactory {

    public static void startTask(String displayName, List<AbstractTask> task, TaskCancellable cancellable, ProgressMonitor progressMonitor) {
        if (null != cancellable) {
            // TaskCancellable must have reference to task(s) to Thread#interrupt() them
            cancellable.setTask(task);
        }
        // AggregateProgressHandle
        AggregateProgressHandle aggregateProgressHandle = AggregateProgressFactory.createHandle(displayName, null, cancellable, null);
        for (AbstractTask t : task) {
            aggregateProgressHandle.addContributor(t.getProgressContributor());
        }
        // ProgressMonitor
        if (null != progressMonitor) {
            aggregateProgressHandle.setMonitor(progressMonitor);
            aggregateProgressHandle.start();
        }
        // Start task(s)
        for (AbstractTask t : task) {
            t.start();
        }
    }
}
