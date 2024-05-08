# API REST de Control de Clientes

Este proyecto consiste en una API REST para el control de clientes, diseñada para proporcionar un conjunto de servicios web que permiten la gestión eficiente de clientes registrados. Utiliza Spring Boot en el backend y JavaScript para el frontend.

## Características

- **Endpoints RESTful**: La API expone endpoints RESTful que permiten realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre los datos de clientes. Estos endpoints son accesibles a través de solicitudes HTTP estándar y devuelven respuestas en formato JSON.

- **Autenticación y Autorización**: Implementa un sistema de autenticación basado en tokens JWT (JSON Web Tokens) para proteger los endpoints de la API. Los usuarios deben autenticarse para obtener un token JWT, que luego se incluye en las solicitudes para autorizar el acceso a recursos protegidos.

- **Operaciones de Cliente**: Ofrece endpoints para registrar nuevos clientes, recuperar información de clientes existentes, modificar datos de clientes y eliminar clientes de la base de datos. Estas operaciones se realizan de acuerdo con los principios RESTful y siguen convenciones de nomenclatura y estructura de URL coherentes.

- **Seguridad y Validación**: Se implementan medidas de seguridad para proteger la integridad y confidencialidad de los datos de clientes, incluido el almacenamiento seguro de contraseñas mediante algoritmos de hash como Argon2. Además, se realiza validación de datos en las solicitudes entrantes para garantizar la consistencia y la seguridad de los datos.

