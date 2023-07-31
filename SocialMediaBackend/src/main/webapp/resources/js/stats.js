function drawChart(data, label, title, type = "pie", canvasId = "myCateChart") {
    const ctx = document.getElementById(canvasId);

    let colors = [];
    for (let i = 0; i < data.length; i++)
        colors.push(randomColor());

    new Chart(ctx, {
        type: type,
        data: {
            labels: label,
            datasets: [{
                    label: title,
                    data: data,
                    borderWidth: 1,
                    backgroundColor: colors
                }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
}

function draw(data, labels, title, type = "pie", canvasId = "donutChart") {
    const donutChartEl = document.querySelector(`#${canvasId}`);
    chartConfig = {
        chart: {
            height: 390,
            type: type
        },
        labels: labels,
        series: data,
//        colors: [
//            chartColors.donut.series1,
//            chartColors.donut.series4,
//            chartColors.donut.series3,
//            chartColors.donut.series2
//        ],
        stroke: {
            show: false,
            curve: 'straight'
        },
        dataLabels: {
            enabled: true,
            formatter: function (val, opt) {
                return parseInt(val, 10) + '%';
            }
        },
        legend: {
            show: true,
            position: 'bottom',
            markers: {offsetX: -3},
            itemMargin: {
                vertical: 3,
                horizontal: 10
            },
            labels: {
//                colors: legendColor,
                useSeriesColors: false
            }
        },
        plotOptions: {
            pie: {
                donut: {
                    size: '50%',
                    background: 'transparent',
                    labels: {
                        show: true,
                        name: {
                            fontSize: '2rem',
                            fontFamily: 'Open Sans'
                        },
                        value: {
                            fontSize: '1.2rem',
//                            color: legendColor,
                            fontFamily: 'Open Sans',
                            formatter: function (val) {
                                return parseInt(val, 10) + '%';
                            }
                        },
                        total: {
                            show: true,
                            fontSize: '1.5rem',
                            color: "#000000",
                            label: title
//                            formatter: function (w) {
//                                return '31%';
//                            }
                        }
                    }
                }
            }
        },
        responsive: [
            {
                breakpoint: 992,
                options: {
                    chart: {
                        height: 380
                    },
                    legend: {
                        position: 'bottom',
                        labels: {
//                            colors: legendColor,
                            useSeriesColors: false
                        }
                    }
                }
            },
            {
                breakpoint: 576,
                options: {
                    chart: {
                        height: 320
                    },
                    plotOptions: {
                        pie: {
                            donut: {
                                labels: {
                                    show: true,
                                    name: {
                                        fontSize: '1.5rem'
                                    },
                                    value: {
                                        fontSize: '1rem'
                                    },
                                    total: {
                                        fontSize: '1.5rem'
                                    }
                                }
                            }
                        }
                    },
                    legend: {
                        position: 'bottom',
                        labels: {
//                            colors: legendColor,
                            useSeriesColors: false
                        }
                    }
                }
            },
            {
                breakpoint: 420,
                options: {
                    chart: {
                        height: 280
                    },
                    legend: {
                        show: false
                    }
                }
            },
            {
                breakpoint: 360,
                options: {
                    chart: {
                        height: 250
                    },
                    legend: {
                        show: false
                    }
                }
            }
        ]
    };
    if (typeof donutChartEl !== undefined && donutChartEl !== null) {
        const chart = new ApexCharts(donutChartEl, chartConfig);
        chart.render();
}
}