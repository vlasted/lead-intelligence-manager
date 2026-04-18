package com.leadmanager.dao;

import com.leadmanager.model.Usuario;
import com.leadmanager.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    public Usuario authenticateUser(String email, String contrasena) {
        String sql = "SELECT * FROM usuarios WHERE email = ? AND contrasena = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, email);
            statement.setString(2, contrasena);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setIdUsuario(resultSet.getInt("id_usuario"));
                    usuario.setNombre(resultSet.getString("nombre"));
                    usuario.setEmail(resultSet.getString("email"));
                    usuario.setContrasena(resultSet.getString("contrasena"));
                    usuario.setRol(resultSet.getString("rol"));
                    return usuario;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al autenticar el usuario.");
            e.printStackTrace();
        }

        return null;
    }
}