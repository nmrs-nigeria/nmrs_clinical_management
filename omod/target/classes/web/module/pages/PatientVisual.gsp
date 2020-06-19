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
    border: 1px solid #ccc;
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
            console.log(hashes);
            for(let i = 0; i < hashes.length; i++)
            {
                hash = hashes[i].split('=');
                vars.push(hash[0]);
                vars[hash[0]] = hash[1];
            }
            console.log(vars);
            return vars;
        }


        var patientId = "";
        patientId = getUrlVars()["patientId"];
        globalPatientID = getUrlVars()["patientId"];
    

    </script>

<!-- Tab links -->
<div class="tab">
        
    <button class="tablinks" onclick="openTab(event, 'main_indicators')" id="defaultOpen">Relationship Indicator</button>

    <button class="tablinks" onclick="openTab(event, 'compliance')">Compliance with appointment</button>

</div>

<!-- Tab content -->



<div id="main_indicators" class="tabcontent">
    
       <% ui.decorateWith("appui", "standardEmrPage") %>

    <%= ui.resourceLinks() %>

    <style>

        table, tr, td {
        border: none;
        }

    </style>

  
    
    ${ ui.includeFragment("nmrsclinicals", "linkedIndicator") }

</div>



<div id="Data_Visualization" class="tabcontent">
    ${ ui.includeFragment("nmrsclinicals", "compliance") }

</div>



<script>
    
    jQuery(document).ready(function() {
    jQuery('.js-example-basic-single').select2();
});
</script>




<script>

    // Get the element with id="defaultOpen" and click on it
    document.getElementById("defaultOpen").click();

    function openTab(evt, tabName) {
    // Declare all variables
    var i, tabcontent, tablinks;

    // Get all elements with class="tabcontent" and hide them
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
    tabcontent[i].style.display = "none";
    }

    // Get all elements with class="tablinks" and remove the class "active"
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
    tablinks[i].className = tablinks[i].className.replace(" active", "");
    }

    // Show the current tab, and add an "active" class to the button that opened the tab
    document.getElementById(tabName).style.display = "block";
    evt.currentTarget.className += " active";
    }

</script>

<script>
//set patient uuid
jQuery('#index_patient_id').val(globalPatientID);

</script>