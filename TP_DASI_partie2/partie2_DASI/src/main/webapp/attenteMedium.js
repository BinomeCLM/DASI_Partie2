/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    recupererInfoClient();
}); 

function recupererInfoClient() {
    $.ajax({
        url:'./ActionServlet',
        type:'POST',
        data: {
            action:'RecupererInfoClient'
        },
        dataType:'json'
    })
    .done(function(data){
        alert(data);
        if (data.id >= 0){
            remplirChamp(data);
        }// Faire le else si jamais le client n'avait pas d'information ( ne devrait pas être possible normalement)
    });
};

function remplirChamp(data) {
    $('.possibilite').prepend(data.prenom + " " + data.nom + " ");
}