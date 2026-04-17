package com.leadmanager.view;

import com.leadmanager.dao.LeadDAO;
import com.leadmanager.model.Lead;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MainMenuFrame extends JFrame {

    private JTable tableLeads;
    private DefaultTableModel tableModel;
    private final LeadDAO leadDAO;

    public MainMenuFrame() {
        this.leadDAO = new LeadDAO();

        setTitle("Lead Intelligence Manager");
        setSize(1000, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        initComponents();
        loadLeads();
    }

    private void initComponents() {
        JLabel titleLabel = new JLabel("Lead Intelligence Manager", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        String[] columnNames = {
                "ID", "Nombre", "Apellidos", "Email", "Prioridad", "Puntuación", "Fecha registro"
        };

        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableLeads = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableLeads);
        add(scrollPane, BorderLayout.CENTER);

        JButton addButton = new JButton("Añadir lead");
        addButton.addActionListener(e -> openAddLeadDialog());

        JButton refreshButton = new JButton("Recargar leads");
        refreshButton.addActionListener(e -> loadLeads());

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(addButton);
        bottomPanel.add(refreshButton);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void openAddLeadDialog() {
        AddLeadFrame dialog = new AddLeadFrame(this, this::loadLeads);
        dialog.setVisible(true);
    }

    private void loadLeads() {
        tableModel.setRowCount(0);

        List<Lead> leads = leadDAO.getAllLeads();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        for (Lead lead : leads) {
            Object[] row = {
                    lead.getIdLead(),
                    lead.getNombre(),
                    lead.getApellidos(),
                    lead.getEmail(),
                    lead.getPrioridad(),
                    lead.getPuntuacion(),
                    lead.getFechaRegistro() != null ? lead.getFechaRegistro().format(formatter) : ""
            };

            tableModel.addRow(row);
        }
    }
}