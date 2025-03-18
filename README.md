Schedulize Backend - Gestión de Autenticación JWT
Este es un proyecto backend desarrollado en Java 17 utilizando Spring Boot. 
Está diseñado para la gestión de autenticación y autorización basada en JWT. 
El proyecto incluye la integración con bases de datos relacionales y un conjunto de características avanzadas para garantizar un rendimiento óptimo y seguridad.

Características principales
Gestión de usuarios: Registro, inicio de sesión y manejo de roles.

Seguridad mediante JWT: Generación y validación de tokens para proteger endpoints.

Persistencia: Implementación de una base de datos relacional mediante PostgreSQL.

APIs RESTful: Creación de endpoints seguros y bien documentados para manejar la interacción cliente-servidor.

Pruebas: Configuración de un entorno de pruebas unitarias y de integración utilizando herramientas como spring-boot-starter-test y spring-security-test.

Desarrollo ágil: Uso de Spring DevTools para un desarrollo más rápido y eficiente.

Tecnologías utilizadas
Lenguaje: Java 17

Framework: Spring Boot (versión 3.2.1)

Base de Datos: PostgreSQL

Dependencias principales:

spring-boot-starter-data-jpa (para persistencia)

spring-boot-starter-security (para seguridad)

spring-boot-starter-web (para APIs RESTful)

lombok (para facilitar el desarrollo con POJOs)

jjwt (manejo de JWT)

okhttp3 (cliente HTTP)

Requisitos previos
Asegúrate de tener instalados los siguientes elementos antes de comenzar:

Java 17 (JDK)

Maven (gestión de dependencias)

PostgreSQL (base de datos)

Instrucciones de instalación
Clona el repositorio:

bash
git clone https://github.com/tu-usuario/schedulize-backend.git
Ve al directorio del proyecto:

bash
cd schedulize-backend

Configura las credenciales de tu base de datos en el archivo application.properties:
properties
spring.datasource.url=jdbc:postgresql://localhost:5432/tu_base_de_datos
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña

Compila y ejecuta la aplicación:
bash
mvn spring-boot:run

Uso
Documentación del API: La documentación de los endpoints está disponible mediante Swagger/OpenAPI en la ruta /swagger-ui.html una vez que el servidor esté en ejecución.

Pruebas: Ejecuta las pruebas con el siguiente comando:

bash
mvn test
Contribuciones
¡Son bienvenidas las contribuciones! Por favor, abre un issue o envía un pull request con tus sugerencias.

Licencia
Este proyecto está licenciado bajo la MIT License.
