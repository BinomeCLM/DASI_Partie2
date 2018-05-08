/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    // ajout d'un "handler" sur le clic du bouton de Confirmer Inscription
    afficherPrediction();
});

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

function rempirChampPrediction(data) {
    $('infoEmp').html(data.nomEploye);
    $('client').html('Prédiction pour ' + data.prenomClient + ' ' + data.nomClient + ' #' + data.idClient);
    $('amourResultat').html('Amour : (' + data.amourVal + '/5):');
    $('amourString').html(data.amourStr);
    $('santeResultat').html('Amour : (' + data.santeVal + '/5):');
    $('santeString').html(data.santeStr);
    $('travailResultat').html('Amour : (' + data.travailVal + '/5):');
    $('travailString').html(data.travailStr);
}