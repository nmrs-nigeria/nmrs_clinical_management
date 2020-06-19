<style>
#container {
    min-width: 320px;
    max-width: 600px;
    margin: 0 auto;
}

</style>
<%= ui.resourceLinks() %>
<% ui.includeJavascript("nmrsclinicals", "highcharts.js") %>
<% ui.includeJavascript("nmrsclinicals", "export-data.js") %>
<% ui.includeJavascript("nmrsclinicals", "exporting.js") %>
<% ui.includeJavascript("nmrsclinicals", "series-label.js") %>

<div id="container"></div>
<br>

<div id="container-3d-pie" style="height: 400px"></div>
<br>
<br>
<div id="container-line" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
<br>
<br>
<div id="container-grouped" style="min-width: 310px; height: 400px; margin: 0 auto"></div>

<script type="text/javascript">
    
        
        var indicatorData = "";
    
           jq.ajax({
            url: "${ ui.actionLink("nmrsclinicals", "clinical", "getLinkedIndicator") }",
            dataType: "json",
            data: {
            'uuid': patientId
            }


            }).success(function(data) {
           // jq('#gen-wait').hide();
            console.log(data);
             indicatorData = jq.parseJSON(data);
             console.log('value for obsdate');
             console.log(indicatorData.obsDate);
               loadChart();
          
            })
            .error(function(xhr, status, err) {
          //  jq('#gen-wait').hide();
            alert('An error occured');

            }); 
    
    function loadChart(){
        Highcharts.chart('container', {
            chart: {
                zoomType: 'xy'
            },
            title: {
                text: 'Patient Weight and Viral-load Combined Chart'
            },
            subtitle: {
                text: ''
            },
            xAxis: [{
                categories: indicatorData.obsDate,
                crosshair: true
            }],
            yAxis: [{ // Primary yAxis
                labels: {
                    format: '{value}kg',
                    style: {
                        color: Highcharts.getOptions().colors[1]
                    }
                },
                title: {
                    text: 'Weight',
                    style: {
                        color: Highcharts.getOptions().colors[1]
                    }
                }
            }, { // Secondary yAxis
                title: {
                    text: 'Viral-load',
                    style: {
                        color: Highcharts.getOptions().colors[0]
                    }
                },
                labels: {
                    format: '{value} copies/mil',
                    style: {
                        color: Highcharts.getOptions().colors[0]
                    }
                },
                opposite: true
            }],
            tooltip: {
                shared: true
            },
            legend: {
                align: 'right',
                verticalAlign: 'top',
                layout: 'vertical',
                x: 0,
                y: 100,
                backgroundColor:
                    Highcharts.defaultOptions.legend.backgroundColor || // theme
                    'rgba(255,255,255,0.25)'
            },
            series: [{
                name: 'Viral-load',
                type: 'spline',
                yAxis: 1,
                data: indicatorData.viralLoads,
                tooltip: {
                    valueSuffix: ' copies/mil'
                }

            }, {
                name: 'Weight',
                type: 'spline',
                data: indicatorData.weights,
                tooltip: {
                    valueSuffix: 'kg'
                }
            },
                {
                    name: 'Pick-Up',
                    type: 'spline',
                    data: indicatorData.pickUps,
                    tooltip: {
                        valueSuffix: ''
                    }
                }

            ]
        });

    }


</script>
