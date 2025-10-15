package com.carely.servlet;

import com.carely.entity.Patient;
import com.carely.entity.SignesVitaux;
import com.carely.enums.StatutPatient;
import com.carely.repository.PatientRepository;
import com.carely.repository.SignesVitauxRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/infirmier/accueil-patient")
public class AccueilPatientServlet extends HttpServlet {

    private PatientRepository patientRepo = new PatientRepository();
    private SignesVitauxRepository signesRepo = new SignesVitauxRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/infirmier/accueil-patient.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String numeroSS = request.getParameter("numeroSS");

        // Check if patient exists
        Patient patient = patientRepo.findByNumeroSS(numeroSS);

        if (patient == null) {
            // New patient
            patient = new Patient();
            patient.setNom(request.getParameter("nom"));
            patient.setPrenom(request.getParameter("prenom"));
            patient.setDateNaissance(LocalDate.parse(request.getParameter("dateNaissance")));
            patient.setNumeroSecuriteSociale(numeroSS);
            patient.setTelephone(request.getParameter("telephone"));
            patient.setAdresse(request.getParameter("adresse"));
            patient.setMutuelle(request.getParameter("mutuelle"));
            patient.setAntecedents(request.getParameter("antecedents"));
            patient.setAllergies(request.getParameter("allergies"));
            patient.setTraitementsEnCours(request.getParameter("traitements"));
            patient.setStatut(StatutPatient.EN_ATTENTE);

            patientRepo.save(patient);
        }

        // Add vital signs
        SignesVitaux signes = new SignesVitaux(patient);
        signes.setTensionArterielle(request.getParameter("tension"));
        signes.setFrequenceCardiaque(Integer.parseInt(request.getParameter("frequenceCardiaque")));
        signes.setTemperature(Double.parseDouble(request.getParameter("temperature")));
        signes.setFrequenceRespiratoire(Integer.parseInt(request.getParameter("frequenceRespiratoire")));

        String poids = request.getParameter("poids");
        if (poids != null && !poids.isEmpty()) {
            signes.setPoids(Double.parseDouble(poids));
        }

        String taille = request.getParameter("taille");
        if (taille != null && !taille.isEmpty()) {
            signes.setTaille(Double.parseDouble(taille));
        }

        signesRepo.save(signes);

        // Update patient status
        patient.setStatut(StatutPatient.EN_ATTENTE);
        patientRepo.update(patient);

        response.sendRedirect(request.getContextPath() + "/infirmier/liste-patients");
    }
}