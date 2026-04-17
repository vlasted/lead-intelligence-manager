package com.leadmanager.view;

import com.leadmanager.dao.LeadDAO;
import com.leadmanager.model.Lead;

import javax.swing.*;
import java.awt.*;

public class AddLeadFrame extends JDialog {

    private final JTextField nombreField;
    private final JTextField apellidosField;
    private final JTextField emailField;
    private final JTextField telefonoField;
    private final JTextField cargoField;
    private final JTextField interesField;
    private final JTextField puntuacionField;
    private final JTextArea observacionesArea;

    private final JComboBox<String> prioridadCombo;
    private final JComboBox<String> empresaCombo;
    private final JComboBox<String> fuenteCombo;
    private final JComboBox<String> estadoCombo;
    private final JComboBox<String> usuarioCombo;

    private final LeadDAO leadDAO;
    private final Runnable onLeadAdded;

    public AddLeadFrame(JFrame parent, Runnable onLeadAdded) {
        super(parent, "Añadir lead", true);

        this.leadDAO = new LeadDAO();
        this.onLeadAdded = onLeadAdded;

        setSize(500, 600);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 10));

        nombreField = new JTextField();
        apellidosField = new JTextField();
        emailField = new JTextField();
        telefonoField = new JTextField();
        cargoField = new JTextField();
        interesField = new JTextField();
        puntuacionField = new JTextField("0");
        observacionesArea = new JTextArea(4, 20);

        prioridadCombo = new JComboBox<>(new String[]{"alta", "media", "baja"});
        empresaCombo = new JComboBox<>(new String[]{
                "1 - DataNova Consulting",
                "2 - BlueRetail Group",
                "3 - FinCore Solutions",
                "4 - GreenLogistics Hub"
        });
        fuenteCombo = new JComboBox<>(new String[]{
                "1 - Formulario web",
                "2 - LinkedIn",
                "3 - Campaña Ads",
                "4 - Recomendacion"
        });
        estadoCombo = new JComboBox<>(new String[]{
                "1 - Nuevo",
                "2 - Contactado",
                "3 - En seguimiento",
                "4 - Descartado",
                "5 - Convertido"
        });
        usuarioCombo = new JComboBox<>(new String[]{
                "1 - Lenin Ramirez",
                "2 - Marta Lopez",
                "3 - Carlos Ruiz"
        });

        formPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        formPanel.add(new JLabel("Nombre *"));
        formPanel.add(nombreField);

        formPanel.add(new JLabel("Apellidos"));
        formPanel.add(apellidosField);

        formPanel.add(new JLabel("Email *"));
        formPanel.add(emailField);

        formPanel.add(new JLabel("Telefono"));
        formPanel.add(telefonoField);

        formPanel.add(new JLabel("Cargo"));
        formPanel.add(cargoField);

        formPanel.add(new JLabel("Interes"));
        formPanel.add(interesField);

        formPanel.add(new JLabel("Puntuacion"));
        formPanel.add(puntuacionField);

        formPanel.add(new JLabel("Prioridad *"));
        formPanel.add(prioridadCombo);

        formPanel.add(new JLabel("Empresa *"));
        formPanel.add(empresaCombo);

        formPanel.add(new JLabel("Fuente *"));
        formPanel.add(fuenteCombo);

        formPanel.add(new JLabel("Estado *"));
        formPanel.add(estadoCombo);

        formPanel.add(new JLabel("Usuario responsable *"));
        formPanel.add(usuarioCombo);

        formPanel.add(new JLabel("Observaciones"));
        formPanel.add(new JScrollPane(observacionesArea));

        JButton saveButton = new JButton("Guardar lead");
        saveButton.addActionListener(e -> saveLead());

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(saveButton);

        add(formPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void saveLead() {
        String nombre = nombreField.getText().trim();
        String email = emailField.getText().trim();

        if (nombre.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Nombre y email son obligatorios.",
                    "Validación",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int puntuacion;
        try {
            puntuacion = Integer.parseInt(puntuacionField.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "La puntuación debe ser un número entero.",
                    "Validación",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        Lead lead = new Lead();
        lead.setNombre(nombre);
        lead.setApellidos(apellidosField.getText().trim());
        lead.setEmail(email);
        lead.setTelefono(telefonoField.getText().trim());
        lead.setCargo(cargoField.getText().trim());
        lead.setInteres(interesField.getText().trim());
        lead.setPuntuacion(puntuacion);
        lead.setPrioridad((String) prioridadCombo.getSelectedItem());
        lead.setObservaciones(observacionesArea.getText().trim());
        lead.setIdEmpresa(extractId((String) empresaCombo.getSelectedItem()));
        lead.setIdFuente(extractId((String) fuenteCombo.getSelectedItem()));
        lead.setIdEstado(extractId((String) estadoCombo.getSelectedItem()));
        lead.setIdUsuarioResponsable(extractId((String) usuarioCombo.getSelectedItem()));

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

    private int extractId(String value) {
        return Integer.parseInt(value.split(" - ")[0]);
    }
}