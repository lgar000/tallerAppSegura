document.getElementById("logoutButton").addEventListener("click", function() {
        // Realizar una solicitud al servidor para cerrar la sesión
        fetch("/logout", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            }
        })
        .then(response => {
            // Verificar si la solicitud fue exitosa
            if (response.ok) {
                // Redirigir al usuario a la página de inicio de sesión
                window.location.href = "/login.html";
            } else {
                // Manejar cualquier error que ocurra durante el cierre de sesión
                console.error("Error al cerrar sesión:", response.statusText);
            }
        })
        .catch(error => {
            console.error("Error al cerrar sesión:", error);
        });
    });
    
