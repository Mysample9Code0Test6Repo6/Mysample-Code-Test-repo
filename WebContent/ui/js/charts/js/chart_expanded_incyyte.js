  
$(document).ready(function () {
 	var chart;	
    	// Build the chart
	chart = new Highcharts.Chart({
		chart: {
			renderTo: 'containersexpanded',
			plotBackgroundColor: null,
			plotBorderWidth: null,
			plotShadow: false
		},
		title: {
			text: ''
		},
		legend: {
			layout: 'vertical',
			align: 'left',
			verticalAlign: 'middle',
			// y: 50,
			padding:5,
			itemMarginTop: 5,
			itemMarginBottom: 5,
			itemStyle: {
				lineHeight: '14px'
			}
		},
		tooltip: {
			pointFormat: '{series.name}: <b>{point.percentage}%</b>',
			percentageDecimals: 1,

		},

		plotOptions: {
			pie: {
				allowPointSelect: false,
				//center: [100, 100],
				size: 250,
				cursor: 'pointer',
				dataLabels: {
					enabled: false
				},
				showInLegend: true
			}
		},
		colors:['#1b303f','#6ecafc','#a8dffd','#e2f4fe','#cfff00','#e2ff66','#f5ffcc','#c2002d','#da6681','#f3ccd5'],

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
			    	['Here is an answer that uses the maximum characters',    8.33],
					['Here is an answer that uses the maximum characters',    9],
					['Here is an answer that uses the maximum characters',    10],
					['Here is an answer that uses the maximum characters',    7],
					['Here is an answer that uses the maximum characters',    11],
					['Here is an answer that uses the maximum characters',    5],
					['Here is an answer that uses the maximum characters',    15],
					['Here is an answer that uses the maximum characters',    7],
					['Here is an answer that uses the maximum characters',    10],
					['Here is an answer that uses the maximum characters',    20],

			]
		}]
	});

});

