package com.leadmanager.view;

import com.leadmanager.dao.LeadDAO;
import com.leadmanager.model.Lead;

import javax.swing.*;
import java.awt.*;

public class EditLeadFrame extends JDialog {

    private final LeadDAO leadDAO;
    private final Runnable onLeadUpdated;
    private final Lead lead;
    private final LeadFormPanel formPanel;

    public EditLeadFrame(JFrame parent, Lead lead, Runnable onLeadUpdated) {
        super(parent, "Editar lead", true);

        this.leadDAO = new LeadDAO();
        this.onLeadUpdated = onLeadUpdated;
        this.lead = lead;
        this.formPanel = new LeadFormPanel();

        setSize(500, 600);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        formPanel.loadLead(lead);

        JButton saveButton = new JButton("Guardar cambios");
        saveButton.addActionListener(e -> updateLead());

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(saveButton);

        add(formPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void updateLead() {
        if (!formPanel.hasRequiredFields()) {
            JOptionPane.showMessageDialog(this,
                    "Nombre y email son obligatorios.",
                    "Validación",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            formPanel.fillLead(lead);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "La puntuación debe ser un número entero.",
                    "Validación",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        boolean updated = leadDAO.updateLead(lead);

        if (updated) {
            JOptionPane.showMessageDialog(this,
                    "Lead actualizado correctamente.",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);

            if (onLeadUpdated != null) {
                onLeadUpdated.run();
            }

            dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                    "No se pudo actualizar el lead.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}