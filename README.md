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

Para ejecutar el proyecto abra su IDE y ejecute las clases HelloSpark1 y HelloSpark2 o ubiquese en la carpeta pricipal y ejecute los siguientes comandos, cada uno en una terminal diferente:

```
java -cp "target/classes;target/dependency/*" edu.escuelaing.arem.ASE.app.HelloSpark1
```

```
java -cp "target/classes;target/dependency/*" edu.escuelaing.arem.ASE.app.HelloSpark2
```

## Pruebas y funcionamiento

Una vez tenga descargado el proyecto y lo haya ejecutado correctamente proceda a ingresar a un navegador, donde deberá ingresar la url: https://localhost:5000/login.html, aquí encontrara un formulario que le pedirá que ingrese su usuario y contraseña. Los usuarios válidos son:

- john, 1234
- jane, 5678

Si ingresa un usuario valido y envia el formulario vera un mensaje que le indica que su inicio de sesión es exitoso:

![loginValido](https://github.com/lgar000/tallerAppSegura/blob/main/Imagenes/loginValido.png)

En caso de ingresar credenciales de credenciales invalidas, le pedira que vuelva a intentarlo:

![loginInvalido](https://github.com/lgar000/tallerAppSegura/blob/main/Imagenes/loginInvalido.png)

Tenga en cuenta que si se encuentra en el puerto 5000 y trata de acceder a algunas de las rutas, https://localhost:5000/local o https://localhost:5000/remote, sin antes iniciar sesion, sera redirigido a la página de login. Esto debido a que estas rutas se encuentran protegidas en la clase HelloSpark1.

![redireccion](https://github.com/lgar000/tallerAppSegura/blob/main/Imagenes/intentoDeAccesoLocalSinInicioDeSesi%C3%B3n.png)

Sin embargo una vez ha iniciado sesión con un usuario válido podrá usar las rutas:  https://localhost:5000/local o https://localhost:5000/remote en el navegador y obtener con https://localhost:5000/local, el mensaje Hello Spark 1, este mensaje corresponde al definido en la propia clase:

![spark1local](https://github.com/lgar000/tallerAppSegura/blob/main/Imagenes/spark1local.png)

En cuanto a la petición a la ruta https://localhost:5000/remote, obtendrá el mensaje Hello Spark 2, que corresponde al mensaje remoto obtenido de la clase HelloSpark2:

![spark1local](https://github.com/lgar000/tallerAppSegura/blob/main/Imagenes/spark1remote.png)

Al ingresar la url https://localhost:5001/remote, obtendra el mensaje propio de la clase HelloSpark2:

![spark2local](https://github.com/lgar000/tallerAppSegura/blob/main/Imagenes/spark2local.png)

Adicionalmente si ingresa a la ruta https://localhost:5000/logout.html, encontrara un boton para cerrar sesión. Debido a que si no se cierra la sesión después de que se ha iniciado correctamente, el usuario queda guardado en una sesión de usuario y a pesar de que ingrese un usuario incorrecto en el formulario, la sesión permanecera y le permitira al usuario no valido acceder a las rutas protegidas(https://localhost:5000/local y https://localhost:5000/remote). Así que para evitar este comportamiento al cerrar sesión se invalida la sesión de usuario.

![logOut](https://github.com/lgar000/tallerAppSegura/blob/main/Imagenes/logout.png)

Una vez cierre sesión sera redirigido a la pagina del login:

![redireccionLogout](https://github.com/lgar000/tallerAppSegura/blob/main/Imagenes/redirecci%C3%B3nLogOut.png)

## Pruebas unitarias

Para ejecutar las pruebas unitarias, ejecutelas desde su IDE

![testIDE](https://github.com/lgar000/tallerAppSegura/blob/main/Imagenes/pruebasIDE.png)

O en la terminal, ubiquese en carpeta principal del proyecto y ejecute el comando:

```
mvn test
```

![testMvn](https://github.com/lgar000/tallerAppSegura/blob/main/Imagenes/testMvn.png)

## Diseño

Teniendo en cuenta los prerrequisitos, los cuales nos indican que se debe tener un acceso seguro desde el navegador, garantizando autenticación y autorización. Se creó la clase HelloSpark1, en la cual se manejan las solicitudes de inicio de sesión, cierre de sesión y en está misma clase se protegen las rutas get, cuyos endpoinds son “local” y “remote”. Esto quiere decir que mediante before((req, res)de spark  se define un filtro que se ejecuta antes de que se puedan manejar otras solicitudes. Mediante este evitamos que el usuario pueda acceder a las rutas get sin antes haber iniciado sesión. Si el usuario trata de  acceder a las rutas get sin iniciar sesión lo redirige a la página de login. Dentro de este before, definimos una lista (protectedRoutes, que contiene la dista de endpoinds protegidos.

Como apoyo a la funcionalidad de HelloSpark1, tenemos la clase User que gestiona la autenticación del usuario,  aquí tenemos un HashMap en el que agregamos usuarios de prueba para validar la autenticación. Además contamos con la clase EncryptString
, mediante la cual encriptamos la contraseña del usuario, para así almacenarla de forma segura.

Para garantizar la comunicación entre dos servidores, se define la clase HelloSpark2,  donde configuramos las rutas “local” y “remote”, donde la ruta local devuelve el saludo “Hello Spark 2” y la ruta “remote” realiza una solicitud segura a una URL remota y devuelve la respuesta, esta solicitud a la URl remota es la que correspondiente a la de HelloSpark1. Este mismo funcionamiento también aplica para HelloSpark1, donde la ruta local devuelve el saludo “Hello Spark 1” y la ruta “remote” realiza una solicitud segura a una URL remota y devuelve la respuesta, esta solicitud a la URl remota es la que correspondiente a la de HelloSpark2. Además en ambas clases se configura la seguridad mediante un archivo de almacén de claves y una contraseña. Este par de llaves públicas y privadas y el certificado, se generaron mediante el comando: keytool -genkeypair -alias ecikeypair -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore ecikeystore.p12 -validity 3650. Formando así los archivos ecicert.cer, ecikeystore.p12 y myTrustStore para la clase HelloSpark1 y los archivos ecicert1.cer, ecikeystore2.p12 y TrustStore1.p12 para la clase HelloSpark2. 

Finalmente tenemos la clase SecureURLReader en la cual se configura la conexión SSL para asegurar la comunicación. Está tiene el método secureURL
que toma tres parámetros: la URL a la que se desea conectar, la ruta del archivo que contiene el almacén de confianza y la contraseña para acceder al truststore. Por otro lado se tiene el método readURL(String sitetoread) que lee el contenido de la URL como parámetro y lo retorna en una cadena. 

Diagama de arquitectura:

![diagrama](https://github.com/lgar000/tallerAppSegura/blob/main/Imagenes/diagramaTaller7.png)

## Escalar la  arquitectura de seguridad para nuevos servicios

En caso de que se quiera agregar un nuevo servicio se debe proceder a crear un par de llaves y generar los certificados y modificar la aplicación para que use los certificados. Tal y como se hizo con HelloSpark1 y HelloSpark2, donde cada uno tiene uno tiene su propio par de llaves y sus certificados. Esto se considera una buena práctica ya que permite un mejor control de acceso, y en caso de que se requieran revocar un certificado o uno de estos se vea comprometido solo afectará a este servicio en particular, mientras que los demás servicios permanecerán seguros.

Otra manera de escalar la arquitectura de seguridad para los servicios sería configurar o implementar políticas de seguridad específicas para cada uno, como tiempos de vida del certificado y métodos de autenticación, según las necesidades y requisitos de los servicios.

Algo adicional que se podría hacer es la creación de sesión compartida para algunos servicios que requieran que el usuario esté autenticado o haya iniciado sesión ya que por el momento tenemos las rutas de HelloSpark1 protegidas pero a las rutas de HelloSpark2 se puede acceder sin necesidad de iniciar sesión, esto se podría tener en cuenta para servicios especiales para usuarios. Por otro lado, también se puede tener en cuenta la creación de roles para los usuarios, lo que nos permitirá definir permisos asociados a cada rol. Teniendo así control de las acciones que los usuario pueden realizar o los recursos o funcionalidades a los que pueden acceder.


## Despliegue en AWS

Para verificar el despliegue del taller en en AWS usando EC2, puede revisar el siguiente video:

https://youtu.be/O5yVOkpKN2Y

## Construido Con

* [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) - Lenguaje de programación y desarrollo
* [Html](https://developer.mozilla.org/es/docs/Web/HTML) - Lenguaje de marcado para la elaboración de páginas web
* [JavaScript](https://developer.mozilla.org/es/docs/Web/CSS) -JavaScript es un lenguaje de programación interpretado
* [Maven](https://maven.apache.org/) - Gestión de dependencias
* [Intellij](https://www.jetbrains.com/es-es/idea/) - Entorno de desarrollo integrado para el desarrollo de programas informáticos
* [Git](https://rometools.github.io/rome/) - Sistema de control de versiones distribuido
* [AWS](https://aws.amazon.com/es/free/?gclid=CjwKCAjw7-SvBhB6EiwAwYdCAdFmvp0VJz5wsQZcg5anEFJtzJJ2dfpVsGlht9X5DSyRY3Cz7u0B-RoC6ewQAvD_BwE&trk=8fa18207-f2c2-4587-81a1-f2a3648571b3&sc_channel=ps&ef_id=CjwKCAjw7-SvBhB6EiwAwYdCAdFmvp0VJz5wsQZcg5anEFJtzJJ2dfpVsGlht9X5DSyRY3Cz7u0B-RoC6ewQAvD_BwE:G:s&s_kwcid=AL!4422!3!647999789202!e!!g!!amazon%20web%20services!19685287144!146461596856) - Es una colección de servicios de computación en la nube pública que en conjunto forman una plataforma de computación en la nube, ofrecidas a través de Internet por Amazon.com

## Autor

* **Laura García** - [lgar000](https://github.com/lgar000)

