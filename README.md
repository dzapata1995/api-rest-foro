# Proyecto de Foro con Spring Boot

Este proyecto implementa una API RESTful para un foro utilizando **Spring Boot**, donde los usuarios pueden crear, ver, actualizar y eliminar tópicos y respuestas. Además, se implementa seguridad basada en JWT para la autenticación y autorización de usuarios, y roles para controlar el acceso.

## Tecnologías

- **Spring Boot**: Framework principal.
- **Spring Security**: Para la autenticación y autorización.
- **JPA/Hibernate**: Para la persistencia de datos.
- **JWT**: Para la autenticación segura.
- **BCryptPasswordEncoder**: Para la encriptación de contraseñas.
- **Lombok**: Para reducir el boilerplate code.

## Funcionalidades

### 1. **Autenticación y Autorización**
- Los usuarios pueden autenticarse utilizando JWT.
- Los usuarios autenticados pueden tener diferentes roles:
    - **ADMIN**: Acceso completo, incluyendo la creación de usuarios y gestión de tópicos.
    - **USER**: Puede crear y ver tópicos y respuestas.

### 2. **Usuarios**
- **Crear Usuario**: Un usuario con rol `ADMIN` puede crear un nuevo usuario.
- **Actualizar Usuario**: Los usuarios pueden ser actualizados por un `ADMIN`.
- **Eliminar Usuario**: Solo un `ADMIN` puede eliminar usuarios.
- **Ver Usuarios**: Los usuarios con rol `ADMIN` pueden listar todos los usuarios.

### 3. **Tópicos**
- **Crear Tópico**: Los usuarios autenticados pueden crear tópicos.
- **Ver Tópicos**: Los usuarios pueden listar todos los tópicos y ver los detalles de un tópico específico.
- **Actualizar Tópico**: Solo los usuarios autenticados pueden actualizar un tópico.
- **Eliminar Tópico**: Los tópicos pueden ser eliminados solo por un `ADMIN`.

### 4. **Respuestas**
- **Crear Respuesta**: Los usuarios autenticados pueden agregar respuestas a los tópicos.
- **Ver Respuestas**: Los usuarios pueden listar todas las respuestas de un tópico.
- **Actualizar Respuesta**: Los usuarios pueden actualizar sus propias respuestas.
- **Eliminar Respuesta**: Las respuestas pueden ser eliminadas solo por los administradores.
- **Solución**: Solo puede haber una respuesta marcada como solución por tópico.

## Endpoints de la API

### **Usuarios**

- **POST** `/api/usuarios`: Crear un nuevo usuario (solo `ADMIN`).
- **GET** `/api/usuarios/{id}`: Obtener un usuario por su ID (solo `ADMIN`).
- **PUT** `/api/usuarios/{id}`: Actualizar un usuario por su ID (solo `ADMIN`).

### **Tópicos**

- **POST** `/api/topicos`: Crear un nuevo tópico.
- **GET** `/api/topicos`: Obtener todos los tópicos.
- **GET** `/api/topicos/{id}`: Obtener un tópico específico por su ID.
- **PUT** `/api/topicos/{id}`: Actualizar un tópico por su ID.
- **DELETE** `/api/topicos/{id}`: Eliminar un tópico por su ID.

### **Respuestas**

- **POST** `/api/topicos/{topicoId}/respuestas`: Crear una respuesta para un tópico.
- **GET** `/api/topicos/{topicoId}/respuestas`: Obtener todas las respuestas de un tópico.
- **PUT** `/api/topicos/{topicoId}/respuestas/{respuestaId}`: Actualizar una respuesta por su ID.
- **DELETE** `/api/topicos/{topicoId}/respuestas/{respuestaId}`: Eliminar una respuesta por su ID.

## Seguridad

La API utiliza JWT para autenticar a los usuarios. Los pasos básicos para obtener un token son los siguientes:

1. Hacer una solicitud `POST` a `/api/auth/login` con las credenciales del usuario (`email` y `password`).
2. El servidor devuelve un token JWT si las credenciales son válidas.
3. El token debe ser incluido en el encabezado `Authorization` de todas las solicitudes protegidas como `Bearer {token}`.

## Estructura de Carpetas
```
src
├── main
│ ├── java
│ │ └── com
│ │ │  └── foro
│ │ │ ├── controller
│ │ │ ├── dto
│ │ │ ├── model
│ │ │ ├── repository
│ │ │ ├── security
│ │ │ ├── service
│ └── resources
│ │ ├── application.properties
│ │ └── data.sql
```