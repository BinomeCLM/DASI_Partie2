/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    recupererListeMediums();
    //desactiverLien();
});  

function recupererListeMediums() {
    $.ajax({
        url:'./ActionServlet',
        type:'POST',
        data: {
            action:'RecupererInfoClient'
        },
        dataType:'json'
    })
    .done(function(data){
        alert('remplirchamp');
        remplirChamp(data);
    });
    $.ajax({
        url:'./ActionServlet',
        type:'POST',
        data: {
            action:'RecupererListeMediums'
        },
        dataType:'json'
    })
    .done(function(data){
        alert('ajouterListeMediums');
        var mediums = data.mediums;
        for (var i = 0; i < mediums.length; i++){
            ajouterListeMediums(i, mediums[i]);
        }
        activerBoutons(); // On place la fonction ici pour être sur 
        // que le boutons seront activés une fois qu'ils seront tous chargés
        // car comme c'est un appel asynchrone, si on le met ailleurs, on ne garantit
        // pas que tous les boutons seront cliquables
    });
    
};

function remplirChamp(data) {
    $('#possibilite').prepend(data.prenom + ' ' + data.nom);
    $('#prenom').html(data.prenom)
}

function ajouterListeMediums(compteur, data) {
    // Le compteur permet de donner un id unique à chaque bouton 
    // pour ensuite savoir lequel a été cliqué
    // TODO : Modifier et ne pas faire la mise en forme
    // grace au tableau car c'est interdit en html 5 mtn !!!!
    // C'était just epour tester et voir si ça marcher pour l'instant
    // Idée : mettre de balises div avec attributs ou classe si besoin
    // et dans le fichier css faire l'alignement grâce à des display ...
    $('#voyants').append('<div id="prenomETmetier">'
            +       '<div id="nom">' + data.nom + '</div>'
            +       '<div id="metier">' + data.metier + '</div>'
            +   '</div>'
            +   '<div id="description">' + data.bio + '</div>');
    if (data.metier === 'Tarologue'){
        $('div').last().after('<div id="Objet">Cartes : ' + data.cartes + '</div>'
            +'<div><button type="button" id="' + data.id + '">Demander voyance</button></div>');
    } 
    else if (data.metier === 'Astrologue') {
        $('div').last().after('<div id="Objet">Ecole' + data.ecole + '</br>Promotion : ' + data.promotion + '</div>'
            +'<div><button type="button" id="' + data.id + '">Demander voyance</button></div>');
    }
    else {
        $('div').last().after('<div id="Objet">Support : ' + data.support + '</div>'
           
            +'<div><button type="button" id="' + data.id + '">Demander voyance</button></div>');
    } 
}

function activerBoutons() {
    alert('activerboutton');
    $('button').on('click', function() {
        var id = $(this).attr('id');
        demanderVoyance(id);
    });
}

function demanderVoyance(identifiant) {
    $('#traitementConnexion').html('Connexion en cours ...');
    $.ajax({
        url: './ActionServlet',
        type: 'POST',
        data: {
            action: 'DemanderVoyance',
            id: identifiant
        },
        dataType: 'json'
    })
    .done(function (data) {
        alert(data);
        if(data="true"){
            window.location="attenteMedium.html";            
        }else{
            alert("Voyance n'a pas pu être demandé..");
        } 
    });
}