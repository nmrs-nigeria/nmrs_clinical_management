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
<button id="plain">Plain</button>
<button id="inverted">Inverted</button>
<button id="polar">Polar</button>
<br>
<br>

<div id="container-3d-pie" style="height: 400px"></div>
<br>
<br>
<div id="container-line" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
<br>
<br>
<div id="container-grouped" style="min-width: 310px; height: 400px; margin: 0 auto"></div>

<script type="text/javascript">


</script>
