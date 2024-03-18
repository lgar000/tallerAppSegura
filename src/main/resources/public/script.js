function submitForm(event) {
    event.preventDefault(); // Evita el envío predeterminado del formulario
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    
    console.log("username: " + username + " password: " + password);

    fetch("/login?username=" + encodeURIComponent(username) + "&password=" + encodeURIComponent(password), {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({username: username, password: password})
    })
    .then(response => response.text())
    .then(message => {
        console.log("message: "+ message)
        document.getElementById("postrespmsg").innerHTML = message;
    })
    .catch(error => {
        console.error('There has been a problem with your fetch operation:', error);
    });
}


document.getElementById("submitButton").addEventListener("click", submitForm);

