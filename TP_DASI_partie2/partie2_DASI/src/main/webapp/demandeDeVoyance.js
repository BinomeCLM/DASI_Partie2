/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    var url = new URL(window.location.href);
    var idEmploye = url.searchParams.get("idEmploye");
    connecterEmploye(idEmploye);
    
    $('#demarrerVoyance').on('click', function () {
        demarrerVoyance();
    });
    $('#demanderPrediction').on('click', function () {
        genererPrediction();
    });
    $('#deconnexion').on('click', function () {
        seDeconnecter();
    });
});

function seDeconnecter(){
    $.ajax({
        url:'./ActionServlet',
        type:'POST',
        data: {
            action:'Deconnecter' // Est-ce qu'on en fait une pour l'employe
            // et une pour le cient ou alors separement (pour l'instant je fais les deux en meme 
            // temps --> voir ActionServlet
        },
        dataType:'json'
    })
    .done(function(){
        alert('deconnexion reussi');
        // On redirige vers la page de connexion
        window.location="connexion.html";
    });
}
// Méthode permettant de connecter l'employé
// On le fait ici parce-qu'ils n'ont pas fait de service connecterEmploye
// donc on le simule ...
function connecterEmploye(idEmploye) {
    $.ajax({
       url : './ActionServlet',
       type: 'POST',
       data : {
           action : 'ConnexionEmploye',
           idEmp : idEmploye
       },
       dataType : 'text'
    })
    .done(function(data){
        if (data){
            alert('Employe est connecté');
            recupererInfoEmploye(); // On récupére les infos nécessaires une fois
            // est connecté
        }
    });
}

function demarrerVoyance() {
   $.ajax({
       url : './ActionServlet',
       type: 'POST',
       data : {
           action : 'StartPrestation'
       },
       dataType : 'json'
    })
    .done(function(data){
        alert('demarrerVoyance');
        if (data.id>0){
            openInNewTab("consultation.html");
        }
        else {
            alert('Aucun client pour le moment. Impossible de démarrer une voyance.');
        }
        
    });
}

function openInNewTab(url) {
  var win = window.open(url, '_blank');
  win.focus();
}

function recupererInfoClientPourEmp() {
    $.ajax({
        url:'./ActionServlet',
        type:'POST',
        data: {
            action:'RecupererInfoClientPourEmp'
        },
        dataType:'json'
    })
    .done(function(data){
        alert('remplirChampClient');
        remplirChampClient(data);
    });
};

function recupererInfoMedium() {
    $.ajax({
        url:'./ActionServlet',
        type:'POST',
        data: {
            action:'RecupererInfoMedium'
        },
        dataType:'json'
    })
    .done(function(data){
        alert('remplirChampMedium');
        remplirChampMedium(data);
    });
};


function recupererInfoEmploye( ) {
    alert("recupInfoEmp");
    $.ajax({
        url:'./ActionServlet',
        type:'POST',
        data: {
            action:'RecupererInfoEmp'
        },
        dataType:'json'
    })
    .done(function(data){
        alert('On recupere les infos de lemploye');
        remplirChampEmploye(data);
        recupererInfoClientPourEmp();
        recupererInfoMedium();
    });
};

function remplirChampClient(data) {
    if (data.id > 0){
        var today = new Date();
        var year = Number(data.dateDeNaissance.substr(6, 10)); 
        var age = today.getFullYear() - year;
        alert(year);
        $('#infoClient').html(data.civilite + '. ' + data.prenom + ' ' + data.nom + ' | '
                + age + 'ans | #' + data.id);
    }
    else
    {
        $('#incfoClient').html('Aucun client pour le moment');
        //$('#infoClient').css('display','hide');
    }
}

function remplirChampMedium(data) {
    if (data.id>0){
        $('#infoMedium').html('Médium demandé: ' + data.nom + ' | '
            + data.talent + ' | ');
    }
    /*if (data.metier === 'Tarologue'){
        $('#infoMedium').append(data.cartes);
    } 
    else if (data.metier === 'Astrologue') {
        $('#infoMedium').append('Ecole : ' + data.ecole + ' promotion ' + data.promotion);
    }
    else {
        $('#infoMedium').append(data.support);
    } 
*/}

// Pour la navBar
function remplirChampEmploye(data) {
    $('.possibilite').prepend(data.nom);
}

// Pour générer les prédictions
function genererPrediction () {
    $.ajax({
        url:'./ActionServlet',
        type:'POST',
        data: {
            action:'GenererPrediction',
            amour: $('#amour').val(),
            sante: $('#sante').val(),
            travail: $('#travail').val()
        },
        dataType:'text'
    })
    .done(function(data){
        alert(data);
        if (data){
            openInNewTab("prediction.html"); 
        }
        else {
            alert('done_erreur');
            $('#msgErreur').html("Erreur lors de l'envoie des données pour la prédiction");
        }
    });
}