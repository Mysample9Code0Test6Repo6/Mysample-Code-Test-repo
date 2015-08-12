$(function () {
    var chart;
    
    $(document).ready(function () {
    	
    	// Build the chart
        chart = new Highcharts.Chart({
            chart: {
                renderTo: 'container',
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false
            },
            title: {
                text: ''
            },
            tooltip: {
        	    pointFormat: '{series.name}: <b>{point.percentage}%</b>',
            	percentageDecimals: 1
            },
            plotOptions: {
                pie: {
                    allowPointSelect: false,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: false
                    },
                    showInLegend: true
                }
            },
            series: [{
                type: 'pie',
                name: 'Total Responce 1,234',
				colors:'#1b303f,#cfff00,#c2002d',
                data: [
                    //['Firefox',   45.0],
                   // ['IE',       26.8],
                   // {
                     //   name: 'Chrome',
                    //    y: 12.8,
                   //     sliced: false,
                   //     selected: false
                 //   },
                    ['Yes',    yesPercentage],
                    ['No',     noPercentage],
                    ['May be',   mayBePercentage]
                ]
            }]
		});
			chart = new Highcharts.Chart({
			chart: {
                renderTo: 'containersmall1',
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false
            },
            title: {
                text: ''
            },
            tooltip: {
        	    pointFormat: '{series.name}: <b>{point.percentage}%</b>',
            	percentageDecimals: 1
            },
            plotOptions: {
                pie: {
                    allowPointSelect: false,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: false
                    },
                    showInLegend: false
                }
            },
            series: [{
                type: 'pie',
                name: '',
                data: [
                    //['Firefox',   45.0],
                   // ['IE',       26.8],
                   // {
                     //   name: 'Chrome',
                    //    y: 12.8,
                   //     sliced: false,
                   //     selected: false
                 //   },
                    ['Yes',    40],
                    ['No',     40],
                    ['May be',   20]
                ]
            }]
        });
		chart = new Highcharts.Chart({
			chart: {
                renderTo: 'container1',
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false
            },
            title: {
                text: ''
            },
            tooltip: {
        	    pointFormat: '{series.name}: <b>{point.percentage}%</b>',
            	percentageDecimals: 1
            },
            plotOptions: {
                pie: {
                    allowPointSelect: false,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: false
                    },
                    showInLegend: true
                }
            },
            series: [{
                type: 'pie',
                name: 'Total Responce 1,234',
                data: [
                    //['Firefox',   45.0],
                   // ['IE',       26.8],
                   // {
                     //   name: 'Chrome',
                    //    y: 12.8,
                   //     sliced: false,
                   //     selected: false
                 //   },
                    ['Yes',    40],
                    ['No',     40],
                    ['May be',   20]
                ]
            }]
        });


			chart = new Highcharts.Chart({
			chart: {
                renderTo: 'containersmall2',
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false
            },
            title: {
                text: ''
            },
            tooltip: {
        	    pointFormat: '{series.name}: <b>{point.percentage}%</b>',
            	percentageDecimals: 1
            },
            plotOptions: {
                pie: {
                    allowPointSelect: false,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: false
                    },
                    showInLegend: false
                }
            },
            series: [{
                type: 'pie',
                name: 'Total Responce 1,234',
                data: [
                    //['Firefox',   45.0],
                   // ['IE',       26.8],
                   // {
                     //   name: 'Chrome',
                    //    y: 12.8,
                   //     sliced: false,
                   //     selected: false
                 //   },
                    ['Yes',    40],
                    ['No',     40],
                    ['May be',   20]
                ]
            }]
        });
		chart = new Highcharts.Chart({
			chart: {
                renderTo: 'container2',
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false
            },
            title: {
                text: ''
            },
            tooltip: {
        	    pointFormat: '{series.name}: <b>{point.percentage}%</b>',
            	percentageDecimals: 1
            },
            plotOptions: {
                pie: {
                    allowPointSelect: false,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: false
                    },
                    showInLegend: true
                }
            },
            series: [{
                type: 'pie',
                name: 'Total Responce 1,234',
                data: [
                    //['Firefox',   45.0],
                   // ['IE',       26.8],
                   // {
                     //   name: 'Chrome',
                    //    y: 12.8,
                   //     sliced: false,
                   //     selected: false
                 //   },
                    ['Yes',    40],
                    ['No',     40],
                    ['May be',   20]
                ]
            }]
        });


			chart = new Highcharts.Chart({
			chart: {
                renderTo: 'containersmall3',
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false
            },
            title: {
                text: ''
            },
            tooltip: {
        	    pointFormat: '{series.name}: <b>{point.percentage}%</b>',
            	percentageDecimals: 1
            },
            plotOptions: {
                pie: {
                    allowPointSelect: false,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: false
                    },
                    showInLegend: false
                }
            },
            series: [{
                type: 'pie',
                name: '',
                data: [
                    //['Firefox',   45.0],
                   // ['IE',       26.8],
                   // {
                     //   name: 'Chrome',
                    //    y: 12.8,
                   //     sliced: false,
                   //     selected: false
                 //   },
                    ['Yes',    40],
                    ['No',     40],
                    ['May be',   20]
                ]
            }]
        });
		chart = new Highcharts.Chart({
			chart: {
                renderTo: 'container3',
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false
            },
            title: {
                text: ''
            },
            tooltip: {
        	    pointFormat: '{series.name}: <b>{point.percentage}%</b>',
            	percentageDecimals: 1
            },
            plotOptions: {
                pie: {
                    allowPointSelect: false,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: false
                    },
                    showInLegend: true
                }
            },
            series: [{
                type: 'pie',
                name: '',
                data: [
                    //['Firefox',   45.0],
                   // ['IE',       26.8],
                   // {
                     //   name: 'Chrome',
                    //    y: 12.8,
                   //     sliced: false,
                   //     selected: false
                 //   },
                    ['Yes',    40],
                    ['No',     40],
                    ['May be',   20]
                ]
            }]
        });



    });
    
});

