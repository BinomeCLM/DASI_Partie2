/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    // ajout d'un "handler" sur le clic du bouton de Confirmer Inscription
    recupererInfoEmploye();
    afficherPrediction();
    
    $('#deconnexion').on('click', function () {
        seDeconnecter();
    });
});

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
    });
};

// Pour la navBar
function remplirChampEmploye(data) {
    $('.possibilite').prepend(data.nom);
}

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
function recupererInfoClientPourConsultation() {
    $.ajax({
        url:'./ActionServlet', 
        type:'POST',
        data: {
            action:'RecupererInfoClientPourConsultation'
        },
        dataType:'json'
    })
    .done(function(data){
        alert(data);
        remplirChampClient(data);
    });
};
function afficherPrediction() {
    $.ajax({
        url:'./ActionServlet',
        type:'POST',
        data: {
            action:'ObtenirPrediction'
        },
        dataType:'json'
    })
    .done(function(data){
        alert('affichage des prédictions');
        remplirChampPrediction(data);
    });
};

function remplirChampPrediction(data) {
    $('#client').html('Prédiction pour ' + data.prenomClient + ' ' + data.nomClient + ' #' + data.idClient);
    $('#amourResultat').html('Amour (' + data.amourVal + '/5):');
    $('#amourString').html(data.amourStr);
    $('#santeResultat').html('Sante (' + data.santeVal + '/5):');
    $('#santeString').html(data.santeStr);
    $('#travailResultat').html('Travail (' + data.travailVal + '/5):');
    $('#travailString').html(data.travailStr);
}

function remplirChampClient(data) {
    $('#infoClient').html('Prediction pour ' + data.prenom + ' ' + data.nom + ' #' + data.id);
}