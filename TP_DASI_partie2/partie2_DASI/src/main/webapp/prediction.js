/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    
    recupererInfoEmploye();
    
    $('#deconnexion').on('click', function () {
        seDeconnecter();
    });
});

function recupererInfoEmploye( ) {
    $.ajax({
        url:'./ActionServlet',
        type:'POST',
        data: {
            action:'RecupererInfoEmp'
        },
        dataType:'json'
    })
    .done(function(data){
        if (data.id > 0) {
            remplirChampEmploye(data);
            afficherPrediction();
        }
        else {
            alert('Vous allez être redirigé vers la page de connexion. En effet, vous ne vous êtes pas identifié.');
            window.location = "connexion.html";
        }
    });
};

function remplirChampEmploye(data) {
    $('.possibilite').prepend(data.nom);
}

function seDeconnecter(){
    $.ajax({
        url:'./ActionServlet',
        type:'POST',
        data: {
            action:'Deconnecter' 
        },
        dataType:'json'
    })
    .done(function(){
        window.location="connexion.html";
    });
}

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
        if (data.idClient > 0){
            remplirChampPrediction(data);
        }
        else {
            alert('erreur lors de la génération des prédictions.');
            window.location='demandeDeVoyance.html';
        }
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

