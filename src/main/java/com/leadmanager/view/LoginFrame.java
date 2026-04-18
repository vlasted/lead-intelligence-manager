package com.leadmanager.view;

import com.leadmanager.dao.UsuarioDAO;
import com.leadmanager.model.Usuario;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private final JTextField emailField;
    private final JPasswordField passwordField;
    private final UsuarioDAO usuarioDAO;

    public LoginFrame() {
        this.usuarioDAO = new UsuarioDAO();

        setTitle("Login - Lead Intelligence Manager");
        setSize(420, 260);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        ((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JLabel titleLabel = new JLabel("INICIAR SESIÓN", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));
        add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 12));

        emailField = new JTextField();
        passwordField = new JPasswordField();

        formPanel.add(new JLabel("Email"));
        formPanel.add(emailField);

        formPanel.add(new JLabel("Contraseña"));
        formPanel.add(passwordField);

        add(formPanel, BorderLayout.CENTER);

        JButton loginButton = new JButton("Entrar");
        loginButton.setPreferredSize(new Dimension(120, 35));
        loginButton.addActionListener(e -> login());

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(loginButton);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void login() {
        String email = emailField.getText().trim();
        String contrasena = new String(passwordField.getPassword()).trim();

        if (email.isEmpty() || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Debes completar email y contraseña.",
                    "Validación",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        Usuario usuario = usuarioDAO.authenticateUser(email, contrasena);

        if (usuario != null) {
            MainMenuFrame mainMenuFrame = new MainMenuFrame(usuario);
            mainMenuFrame.setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Credenciales incorrectas.",
                    "Error de acceso",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}