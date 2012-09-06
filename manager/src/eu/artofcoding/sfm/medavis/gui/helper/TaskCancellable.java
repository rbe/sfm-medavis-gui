/*
 * SFM Medavis Tool
 * Copyright (C) 2011-2012 art of coding UG (haftungsbeschränkt).
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 */
package eu.artofcoding.sfm.medavis.gui.helper;

import java.util.Collections;
import java.util.List;
import org.openide.util.Cancellable;

/**
 *
 * @author rbe
 */
public class TaskCancellable implements Cancellable {

    private List<? extends Thread> task;

    public void setTask(List<? extends Thread> task) {
        //this.task = new ArrayList<? extends SwingWorker<T, V>>(task);
        this.task = (List<? extends Thread>) Collections.unmodifiableList(task);
    }

    @Override
    public boolean cancel() {
        for (Thread t : task) {
            t.interrupt();
        }
        return true;
    }
}