package com.leadmanager.view;

import com.leadmanager.model.Lead;

import javax.swing.*;
import java.awt.*;

public class LeadFormPanel extends JPanel {

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

    public LeadFormPanel() {
        setLayout(new GridLayout(0, 2, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

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

        add(new JLabel("Nombre *"));
        add(nombreField);

        add(new JLabel("Apellidos"));
        add(apellidosField);

        add(new JLabel("Email *"));
        add(emailField);

        add(new JLabel("Telefono"));
        add(telefonoField);

        add(new JLabel("Cargo"));
        add(cargoField);

        add(new JLabel("Interes"));
        add(interesField);

        add(new JLabel("Puntuacion"));
        add(puntuacionField);

        add(new JLabel("Prioridad *"));
        add(prioridadCombo);

        add(new JLabel("Empresa *"));
        add(empresaCombo);

        add(new JLabel("Fuente *"));
        add(fuenteCombo);

        add(new JLabel("Estado *"));
        add(estadoCombo);

        add(new JLabel("Usuario responsable *"));
        add(usuarioCombo);

        add(new JLabel("Observaciones"));
        add(new JScrollPane(observacionesArea));
    }

    public boolean hasRequiredFields() {
        return !nombreField.getText().trim().isEmpty() &&
                !emailField.getText().trim().isEmpty();
    }

    public void fillLead(Lead lead) throws NumberFormatException {
        int puntuacion = Integer.parseInt(puntuacionField.getText().trim());

        lead.setNombre(nombreField.getText().trim());
        lead.setApellidos(apellidosField.getText().trim());
        lead.setEmail(emailField.getText().trim());
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
    }

    public void loadLead(Lead lead) {
        nombreField.setText(lead.getNombre());
        apellidosField.setText(lead.getApellidos());
        emailField.setText(lead.getEmail());
        telefonoField.setText(lead.getTelefono());
        cargoField.setText(lead.getCargo());
        interesField.setText(lead.getInteres());
        puntuacionField.setText(String.valueOf(lead.getPuntuacion()));
        observacionesArea.setText(lead.getObservaciones());

        prioridadCombo.setSelectedItem(lead.getPrioridad());
        selectComboById(empresaCombo, lead.getIdEmpresa());
        selectComboById(fuenteCombo, lead.getIdFuente());
        selectComboById(estadoCombo, lead.getIdEstado());
        selectComboById(usuarioCombo, lead.getIdUsuarioResponsable());
    }

    private int extractId(String value) {
        return Integer.parseInt(value.split(" - ")[0]);
    }

    private void selectComboById(JComboBox<String> comboBox, int id) {
        for (int i = 0; i < comboBox.getItemCount(); i++) {
            String item = comboBox.getItemAt(i);
            if (extractId(item) == id) {
                comboBox.setSelectedIndex(i);
                break;
            }
        }
    }
}