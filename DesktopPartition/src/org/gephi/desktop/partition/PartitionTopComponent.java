/*
Copyright 2008 WebAtlas
Authors : Mathieu Bastian, Mathieu Jacomy, Julian Bilcke
Website : http://www.gephi.org

This file is part of Gephi.

Gephi is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Gephi is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Gephi.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.gephi.desktop.partition;

import java.io.Serializable;
import java.util.logging.Logger;
import org.gephi.partition.api.PartitionController;
import org.gephi.partition.api.PartitionModel;
import org.gephi.project.api.ProjectController;
import org.gephi.workspace.api.Workspace;
import org.gephi.workspace.api.WorkspaceDataKey;
import org.gephi.workspace.api.WorkspaceListener;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
//import org.openide.util.Utilities;

final class PartitionTopComponent extends TopComponent {

    private static PartitionTopComponent instance;
    /** path to the icon used by the component and its open action */
//    static final String ICON_PATH = "SET/PATH/TO/ICON/HERE";
    private static final String PREFERRED_ID = "PartitionTopComponent";

    private PartitionTopComponent() {
        initComponents();
        setName(NbBundle.getMessage(PartitionTopComponent.class, "CTL_PartitionTopComponent"));
        setToolTipText(NbBundle.getMessage(PartitionTopComponent.class, "HINT_PartitionTopComponent"));
//        setIcon(Utilities.loadImage(ICON_PATH, true));

        initEvents();
        ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
        PartitionController partitionController = Lookup.getDefault().lookup(PartitionController.class);
        if (pc.getCurrentWorkspace() != null) {
            PartitionModel model = (PartitionModel) pc.getCurrentWorkspace().getWorkspaceData().getData("PartitionModel");
            refreshModel(model);
        }
    }

    private void initEvents() {
        ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
        pc.addWorkspaceListener(new WorkspaceListener() {

            public void initialize(Workspace workspace) {
            }

            public void select(Workspace workspace) {
                PartitionModel model = (PartitionModel) workspace.getWorkspaceData().getData("PartitionModel");
                refreshModel(model);
            }

            public void unselect(Workspace workspace) {
                refreshModel(null);
            }

            public void close(Workspace workspace) {
            }

            public void disable() {
            }
        });
    }

    private void refreshModel(PartitionModel model) {
        if (model != null) {
            ((PartitionToolbar) partitionToolbar).setup(model);
            ((PartitionChooser) partitionChooser).setup(model);
        } else {
            ((PartitionToolbar) partitionToolbar).unsetup();
            ((PartitionChooser) partitionChooser).unsetup();
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        partitionToolbar = new PartitionToolbar();
        partitionChooser = new PartitionChooser();
        resultPanel = new javax.swing.JPanel();

        setLayout(new java.awt.GridBagLayout());

        partitionToolbar.setRollover(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        add(partitionToolbar, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        add(partitionChooser, gridBagConstraints);

        resultPanel.setPreferredSize(new java.awt.Dimension(0, 0));

        javax.swing.GroupLayout resultPanelLayout = new javax.swing.GroupLayout(resultPanel);
        resultPanel.setLayout(resultPanelLayout);
        resultPanelLayout.setHorizontalGroup(
            resultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 325, Short.MAX_VALUE)
        );
        resultPanelLayout.setVerticalGroup(
            resultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 245, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 1, 5);
        add(resultPanel, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel partitionChooser;
    private javax.swing.JToolBar partitionToolbar;
    private javax.swing.JPanel resultPanel;
    // End of variables declaration//GEN-END:variables

    /**
     * Gets default instance. Do not use directly: reserved for *.settings files only,
     * i.e. deserialization routines; otherwise you could get a non-deserialized instance.
     * To obtain the singleton instance, use {@link #findInstance}.
     */
    public static synchronized PartitionTopComponent getDefault() {
        if (instance == null) {
            instance = new PartitionTopComponent();
        }
        return instance;
    }

    /**
     * Obtain the PartitionTopComponent instance. Never call {@link #getDefault} directly!
     */
    public static synchronized PartitionTopComponent findInstance() {
        TopComponent win = WindowManager.getDefault().findTopComponent(PREFERRED_ID);
        if (win == null) {
            Logger.getLogger(PartitionTopComponent.class.getName()).warning(
                    "Cannot find " + PREFERRED_ID + " component. It will not be located properly in the window system.");
            return getDefault();
        }
        if (win instanceof PartitionTopComponent) {
            return (PartitionTopComponent) win;
        }
        Logger.getLogger(PartitionTopComponent.class.getName()).warning(
                "There seem to be multiple components with the '" + PREFERRED_ID +
                "' ID. That is a potential source of errors and unexpected behavior.");
        return getDefault();
    }

    @Override
    public int getPersistenceType() {
        return TopComponent.PERSISTENCE_ALWAYS;
    }

    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    /** replaces this in object stream */
    @Override
    public Object writeReplace() {
        return new ResolvableHelper();
    }

    @Override
    protected String preferredID() {
        return PREFERRED_ID;
    }

    final static class ResolvableHelper implements Serializable {

        private static final long serialVersionUID = 1L;

        public Object readResolve() {
            return PartitionTopComponent.getDefault();
        }
    }
}
