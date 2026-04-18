package com.leadmanager.view;

import com.leadmanager.dao.LeadDAO;
import com.leadmanager.model.Lead;
import com.leadmanager.model.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MainMenuFrame extends JFrame {

    private JTable tableLeads;
    private DefaultTableModel tableModel;
    private final LeadDAO leadDAO;
    private final Usuario usuarioLogueado;

    public MainMenuFrame(Usuario usuarioLogueado) {
        this.leadDAO = new LeadDAO();
        this.usuarioLogueado = usuarioLogueado;

        setTitle("Lead Intelligence Manager");
        setSize(1220, 700);
        setMinimumSize(new Dimension(1100, 620));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        ((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(12, 22, 12, 22));

        initComponents();
        loadLeads();
    }

    private void initComponents() {
        JPanel topPanel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("Lead Intelligence Manager", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 34));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(14, 0, 8, 0));

        String textoUsuario = "Usuario: ";
        if (usuarioLogueado != null) {
            textoUsuario += usuarioLogueado.getNombre() + " (" + usuarioLogueado.getRol() + ")";
        } else {
            textoUsuario += "sin sesión";
        }

        JLabel userLabel = new JLabel(textoUsuario, SwingConstants.CENTER);
        userLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        userLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        JPanel titleContainer = new JPanel(new GridLayout(2, 1));
        titleContainer.add(titleLabel);
        titleContainer.add(userLabel);

        topPanel.add(titleContainer, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);

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
        styleTable();

        JScrollPane scrollPane = new JScrollPane(tableLeads);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)));

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        JButton addButton = new JButton("Añadir lead");
        addButton.addActionListener(e -> openAddLeadDialog());

        JButton editButton = new JButton("Editar lead");
        editButton.addActionListener(e -> openEditLeadDialog());

        JButton deleteButton = new JButton("Eliminar lead");
        deleteButton.addActionListener(e -> deleteSelectedLead());

        JButton refreshButton = new JButton("Recargar leads");
        refreshButton.addActionListener(e -> loadLeads());

        JButton logoutButton = new JButton("Cerrar sesión");
        logoutButton.addActionListener(e -> logout());

        Dimension buttonSize = new Dimension(140, 36);
        addButton.setPreferredSize(buttonSize);
        editButton.setPreferredSize(buttonSize);
        deleteButton.setPreferredSize(buttonSize);
        refreshButton.setPreferredSize(buttonSize);
        logoutButton.setPreferredSize(new Dimension(150, 36));

        JPanel centerButtonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 14, 12));
        centerButtonsPanel.add(addButton);
        centerButtonsPanel.add(editButton);
        centerButtonsPanel.add(deleteButton);
        centerButtonsPanel.add(refreshButton);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 12));
        rightPanel.add(logoutButton);

        JPanel bottomContainer = new JPanel(new BorderLayout());
        bottomContainer.setBorder(BorderFactory.createEmptyBorder(8, 0, 6, 0));
        bottomContainer.add(centerButtonsPanel, BorderLayout.CENTER);
        bottomContainer.add(rightPanel, BorderLayout.EAST);

        add(bottomContainer, BorderLayout.SOUTH);
    }

    private void logout() {
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "¿Seguro que quieres cerrar sesión?",
                "Cerrar sesión",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (confirm == JOptionPane.YES_OPTION) {
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
            dispose();
        }
    }

    private void styleTable() {
        tableLeads.setRowHeight(26);
        tableLeads.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableLeads.setFillsViewportHeight(true);
        tableLeads.setGridColor(new Color(210, 210, 210));
        tableLeads.setShowVerticalLines(true);
        tableLeads.setShowHorizontalLines(true);
        tableLeads.setFont(new Font("Arial", Font.PLAIN, 14));

        JTableHeader header = tableLeads.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 14));
        header.setReorderingAllowed(false);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        tableLeads.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableLeads.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tableLeads.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);

        adjustColumnWidths();
    }

    private void adjustColumnWidths() {
        tableLeads.getColumnModel().getColumn(0).setPreferredWidth(50);
        tableLeads.getColumnModel().getColumn(1).setPreferredWidth(120);
        tableLeads.getColumnModel().getColumn(2).setPreferredWidth(140);
        tableLeads.getColumnModel().getColumn(3).setPreferredWidth(280);
        tableLeads.getColumnModel().getColumn(4).setPreferredWidth(100);
        tableLeads.getColumnModel().getColumn(5).setPreferredWidth(110);
        tableLeads.getColumnModel().getColumn(6).setPreferredWidth(170);
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

        adjustColumnWidths();
    }

    private void openAddLeadDialog() {
        AddLeadFrame dialog = new AddLeadFrame(this, this::loadLeads);
        dialog.setVisible(true);
    }

    private void openEditLeadDialog() {
        int selectedRow = tableLeads.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                    "Debes seleccionar un lead para editar.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int idLead = (int) tableModel.getValueAt(selectedRow, 0);
        Lead lead = leadDAO.getLeadById(idLead);

        if (lead == null) {
            JOptionPane.showMessageDialog(this,
                    "No se pudo cargar el lead seleccionado.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        EditLeadFrame dialog = new EditLeadFrame(this, lead, this::loadLeads);
        dialog.setVisible(true);
    }

    private void deleteSelectedLead() {
        int selectedRow = tableLeads.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                    "Debes seleccionar un lead para eliminar.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int idLead = (int) tableModel.getValueAt(selectedRow, 0);
        String nombreLead = String.valueOf(tableModel.getValueAt(selectedRow, 1));
        String apellidosLead = String.valueOf(tableModel.getValueAt(selectedRow, 2));

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "¿Seguro que quieres eliminar el lead: " + nombreLead + " " + apellidosLead + "?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        boolean deleted = leadDAO.deleteLead(idLead);

        if (deleted) {
            JOptionPane.showMessageDialog(this,
                    "Lead eliminado correctamente.",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);
            loadLeads();
        } else {
            JOptionPane.showMessageDialog(this,
                    "No se pudo eliminar el lead.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}