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
    $('#voyants').append('<tr id="prenomETmetier">'
            +   '<td id="nom">' + data.nom + '</td>'
            +   '<td id="metier">' + data.metier + '</td>'
            +'</tr>'
            +'<tr>'
            +    '<td colspan="2" id="description">' + data.bio + '</td>'
            +'</tr>');
    if (data.metier === 'Tarologue'){
        $('tr').last().after('<tr>'
            +    '<td colspan="2" id="Objet">Cartes : ' + data.cartes + '</td>'
            +'</tr>'
            +'<tr>'
            +    '<td><button type="button" id="' + data.id + '">Demander voyance</button></td>'
            +'</tr>');
    } 
    else if (data.metier === 'Astrologue') {
        $('tr').last().after('<tr>'
            +    '<td colspan="2" id="Objet">Ecole' + data.ecole + '</br>Promotion : ' + data.promotion + '</td>'
            +'</tr>'
            +'<tr>'
            +    '<td><button type="button" id="' + data.id + '">Demander voyance</button></td>'
            +'</tr>');
    }
    else {
        $('tr').last().after('<tr>'
            +    '<td colspan="2" id="Objet">Support : ' + data.support + '</td>'
            +'</tr>'
            +'<tr>'
            +    '<td><button type="button" id="' + data.id + '">Demander voyance</button></td>'
            +'</tr>');
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
        window.location="attenteMedium.html";
    });
}