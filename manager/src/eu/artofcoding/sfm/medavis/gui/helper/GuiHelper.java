/*
 * SFM Medavis Tool
 * Copyright (C) 2011-2012 art of coding UG (haftungsbeschränkt).
 *
 * Alle Rechte vorbehalten. Nutzung unterliegt Lizenzbedingungen.
 * All rights reserved. Use is subject to license terms.
 *
 */
package eu.artofcoding.sfm.medavis.gui.helper;

import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.Notification;
import org.openide.awt.NotificationDisplayer;
import org.openide.util.ImageUtilities;

/**
 *
 * @author rbe
 */
public class GuiHelper {

    /**
     *
     * @param title
     * @param message
     */
    public static void infoBubble(String title, String message, ActionListener actionListener) {
        ImageIcon imageIcon = ImageUtilities.loadImageIcon("eu/artofcoding/sfm/medavis/gui/resource/info16.png", false);
        //ActionListener actionListener = Lookups.forPath("NotificationActions").lookup(ActionListener.class);
        NotificationDisplayer.Priority priority = NotificationDisplayer.Priority.NORMAL;
        Notification n = NotificationDisplayer.getDefault().notify(title, imageIcon, message, actionListener, priority);
    }

    /**
     *
     * @param title
     * @param message
     */
    public static void errorBubble(String title, String message, ActionListener actionListener) {
        ImageIcon imageIcon = ImageUtilities.loadImageIcon("eu/artofcoding/sfm/medavis/gui/resource/error16.png", false);
        //ActionListener actionListener = Lookups.forPath("NotificationActions").lookup(ActionListener.class);
        NotificationDisplayer.Priority priority = NotificationDisplayer.Priority.NORMAL;
        Notification n = NotificationDisplayer.getDefault().notify(title, imageIcon, message, actionListener, priority);
    }

    /**
     * Display error message.
     *
     * @param title
     * @param message
     */
    public static void errorMessage(String title, String message) {
        NotifyDescriptor d = new NotifyDescriptor.Message(title);
        d.setMessageType(NotifyDescriptor.ERROR_MESSAGE);
        d.setMessage(message);
        DialogDisplayer.getDefault().notify(d);
    }

    /**
     * Display warning message.
     *
     * @param title
     * @param message
     */
    public static void warningMessage(String title, String message) {
        NotifyDescriptor d = new NotifyDescriptor.Message(title);
        d.setMessageType(NotifyDescriptor.WARNING_MESSAGE);
        d.setMessage(message);
        DialogDisplayer.getDefault().notify(d);
    }

    /**
     * Display informational message.
     *
     * @param title
     * @param message
     */
    public static void infoMessage(String title, String message) {
        NotifyDescriptor d = new NotifyDescriptor.Message(title);
        d.setMessageType(NotifyDescriptor.INFORMATION_MESSAGE);
        d.setMessage(message);
        DialogDisplayer.getDefault().notify(d);
    }
}
