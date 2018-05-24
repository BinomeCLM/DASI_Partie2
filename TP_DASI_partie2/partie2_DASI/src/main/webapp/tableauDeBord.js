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
    
    $('#tabLien').on('click', function () {
        window.location = "tableauDeBord.html"
    });
    
    $('#accueil').on('click', function () {
        window.location = "index.html"
    });
});

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

function remplirChampEmploye(data) {
    $('.possibilite').prepend(data.nom);
}

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
        if (data.id) {
            remplirChampEmploye(data);
        }
        else {
            alert('Vous allez être redirigé vers la page de connexion. En effet, vous ne vous êtes pas identifié.');
            window.location = "connexion.html";
        }
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