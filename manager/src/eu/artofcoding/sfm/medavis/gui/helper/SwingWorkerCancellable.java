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
import javax.swing.SwingWorker;
import org.openide.util.Cancellable;

/**
 *
 * @author rbe
 */
public class SwingWorkerCancellable<T, V> implements Cancellable {

    private List<? extends SwingWorker<T, V>> task;

    public void setTask(List<? extends SwingWorker<T, V>> task) {
        //this.task = new ArrayList<? extends SwingWorker<T, V>>(task);
        this.task = (List<? extends SwingWorker<T, V>>) Collections.unmodifiableList(task);
    }

    @Override
    public boolean cancel() {
        for (SwingWorker<T, V> t : task) {
            if (!t.isDone()) {
                if (!t.cancel(true)) {
                    GuiHelper.errorBubble("SwingWorker", "Beenden des Threads " + t.toString() + " = false", null);
                }
            }
        }
        return true;
    }
}