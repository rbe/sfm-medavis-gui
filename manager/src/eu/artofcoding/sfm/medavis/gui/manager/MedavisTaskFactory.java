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
import eu.artofcoding.sfm.medavis.gui.helper.TaskCancellable;
import eu.artofcoding.sfm.medavis.gui.helper.TaskFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rbe
 */
public class MedavisTaskFactory {

    public static void startImportTask(File file, String url) {
        // List with tasks
        List<AbstractTask> task = new ArrayList<AbstractTask>();
        task.add(new ImportTask("ImportCSV-" + file.getName(), file, url));
        TaskFactory.startTask("CSV Import", task, new TaskCancellable(), MedavisComponentFactory.getImportCSVProgressMonitor());
    }

    public static void startExportTask(File file, String url) {
        // List with tasks
        List<AbstractTask> task = new ArrayList<AbstractTask>();
        task.add(new ExportTask("ExportCSV-" + file.getName(), file, url));
        TaskFactory.startTask("CSV Export", task, new TaskCancellable(), MedavisComponentFactory.getExportCSVProgressMonitor());
    }

}
