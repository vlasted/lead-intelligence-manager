package com.leadmanager.app;

import com.leadmanager.dao.LeadDAO;
import com.leadmanager.model.Lead;

import java.util.List;

public class LeadTest {

    public static void main(String[] args) {
        LeadDAO leadDAO = new LeadDAO();
        List<Lead> leads = leadDAO.getAllLeads();

        if (leads.isEmpty()) {
            System.out.println("No se encontraron leads en la base de datos.");
        } else {
            System.out.println("Listado de leads:");
            for (Lead lead : leads) {
                System.out.println(lead);
            }
        }
    }
}