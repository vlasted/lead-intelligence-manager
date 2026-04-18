package com.leadmanager.view;

import com.leadmanager.dao.LeadDAO;
import com.leadmanager.model.Lead;

import javax.swing.*;
import java.awt.*;

public class AddLeadFrame extends JDialog {

    private final LeadDAO leadDAO;
    private final Runnable onLeadAdded;
    private final LeadFormPanel formPanel;

    public AddLeadFrame(JFrame parent, Runnable onLeadAdded) {
        super(parent, "Añadir lead", true);

        this.leadDAO = new LeadDAO();
        this.onLeadAdded = onLeadAdded;
        this.formPanel = new LeadFormPanel();

        setSize(500, 600);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        JButton saveButton = new JButton("Guardar lead");
        saveButton.addActionListener(e -> saveLead());

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(saveButton);

        add(formPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void saveLead() {
        if (!formPanel.hasRequiredFields()) {
            JOptionPane.showMessageDialog(this,
                    "Nombre y email son obligatorios.",
                    "Validación",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        Lead lead = new Lead();

        try {
            formPanel.fillLead(lead);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "La puntuación debe ser un número entero.",
                    "Validación",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        boolean inserted = leadDAO.insertLead(lead);

        if (inserted) {
            JOptionPane.showMessageDialog(this,
                    "Lead añadido correctamente.",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);

            if (onLeadAdded != null) {
                onLeadAdded.run();
            }

            dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                    "No se pudo añadir el lead.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}