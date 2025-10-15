<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des Patients</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: Arial, sans-serif; background: #f4f4f4; padding: 20px; }
        .container { max-width: 1200px; margin: 0 auto; background: white; padding: 30px; border-radius: 8px; }
        h1 { color: #333; margin-bottom: 30px; }
        table { width: 100%; border-collapse: collapse; }
        th, td { padding: 12px; text-align: left; border-bottom: 1px solid #ddd; }
        th { background: #007bff; color: white; }
        tr:hover { background: #f5f5f5; }
        .btn { display: inline-block; padding: 10px 20px; background: #28a745; color: white; text-decoration: none; border-radius: 4px; margin-bottom: 20px; }
        .btn:hover { background: #218838; }
        .logout { text-align: right; margin-bottom: 20px; }
        .logout a { color: #dc3545; text-decoration: none; }
        .badge { padding: 5px 10px; border-radius: 4px; font-size: 12px; }
        .badge-success { background: #28a745; color: white; }
        .badge-warning { background: #ffc107; color: black; }
        .badge-info { background: #17a2b8; color: white; }
    </style>
</head>
<body>
<div class="container">
    <div class="logout">
        <a href="${pageContext.request.contextPath}/logout">Déconnexion</a>
    </div>

    <h1>📋 Liste des Patients du Jour</h1>

    <a href="${pageContext.request.contextPath}/infirmier/accueil-patient" class="btn">+ Nouveau Patient</a>

    <table>
        <thead>
        <tr>
            <th>Nom</th>
            <th>Prénom</th>
            <th>N° SS</th>
            <th>Heure Arrivée</th>
            <th>Statut</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${patients}" var="patient">
            <tr>
                <td>${patient.nom}</td>
                <td>${patient.prenom}</td>
                <td>${patient.numeroSecuriteSociale}</td>
                <td>${patient.heureArrivee.toLocalTime()}</td>
                <td>
                    <c:choose>
                        <c:when test="${patient.statut == 'EN_ATTENTE'}">
                            <span class="badge badge-warning">EN ATTENTE</span>
                        </c:when>
                        <c:when test="${patient.statut == 'EN_CONSULTATION'}">
                            <span class="badge badge-info">EN CONSULTATION</span>
                        </c:when>
                        <c:otherwise>
                            <span class="badge badge-success">TERMINÉ</span>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>