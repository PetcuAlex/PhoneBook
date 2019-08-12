package org.Alex.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.Alex.service.ContactService;
import org.Alex.transfer.SaveContactRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ContactServlet extends HttpServlet {

    private ContactService contactService = new ContactService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        ObjectMapper objectMapper = new ObjectMapper();
        SaveContactRequest request = objectMapper.readValue(req.getReader(), SaveContactRequest.class);
        try {
            ContactService.createContact(request);
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500,"Internal server error: " + e.getMessage());
        }

    }
}
