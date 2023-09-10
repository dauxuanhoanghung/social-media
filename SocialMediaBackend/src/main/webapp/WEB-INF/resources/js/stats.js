let chartInDraw = null;
function draw(data, labels, title, type = "pie", canvasId = "donutChart") {
    if (chartInDraw) {
        chartInDraw.destroy();
    }
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
        chartInDraw = new ApexCharts(donutChartEl, chartConfig);

        chartInDraw.render();
        return chartInDraw;
}
}

function drawColChart(data, label, title, type = 'bar', canvasId = "barChart") {
    var options = {
        series: data,
        chart: {
            type: type,
            height: 350
        },
        plotOptions: {
            bar: {
                horizontal: false,
                columnWidth: '55%',
                endingShape: 'rounded'
            }
        },
        dataLabels: {
            enabled: true,
            formatter: (val) => {
                return val;
            }
        },
        stroke: {
            show: true,
            width: 2,
            colors: ['transparent']
        },
        xaxis: {
            categories: label
        },
        yaxis: {
            title: {
                text: title
            }
        },
        fill: {
            opacity: 1
        },
        tooltip: {
            y: {
                formatter: function (val) {
                    return val;
                }
            }
        }
    };

    var chart = new ApexCharts(document.querySelector(`#${canvasId}`), options);
    chart.render();
}

function drawLineChart(data, label, title, xaxis, type = "line", canvasId = "lineChart") {
    var options = {
        series: data,
        chart: {
            height: 350,
            type: type,
            dropShadow: {
                enabled: true,
                color: '#000',
                top: 18,
                left: 7,
                blur: 10,
                opacity: 0.2
            },
            toolbar: {
                show: false
            }
        },
        colors: ['#77B6EA', '#545454'],
        dataLabels: {
            enabled: true
        },
        stroke: {
            curve: 'smooth'
        },
        title: {
            text: title,
            align: 'left'
        },
        grid: {
            borderColor: '#e7e7e7',
            row: {
                colors: ['#f3f3f3', 'transparent'], // takes an array which will be repeated on columns
                opacity: 0.5
            }
        },
        markers: {
            size: 1
        },
        xaxis: {
            categories: label,
            title: {
                text: xaxis
            }
        },
        yaxis: {
            title: {
                text: title
            }
        },
        legend: {
            position: 'top',
            horizontalAlign: 'right',
            floating: true,
            offsetY: -25,
            offsetX: -5
        }
    };

    var chart = new ApexCharts(document.querySelector(`#${canvasId}`), options);
    chart.render();
    return chart;
}