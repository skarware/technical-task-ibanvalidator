package edu.task.technical.ibanvalidator.servlet;

import org.apache.commons.validator.routines.IBANValidator;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class IBANValidationServlet extends HttpServlet {

    // Get a singleton instance of the IBAN validator using the default formats
    private final IBANValidator ibanValidator = IBANValidator.getInstance();

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String iban = req.getParameter("iban");

        res.setContentType("application/json");
        res.setStatus(HttpServletResponse.SC_OK);
        res.getWriter().println("{\"" + iban + "\": \"" + ibanValidator.isValid(iban) + "\"}");
    }

}
