/*
 * SFM Medavis Tool
 * Copyright (C) 2011-2012 art of coding UG (haftungsbeschränkt).
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 */
package eu.artofcoding.sfm.medavis.gui.manager;

import org.netbeans.api.progress.aggregate.ProgressMonitor;

/**
 *
 * @author rbe
 */
public class MedavisComponentFactory {

    private static final ExportCSVProgressMonitor EXPORT_CSVPROGRESS_MONITOR;
    private static final ImportCSVProgressMonitor IMPORT_CSVPROGRESS_MONITOR;

    static {
        EXPORT_CSVPROGRESS_MONITOR = new ExportCSVProgressMonitor();
        IMPORT_CSVPROGRESS_MONITOR = new ImportCSVProgressMonitor();
    }

    public static ProgressMonitor getExportCSVProgressMonitor() {
        return EXPORT_CSVPROGRESS_MONITOR;
    }

    public static ProgressMonitor getImportCSVProgressMonitor() {
        return IMPORT_CSVPROGRESS_MONITOR;
    }
}
