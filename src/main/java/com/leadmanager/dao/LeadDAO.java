package com.leadmanager.dao;

import com.leadmanager.model.Lead;
import com.leadmanager.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LeadDAO {

    public List<Lead> getAllLeads() {
        List<Lead> leads = new ArrayList<>();

        String sql = "SELECT * FROM leads ORDER BY id_lead";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Lead lead = new Lead();

                lead.setIdLead(resultSet.getInt("id_lead"));
                lead.setNombre(resultSet.getString("nombre"));
                lead.setApellidos(resultSet.getString("apellidos"));
                lead.setEmail(resultSet.getString("email"));
                lead.setTelefono(resultSet.getString("telefono"));
                lead.setCargo(resultSet.getString("cargo"));
                lead.setInteres(resultSet.getString("interes"));
                lead.setPuntuacion(resultSet.getInt("puntuacion"));
                lead.setPrioridad(resultSet.getString("prioridad"));

                Timestamp timestamp = resultSet.getTimestamp("fecha_registro");
                if (timestamp != null) {
                    LocalDateTime fechaRegistro = timestamp.toLocalDateTime();
                    lead.setFechaRegistro(fechaRegistro);
                }

                lead.setObservaciones(resultSet.getString("observaciones"));
                lead.setIdEmpresa(resultSet.getInt("id_empresa"));
                lead.setIdFuente(resultSet.getInt("id_fuente"));
                lead.setIdEstado(resultSet.getInt("id_estado"));
                lead.setIdUsuarioResponsable(resultSet.getInt("id_usuario_responsable"));

                leads.add(lead);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener los leads desde la base de datos.");
            e.printStackTrace();
        }

        return leads;
    }
}