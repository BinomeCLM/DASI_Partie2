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
        activerCss();
        activerBoutons(); // On place la fonction ici pour être sur 
        // que le boutons seront activés une fois qu'ils seront tous chargés
        // car comme c'est un appel asynchrone, si on le met ailleurs, on ne garantit
        // pas que tous les boutons seront cliquables
    });
    
};

function remplirChamp(data) {
    $('.possibilite').prepend(data.prenom + ' ' + data.nom);
    $('#prenom').html(data.prenom);
}

function ajouterListeMediums(compteur, data) {
    // Le compteur permet de donner un id unique à chaque bouton 
    // pour ensuite savoir lequel a été cliqué
    // TODO : Modifier et ne pas faire la mise en forme
    // grace au tableau car c'est interdit en html 5 mtn !!!!
    // C'était just epour tester et voir si ça marcher pour l'instant
    // Idée : mettre de balises div avec attributs ou classe si besoin
    // et dans le fichier css faire l'alignement grâce à des display ...
    $('#voyants').append('<div class="elemBox"><div class="nommetier">'
            +       '<span class="nom">' + data.nom + '</span>'
            +       '<span class="metier">' + data.metier + '</span>'
            +   '</div></div>'
            +   '<div class="elemBox"><div class="description">' + data.bio + '</div></div>');
    if (data.metier === 'Tarologue'){
        $('div').last().after('<div class="elemBox"><div class="cartes">Cartes : ' + data.cartes + '</div></div>'
            +'<div class="elemBox"><button type="button" id="' + data.id + '">Demander voyance</button></div>');
    } 
    else if (data.metier === 'Astrologue') {
        $('div').last().after('<div class="elemBox"><div class="Objet">Ecole' + data.ecole + '</div><div>Promotion : ' + data.promotion + '</div></div>'
            +'<div class="elemBox"><button type="button" id="' + data.id + '">Demander voyance</button></div>');
    }
    else {
        $('div').last().after('<div class="elemBox"><div class="support">Support : ' + data.support + '</div></div>'
           
            +'<div class="elemBox"><button type="button" id="' + data.id + '">Demander voyance</button></div>');
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
        if(data){
            window.location="attenteMedium.html";            
        }else{
            alert("Voyance n'a pas pu être demandé..");
        } 
    });
}

function activerCss() {
    $('#voyants').css('display', 'flex');
    $('#voyants').css('flex-direction', 'column');
    $('#voyants').css('justify-content', 'space-around');
    $('#voyants').css('align-items', 'center');
    $('#voyants').css('align-content', 'space-around');
    //$('#voyant').css('width', '60%');
    //$('#voyants').css('background-color', 'blue');
    $('.elemBox').css('display','flex');
    //$('.elemBox').css('background-color', 'red');
    $('.elemBox').css('flex-direction','column');
    $('.elemBox').css('justify-content','flex-start');
    $('.elemBox').css('align-items','strech');
    $('.elemBox').css('align-content','strech');
    $('.elemBox').css('width','50%');
    $('.elemBox').css('margin-bottom','10px');
    $('.nommetier').css('display','flex');
    $('.nommetier').css('flex-direction','row');
    $('.nommetier').css('justify-content','space-between');
    $('.nommetier').css('background-color','#E6E6FA');
    $('.nommetier').css('padding-left','10px');
    $('.nommetier').css('padding-right','10px');
    $('.nommetier').css('border-radius','5px');
    $('.nommetier').css('border-style','solid');
    $('.nommetier').css('border-color','grey');
    $('.nommetier').css('border-width','2px');
    $('.cartes').css('align-self', 'flex-start');
    $('.support').css('align-self', 'flex-start');
    $('description').css('align-self','flex-start');
    $('button').css('width', '200%');
    $('button').css('align-self', 'stretch');
}