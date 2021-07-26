<style>
    /* Style the tab */
    .tab {
    overflow: hidden;
    border: 1px solid #ccc;
    background-color: #f1f1f1;
    }

    /* Style the buttons that are used to open the tab content */
    .tab button {
    background-color: inherit;
    float: left;
    border: none;
    outline: none;
    cursor: pointer;
    padding: 14px 16px;
    transition: 0.3s;
    }
tablinks
    /* Change background color of buttons on hover */
    .tab button:hover {
    background-color: #ddd;
    }

    /* Create an active/current tablink class */
    .tab button.active {
    background-color: #ccc;
    }

    /* Style the tab content */
    .tabcontent {
    display: none;
    padding: 6px 12px;
    border: 1px solid #cccblock;
    border-top: none;
    animation: fadeEffect 1s; /* Fading effect takes 1 second */
    }

    /*.input-w label, .input-w input {
    float: none; !* if you had floats before? otherwise inline-block will behave differently *!
    display: inline-block;
    vertical-align: middle;
    }*/

    /* Go from zero to full opacity */
    @keyframes fadeEffect {
    from {opacity: 0;}
    to {opacity: 1;}
    }
</style>

<link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.10/css/select2.min.css" rel="stylesheet" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.10/js/select2.min.js"></script>
  <script>

        var globalPatientID = "";
        function getUrlVars()
        {
            let vars = [], hash;
            let hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
            // console.log(hashes);
            for(let i = 0; i < hashes.length; i++)
            {
                hash = hashes[i].split('=');
                vars.push(hash[0]);
                vars[hash[0]] = hash[1];
            }
            // console.log(vars);
            return vars;
        }


        var patientId = "";
        patientId = getUrlVars()["patientId"];
        globalPatientID = getUrlVars()["patientId"];

        // console.log(globalPatientID);
    </script>

<!-- Tab links -->


<!-- Tab content -->


    <div id="main_indicators">

        <% ui.decorateWith("appui", "standardEmrPage") %>

        <%= ui.resourceLinks() %>

        ${ ui.includeFragment("nmrsclinicals", "encountersByType") }
    </div>

<div id="cont">

</div>
<script>
    var formArray = [];
    jQuery(document).ready(function() {
    jQuery('.js-example-basic-single').select2();
        console.log(globalPatientID);
        jQuery.ajax({
            url: '${ui.actionLink("nmrsclinicals", "encountersByType","getEncounters")}',
            dataType: "json",
            data: {
                'patientId' : globalPatientID
            }

        }).success(function(data) {
            // jqq('#gen-wait').hide();
            var jsonData = JSON.parse(data);
            // console.log(jsonData);
            var got = [];
            var output = '';// '<div class="tab">';
                if(jsonData) {
                    for(var it in jsonData){
                        // console.log(jsonData[it].encounterType);
                        var hold = jsonData[it].encounterType.replace(/[^a-zA-Z0-9]/g,'_');
                        // console.log(hold);
                            if(! got.includes(hold)){
                                output += '<button class="tablinks" onclick="openTab(event, '+hold+')">'+jsonData[it].encounterType+'</button>';
                                got.push(hold)
                            }
                    }
                }

               //output += '</div>';

            var tabs = '';
            if(jsonData) {
                for(var gt in got){
                    tabs += '<div class="tabcontent" id="'+got[gt]+'">';
                    tabs += '<table>';
                    tabs += '<tr>';
                    tabs += '<th>Encounter Type</th>';
                    tabs += '<th>Encounter DateTime</th>';
                    tabs += '</tr>';

                   for(var enc in jsonData) {
                        if (jsonData[enc].encounterType.replace(/[^a-zA-Z0-9]/g,'_') === got[gt]) {
                            formArray[jsonData[enc].encounterId] = jsonData[enc].formUUID;
                            tabs += '<tr>';
                            tabs += '<td>'+jsonData[enc].formName+'</td>';
                            tabs += '<td>'+new Date(jsonData[enc].encounterDateTime).toISOString()+'</td>';
                            tabs += '<td><button onclick="editForm('+ jsonData[enc].encounterId+')">Edit</button></td>';
                            tabs += '</tr>';
                        }
                    }
                    tabs += '</table>';
                    tabs += '</div>';
                }
            }
             document.getElementById("tabmenu").innerHTML = output;
             document.getElementById("cont").innerHTML = tabs;


        })
            .error(function(xhr, status, err) {
                document.getElementById("tabmenu").innerHTML = "";
                document.getElementById("cont").innerHTML = "";
            });
    });

    function editForm(encounterId){
        var formUUID = formArray[encounterId];
       window.location.href = 'http://' +window.location.host + '/'+ OPENMRS_CONTEXT_PATH +
            '/htmlformentryui/htmlform/editHtmlFormWithStandardUi.page?visitId='+globalPatientID+'&formUuid='+formUUID+'&patientId='+
            globalPatientID +'&encounterId='+encounterId+'&returnUrl=%2F' + OPENMRS_CONTEXT_PATH +
            '%2Fcoreapps%2Fclinicianfacing%2Fpatient.page%3FpatientId%3D'+globalPatientID;

        // emr.navigateTo({ applicationUrl: link.goToUrl });
    }
</script>

<script>
//set patient uuid
jQuery('#index_patient_id').val(globalPatientID);

</script>
<script>


    function openTab(evt, id) {
        var i, tabcontent, tablinks;
        tabcontent = document.getElementsByClassName("tabcontent");
        for (i = 0; i < tabcontent.length; i++) {
            tabcontent[i].style.display = "none";
        }
        tablinks = document.getElementsByClassName("tablinks");
        for (i = 0; i < tablinks.length; i++) {
            tablinks[i].className = tablinks[i].className.replace(" active", "");
        }
        document.getElementById(id.id).style.display = "block";
        evt.currentTarget.className += " active";
    }
</script>