# APLICACIÓN DISTRIBUIDA SEGURA EN TODOS SUS FRENTES

Para este taller se Desarrolló una aplicación web segura con los siguientes requisitos:

Debe permitir un acceso seguro desde el navegador a la aplicación. Es decir debe garantizar autenticación, autorización e integridad de los usuarios.
Debe tener al menos dos computadores comunicándose entre ellos y el acceso de servicios remotos debe garantizar: autenticación, autorización e integridad entre los servicios. Nadie puede invocar los servicios si no está autorizado.
Explique como escalaría su arquitectura de seguridad para incorporar nuevos servicios.

### Prerrequisitos

- Java
- Maven
- Git
- Docker
- AWS
- 
### Instalación

Para hacer uso del proyecto clone el repositorio usando el siguiente comando

```
git clone https://github.com/lgar000/tallerAppSegura.git
```

Ubiquese en la carpeta en la cual clono el repositorio. A continuación
acceda a la carpeta principal del proyecto mediante el siguiente comando

```
cd tallerAppSegura
```

Para compilar y empaquetar, ejecute

```
mvn clean install
```

## Diseño

Teniendo en cuenta los prerrequisitos, los cuales nos indican que se debe tener un acceso seguro desde el navegador, garantizando autenticación y autorización. Se creó la clase HelloSpark1, en la cual se manejan las solicitudes de inicio de sesión, cierre de sesión y en está misma clase se protegen las rutas get, cuyos endpoinds son “local” y “remote”. Esto quiere decir que mediante before((req, res)de spark  se define un filtro que se ejecuta antes de que se puedan manejar otras solicitudes. Mediante este evitamos que el usuario pueda acceder a las rutas get sin antes haber iniciado sesión. Si el usuario trata de  acceder a las rutas get sin iniciar sesión lo redirige a la página de login. Dentro de este before, definimos una lista (protectedRoutes, que contiene la dista de endpoinds protegidos.

Como apoyo a la funcionalidad de HelloSpark1, tenemos la clase User que gestiona la autenticación del usuario,  aquí tenemos un HashMap en el que agregamos usuarios de prueba para validar la autenticación. Además contamos con la clase EncryptString
, mediante la cual encriptamos la contraseña del usuario, para así almacenarla de forma segura.

Para garantizar la existencia de dos computadores comunicándose entre ellos, se define la clase HelloSpark2,  donde configuramos las rutas “local” y “remote”, donde la ruta local devuelve el saludo “Hello Spark 2” y la ruta “remote” realiza una solicitud segura a una URL remota y devuelve la respuesta, esta solicitud a la URl remota es la que correspondiente a la de HelloSpark1. Este mismo funcionamiento también aplica para HelloSpark1, donde la ruta local devuelve el saludo “Hello Spark 1” y la ruta “remote” realiza una solicitud segura a una URL remota y devuelve la respuesta, esta solicitud a la URl remota es la que correspondiente a la de HelloSpark2. Además en ambas clases se configura la seguridad mediante un archivo de almacén de claves y una contraseña.

## Pruebas y funcionamiento



## Despliegue en AWS

Para verificar el despliegue del taller en en AWS usando EC2 y Docke, puede revisar el siguiente video:



## Construido Con

* [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) - Lenguaje de programación y desarrollo
* [Html](https://developer.mozilla.org/es/docs/Web/HTML) - Lenguaje de marcado para la elaboración de páginas web
* [JavaScript](https://developer.mozilla.org/es/docs/Web/CSS) -JavaScript es un lenguaje de programación interpretado
* [Maven](https://maven.apache.org/) - Gestión de dependencias
* [Intellij](https://www.jetbrains.com/es-es/idea/) - Entorno de desarrollo integrado para el desarrollo de programas informáticos
* [Git](https://rometools.github.io/rome/) - Sistema de control de versiones distribuido
* [Docker](https://www.docker.com/) - Docker es una plataforma de código abierto diseñada para facilitar la creación, implementación y ejecución de aplicaciones en contenedores

## Autor

* **Laura García** - [lgar000](https://github.com/lgar000)

