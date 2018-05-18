/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    recupererInfoEmploye();
    obtenirListeEmploye();
    obtenirListeMedium();
    
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

// Pour la navBar
function remplirChampEmploye(data) {
    $('.possibilite').prepend(data.nom);
}

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
        afficherGraphiqueHistoEmploye(data.employes);
        afficherGraphiqueCamembertEmploye(data.employes);
    });
};

function obtenirListeMedium() {
    $.ajax({
        url:'./ActionServlet', 
        type:'POST',
        data: {
            action:'ObtenirListeMediums'
        },
        dataType:'json'
    })
    .done(function(data){
        afficherGraphiqueHistoMedium(data.mediums);
    });
};

function afficherGraphiqueHistoMedium(dataMed) {
    var labelM = [];
    var dataM = [];
    for (i=0; i<dataMed.length; i++){
        labelM.push(dataMed[i].nom);

        dataM.push(dataMed[i].nbPresta);
    };

    var lineChartData = {
        labels: labelM, data:dataM
    };
    buildBarChart('Medium', lineChartData);
};

function afficherGraphiqueHistoEmploye(dataEmp) {
    var labelE = [];
    var dataE = [];
    for (i=0; i<dataEmp.length; i++){
        labelE.push(dataEmp[i].nom);

        dataE.push(dataEmp[i].nbPresta);
    };

    var lineChartData = {
        labels: labelE, data:dataE
    };
    buildBarChart('Employe', lineChartData);
};

function buildBarChart(container, graphData) {

    Highcharts.chart(container, {

        chart: {
            type: 'column'
        },
        title: {
            text: 'Total de voyance demandées par ' +  container
        },
        subtitle: {
            text: ''
        },
        xAxis: {
            categories: graphData.labels
        },
        yAxis: {
            title: {
                text: 'Nombre total de voyance demandé'
            }
        },
        legend: {
            enabled: false
        },
        credits: {
            enabled: false
        },
        series: [{name: 'Données', data: graphData.data}]
    });
}   

function afficherGraphiqueCamembertEmploye(dataEmp){
    var empTotal = [];
    
    var emp = [];
    
    for (i=0; i<dataEmp.length; i++){
        emp = {name: dataEmp[i].nom, y: dataEmp[i].nbPresta};
        empTotal.push(emp);
    };
    var proportionChartData = {
        label: 'Répartition des voyances entre employé',
        data: empTotal
    };
            
    buildPieChart('resNbVoyEntreEmp', proportionChartData);
}

function buildPieChart(container, graphData) {

    Highcharts.chart(container, {

        chart: {
            type: 'pie'
        },
        title: {
            text: 'Répartition des voyances entre employé'
        },
        subtitle: {
            text: ''
        },
        credits: {
            enabled: false
        },
        series: [{name: graphData.label, data: graphData.data}]
    });
}