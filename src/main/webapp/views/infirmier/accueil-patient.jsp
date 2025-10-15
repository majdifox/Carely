<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Accueil Patient</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: Arial, sans-serif; background: #f4f4f4; padding: 20px; }
        .container { max-width: 800px; margin: 0 auto; background: white; padding: 30px; border-radius: 8px; }
        h1 { color: #333; margin-bottom: 30px; }
        .form-group { margin-bottom: 20px; }
        label { display: block; margin-bottom: 5px; color: #555; font-weight: bold; }
        input, textarea { width: 100%; padding: 10px; border: 1px solid #ddd; border-radius: 4px; }
        textarea { resize: vertical; min-height: 80px; }
        button { padding: 12px 30px; background: #28a745; color: white; border: none; border-radius: 4px; cursor: pointer; font-size: 16px; }
        button:hover { background: #218838; }
        .btn-secondary { background: #6c757d; margin-left: 10px; }
        .btn-secondary:hover { background: #5a6268; }
        .row { display: grid; grid-template-columns: 1fr 1fr; gap: 20px; }
        .logout { text-align: right; margin-bottom: 20px; }
        .logout a { color: #dc3545; text-decoration: none; }
    </style>
</head>
<body>
<div class="container">
    <div class="logout">
        <a href="${pageContext.request.contextPath}/logout">Déconnexion</a>
    </div>

    <h1>👨‍⚕️ Accueil Patient</h1>

    <form method="post" action="${pageContext.request.contextPath}/infirmier/accueil-patient">
        <h3>Informations Patient</h3>

        <div class="form-group">
            <label>Numéro Sécurité Sociale *</label>
            <input type="text" name="numeroSS" required>
        </div>

        <div class="row">
            <div class="form-group">
                <label>Nom *</label>
                <input type="text" name="nom" required>
            </div>
            <div class="form-group">
                <label>Prénom *</label>
                <input type="text" name="prenom" required>
            </div>
        </div>

        <div class="row">
            <div class="form-group">
                <label>Date de naissance *</label>
                <input type="date" name="dateNaissance" required>
            </div>
            <div class="form-group">
                <label>Téléphone</label>
                <input type="tel" name="telephone">
            </div>
        </div>

        <div class="form-group">
            <label>Adresse</label>
            <input type="text" name="adresse">
        </div>

        <div class="form-group">
            <label>Mutuelle</label>
            <input type="text" name="mutuelle">
        </div>

        <div class="form-group">
            <label>Antécédents</label>
            <textarea name="antecedents"></textarea>
        </div>

        <div class="form-group">
            <label>Allergies</label>
            <textarea name="allergies"></textarea>
        </div>

        <div class="form-group">
            <label>Traitements en cours</label>
            <textarea name="traitements"></textarea>
        </div>

        <h3 style="margin-top: 30px;">Signes Vitaux</h3>

        <div class="row">
            <div class="form-group">
                <label>Tension Artérielle *</label>
                <input type="text" name="tension" placeholder="120/80" required>
            </div>
            <div class="form-group">
                <label>Fréquence Cardiaque (bpm) *</label>
                <input type="number" name="frequenceCardiaque" required>
            </div>
        </div>

        <div class="row">
            <div class="form-group">
                <label>Température (°C) *</label>
                <input type="number" step="0.1" name="temperature" required>
            </div>
            <div class="form-group">
                <label>Fréquence Respiratoire (rpm) *</label>
                <input type="number" name="frequenceRespiratoire" required>
            </div>
        </div>

        <div class="row">
            <div class="form-group">
                <label>Poids (kg)</label>
                <input type="number" step="0.1" name="poids">
            </div>
            <div class="form-group">
                <label>Taille (cm)</label>
                <input type="number" step="0.1" name="taille">
            </div>
        </div>

        <button type="submit">Enregistrer</button>
        <a href="${pageContext.request.contextPath}/infirmier/liste-patients"><button type="button" class="btn-secondary">Voir la liste</button></a>
    </form>
</div>
</body>
</html>