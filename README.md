
## TFM: Integración de seguridad en Spring Boot con OAuth 2.0

El objetivo principal de este proyecto es desarrollar un caso de uso práctico del protocolo de seguridad OAuth 2.0 
en una aplicación construida con el framework Spring. La implementación busca reforzar la seguridad mediante una capa 
adicional de autenticación tanto en el cliente (aplicación frontend) como en el servidor (microservicios que conforman 
el backend). La aplicación seleccionada para esta implementación es una App de gestión de cursos, desarrollada en el 
contexto de la asignatura Frameworks Backend y Microservicios. 

Código base sin OAuth2.0 desarrollado por: "Salvador Otón Tortosa" https://github.com/sot1.

Para más detalles del trabajo realizado consulte la memoria del TFM en:

### Bade de datos MySQL

Se deben crear las bases de datos para cada microservicio. Para ello, ejecutar los archivos presentes en la carpet db,
por defecto la contraseña es "1234567890"

### Proveedor Auth0

1. Crear cuenta en Auth0
2. Registrar Aplicación "clienteCursosSeguro" como una aplicación web regular.
3. Registrar cada microservicio como APIs, con su propio audience ("api-micro-usuarios-matricula" y "api-micro-cursos-alumnos"),
   JWT tipo RFC 9068 y algoritmo RS256.
5. En cada API, crear el permiso o scope de “read”
6. En cada API, dar acceso únicamente a la Aplicación "clienteCursosSeguro" con el permiso de “read”.
7. Personalizar logo, color de fondo y texto de bienvenida de las interfaces o prompts de login y sing up (registro) que proporciona Auth0

### Modificaciones y ejecución de proyectos

Se puede abrir en cualquier IDE, sin embargo en este caso se usa,Eclipse. Cada proyecto se abre dentro de esta herramienta. 
Para la instalación, configuración inicial y otros detalles técnicos, se deben seguir los pasos descritos en la documentación 
oficial de Eclipse. En caso de ser necesarias modificaciones adicionales, es imprescindible instalar el complemento 
“Spring Tools (Spring Tools Suite) versión 4.31.0.RELEASE”. 

1. Abrir Eclipse
2. seleccionar el espacio de trabajo correspondiente
3. Importar cada uno de los proyectos.
4. En los archivos applications.properties, de cada microservicio, editar las variables de Auth0 según el registro de cada api.
5. En el archivo application.properties de la Aplicación "clienteCursosSeguro",editar las variables de Auth0 según el registro de la
   aplicacion en Auth0. Editar las urls de "logoutSuccessUrl" y "response.sendRedirect( ... )" que son las mismas de en el archivo
   WebSecurityConfig.java
7. Limpiar dependencias, instalar de nuevo y ejecutar como proyecto de spring.
   
   ![image](https://github.com/user-attachments/assets/b45f98c7-a2d3-4c56-b360-87f35bf9ae27)

   ![image](https://github.com/user-attachments/assets/6e4ffd77-4a18-4b20-9a8e-1bfd98944ba2)

   ![image](https://github.com/user-attachments/assets/1d7dfa33-c589-4332-9733-7b3c9f90a521)


### Login

en caso de no existir una sesión activa al ingresar por cualquier ruta derivada de “http://localhost:9000/”, el sistema despliegue la interfaz 
de login predeterminada de OAuth 2.0 proporcionada por Auth0 con las personalizaciones realizadas.

![image](https://github.com/user-attachments/assets/deffd2e1-4b54-4a8d-824c-e36a41ed8382)








   




