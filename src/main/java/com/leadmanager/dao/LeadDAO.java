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

    public boolean insertLead(Lead lead) {
        String sql = "INSERT INTO leads " +
                "(nombre, apellidos, email, telefono, cargo, interes, puntuacion, prioridad, observaciones, " +
                "id_empresa, id_fuente, id_estado, id_usuario_responsable) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, lead.getNombre());
            statement.setString(2, emptyToNull(lead.getApellidos()));
            statement.setString(3, lead.getEmail());
            statement.setString(4, emptyToNull(lead.getTelefono()));
            statement.setString(5, emptyToNull(lead.getCargo()));
            statement.setString(6, emptyToNull(lead.getInteres()));
            statement.setInt(7, lead.getPuntuacion());
            statement.setString(8, lead.getPrioridad());
            statement.setString(9, emptyToNull(lead.getObservaciones()));
            statement.setInt(10, lead.getIdEmpresa());
            statement.setInt(11, lead.getIdFuente());
            statement.setInt(12, lead.getIdEstado());
            statement.setInt(13, lead.getIdUsuarioResponsable());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al insertar el lead en la base de datos.");
            e.printStackTrace();
            return false;
        }
    }

    private String emptyToNull(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        return value.trim();
    }

    public Lead getLeadById(int idLead) {
        String sql = "SELECT * FROM leads WHERE id_lead = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, idLead);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
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
                        lead.setFechaRegistro(timestamp.toLocalDateTime());
                    }

                    lead.setObservaciones(resultSet.getString("observaciones"));
                    lead.setIdEmpresa(resultSet.getInt("id_empresa"));
                    lead.setIdFuente(resultSet.getInt("id_fuente"));
                    lead.setIdEstado(resultSet.getInt("id_estado"));
                    lead.setIdUsuarioResponsable(resultSet.getInt("id_usuario_responsable"));

                    return lead;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener el lead por ID.");
            e.printStackTrace();
        }

        return null;
    }

    public boolean updateLead(Lead lead) {
        String sql = "UPDATE leads SET " +
                "nombre = ?, apellidos = ?, email = ?, telefono = ?, cargo = ?, interes = ?, " +
                "puntuacion = ?, prioridad = ?, observaciones = ?, id_empresa = ?, id_fuente = ?, " +
                "id_estado = ?, id_usuario_responsable = ? " +
                "WHERE id_lead = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, lead.getNombre());
            statement.setString(2, emptyToNull(lead.getApellidos()));
            statement.setString(3, lead.getEmail());
            statement.setString(4, emptyToNull(lead.getTelefono()));
            statement.setString(5, emptyToNull(lead.getCargo()));
            statement.setString(6, emptyToNull(lead.getInteres()));
            statement.setInt(7, lead.getPuntuacion());
            statement.setString(8, lead.getPrioridad());
            statement.setString(9, emptyToNull(lead.getObservaciones()));
            statement.setInt(10, lead.getIdEmpresa());
            statement.setInt(11, lead.getIdFuente());
            statement.setInt(12, lead.getIdEstado());
            statement.setInt(13, lead.getIdUsuarioResponsable());
            statement.setInt(14, lead.getIdLead());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar el lead en la base de datos.");
            e.printStackTrace();
            return false;
        }
    }
}