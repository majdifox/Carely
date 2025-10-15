package com.carely.servlet;

import com.carely.entity.Patient;
import com.carely.repository.PatientRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/infirmier/liste-patients")
public class ListePatientsServlet extends HttpServlet {

    private PatientRepository patientRepo = new PatientRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Patient> patients = patientRepo.findByDate(LocalDate.now());
        request.setAttribute("patients", patients);
        request.getRequestDispatcher("/views/infirmier/liste-patients.jsp").forward(request, response);
    }
}