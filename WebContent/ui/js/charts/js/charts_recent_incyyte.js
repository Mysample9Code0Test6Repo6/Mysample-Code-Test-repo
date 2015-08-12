$(function () {
	var chart;

	$(document).ready(function () {

		// Build the chart
		chart = new Highcharts.Chart({
			chart: {
				renderTo: 'containersmall0',
				plotBackgroundColor: null,
				plotBorderWidth: null,
				plotShadow: false
			},
			title: {
				text: ''
			},
			tooltip: {
				pointFormat: '{series.name}: <b>{point.percentage}%</b>',
				percentageDecimals: 1,

			},			
			plotOptions: {
				pie: {
					allowPointSelect: false,
					//center: [100, 100],
					size: 60,
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

		// Build the chart
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
				percentageDecimals: 1,

			},

			plotOptions: {
				pie: {
					allowPointSelect: false,
					//center: [100, 100],
					size: 60,
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
					size: 60,
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
					size: 60,
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
				renderTo: 'containersmall4',
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
					size: 60,
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
				renderTo: 'containersmall5',
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
					size: 60,
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
				renderTo: 'containersmall6',
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
					size: 60,
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
				renderTo: 'containersmall7',
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
					size: 60,
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
				renderTo: 'containersmall8',
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
					size: 60,
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
				renderTo: 'containersmall9',
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
					size: 60,
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
				renderTo: 'containersmall10',
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
					size: 60,
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

		// Build the chart
		chart = new Highcharts.Chart({
			chart: {
				renderTo: 'containersmall11',
				plotBackgroundColor: null,
				plotBorderWidth: null,
				plotShadow: false
			},
			title: {
				text: ''
			},
			tooltip: {
				pointFormat: '{series.name}: <b>{point.percentage}%</b>',
				percentageDecimals: 1,

			},

			plotOptions: {
				pie: {
					allowPointSelect: false,
					//center: [100, 100],
					size: 60,
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
				renderTo: 'containersmall12',
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
					size: 60,
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
				renderTo: 'containersmall13',
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
					size: 60,
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
				renderTo: 'containersmall14',
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
					size: 60,
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
				renderTo: 'containersmall15',
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
					size: 60,
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
				renderTo: 'containersmall16',
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
					size: 60,
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
				renderTo: 'containersmall17',
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
					size: 60,
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
				renderTo: 'containersmall18',
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
					size: 60,
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
				renderTo: 'containersmall19',
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
					size: 60,
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
				renderTo: 'containersmall20',
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
					size: 60,
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
				renderTo: 'container0',
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
				       ['Yes',    40],
				       ['No',     40],
				       ['May be',   20]
				       ]
			}]
		});

		chart = new Highcharts.Chart({
			chart: {
				renderTo: 'container4',
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
				       ['Yes',    40],
				       ['No',     40],
				       ['May be',   20]
				       ]
			}]
		});

		chart = new Highcharts.Chart({
			chart: {
				renderTo: 'container5',
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
				       ['Yes',    40],
				       ['No',     40],
				       ['May be',   20]
				       ]
			}]
		});

		chart = new Highcharts.Chart({
			chart: {
				renderTo: 'container6',
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
				       ['Yes',    40],
				       ['No',     40],
				       ['May be',   20]
				       ]
			}]
		});

		chart = new Highcharts.Chart({
			chart: {
				renderTo: 'container7',
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
				       ['Yes',    40],
				       ['No',     40],
				       ['May be',   20]
				       ]
			}]
		});

		chart = new Highcharts.Chart({
			chart: {
				renderTo: 'container8',
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
				       ['Yes',    40],
				       ['No',     40],
				       ['May be',   20]
				       ]
			}]
		});

		chart = new Highcharts.Chart({
			chart: {
				renderTo: 'container9',
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
				       ['Yes',    40],
				       ['No',     40],
				       ['May be',   20]
				       ]
			}]
		});

		chart = new Highcharts.Chart({
			chart: {
				renderTo: 'container10',
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
				       ['Yes',    40],
				       ['No',     40],
				       ['May be',   20]
				       ]
			}]
		});

		chart = new Highcharts.Chart({
			chart: {
				renderTo: 'container11',
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
				       ['Yes',    40],
				       ['No',     40],
				       ['May be',   20]
				       ]
			}]
		});

		chart = new Highcharts.Chart({
			chart: {
				renderTo: 'container12',
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
				       ['Yes',    40],
				       ['No',     40],
				       ['May be',   20]
				       ]
			}]
		});

		chart = new Highcharts.Chart({
			chart: {
				renderTo: 'container13',
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
				       ['Yes',    40],
				       ['No',     40],
				       ['May be',   20]
				       ]
			}]
		});

		chart = new Highcharts.Chart({
			chart: {
				renderTo: 'container14',
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
				       ['Yes',    40],
				       ['No',     40],
				       ['May be',   20]
				       ]
			}]
		});

		chart = new Highcharts.Chart({
			chart: {
				renderTo: 'container15',
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
				       ['Yes',    40],
				       ['No',     40],
				       ['May be',   20]
				       ]
			}]
		});

		chart = new Highcharts.Chart({
			chart: {
				renderTo: 'container16',
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
				       ['Yes',    40],
				       ['No',     40],
				       ['May be',   20]
				       ]
			}]
		});

		chart = new Highcharts.Chart({
			chart: {
				renderTo: 'container17',
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
				       ['Yes',    40],
				       ['No',     40],
				       ['May be',   20]
				       ]
			}]
		});

		chart = new Highcharts.Chart({
			chart: {
				renderTo: 'container18',
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
				       ['Yes',    40],
				       ['No',     40],
				       ['May be',   20]
				       ]
			}]
		});

		chart = new Highcharts.Chart({
			chart: {
				renderTo: 'container19',
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
				       ['Yes',    40],
				       ['No',     40],
				       ['May be',   20]
				       ]
			}]
		});

		chart = new Highcharts.Chart({
			chart: {
				renderTo: 'container20',
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
				       ['Yes',    40],
				       ['No',     40],
				       ['May be',   20]
				       ]
			}]
		});



	});

});

