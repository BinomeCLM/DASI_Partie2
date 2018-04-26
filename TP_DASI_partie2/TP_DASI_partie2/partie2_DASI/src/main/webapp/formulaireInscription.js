/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(function () {
    $('#valider').click(function() {
            $.ajax({
            url:'./ActionServlet',
            type:'Post',
            data: {
                action:'ConfirmationInscription',
                id: window.location.search.substring(1)
            },
            dataType:'json'
        })
        .done(function(){
            document.location.href="./confEnvoieMail";
        });
    });
});