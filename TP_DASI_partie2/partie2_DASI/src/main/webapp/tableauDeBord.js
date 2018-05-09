/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    obtenirListeEmploye();
    obtenirListeMedium();
});

function obtenirListeEmploye() {
    $.ajax({
        url:'./ActionServlet', 
        type:'POST',
        data: {
            action:'ObtenirListeEmploye'
        },
        dataType:'json'
    })
    .done(function(data){
        
    });
};

function obtenirListeMedium() {
    $.ajax({
        url:'./ActionServlet', 
        type:'POST',
        data: {
            action:'ObtenirListeMedium'
        },
        dataType:'json'
    })
    .done(function(data){
        
    });
};