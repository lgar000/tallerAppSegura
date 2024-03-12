function loadGetMsg() {
                let nameVar = document.getElementById("idName").value;
                let passVar =  document.getElementById("idPassword").value;
                const xhttp = new XMLHttpRequest();
                xhttp.onload = function() {
                    document.getElementById("getrespmsg").innerHTML =
                    this.responseText;
                }
                xhttp.open("POST", "/hello?name="+nameVar);
                xhttp.send();
  }