# Tareas por hacer

## 1. **Configuración básica**
- [x] Configurar el proyecto Spring Boot (si no está preconfigurado).
- [x] Configurar MinIO localmente

## 2. **Crear Endpoints**
- [x] Insertar
- [x] Búsqueda
- [x] generar link de descarga
- [x] Asegurarse de que todas las configuraciones sensibles estén externalizadas (variables de entorno y config files).
- [x] Integrar cliente MinIO.
- [x] Usar Lombok para reducir boilerplate.
- [x] Asegurar manejo apropiado de excepciones.

### Upload Endpoint
- [X] Implementar endpoint para subir documento PDF + metadata (user, nombre, tags).
- [X] Validar tamaño máximo del archivo (50MB/500MB).
- [ ] Subir archivo a MinIO respetando la estructura de buckets.
- [X] Almacenar metadata en PostgreSQL.
- [X] Manejar la subida de archivos en streaming para limitar uso de memoria (<50MB).
- [ ] Validar modelo/DTO (campos no nulos).
- [ ] Capacidad para manejar subidas concurrentes (hasta 10 simultáneas).

### Search Endpoint
- [ ] Implementar endpoint para consultar documentos con filtros (user, nombre, tags) y paginación.
- [ ] Ordenar resultados por created_at descendente.
- [ ] No devolver URLs de descarga.

### Download Endpoint
- [X] Implementar endpoint para obtener un enlace de descarga temporal (pre-signed URL de MinIO) basado en el ID del documento.
- [ ] Asegurarse de que el endpoint no exponga rutas directas de MinIO.

## 4. **Integraciones SQL**
- [ ] Configurar DBMS SQL preferentemente PostgreSQL, preferentemente en docker-compose.
- [ ] Crear un script SQL (`docker/init-scripts/schema-init.sql`) para la base de datos con los campos:
  - user, document_name, tags (relación apropiada para múltiples etiquetas), minio_path, file_size, file_type, created_at y campos adicionales necesarios.
- [ ] Asegurarse de la indexación eficiente (user, document_name, created_at).
- [X] Integrar Spring Data JPA para operaciones de base de datos.

## 5. **Testing**
- [ ] Implementar unit tests (JUnit 5, Mockito, AssertJ).
- [ ] Implementar integration tests clave.
- [ ] Asegurarse de tener cobertura en casos críticos y edge cases.

## 6. **Dockerización**
- [ ] Crear Dockerfile para el servicio document-management-service.
- [ ] Modificar/adaptar docker-compose.yml (bootstrap del stack).

## 7. **Buenas prácticas**
- [X] Seguir las convenciones de commits (Conventional Commits).
- [X] Usar patrones Controller-Service-Repository.
- [X] Aplicar principios SOLID y clean code.
- [ ] Documentar (opcionalmente) los endpoints con OpenAPI.

## 8. **Validación**
- [X] Validar endpoints con Postman.
- [ ] Probar el stack completo con `docker-compose up --build`.

## 9. **Entrega**
- [X] Comentar y explicar cualquier limitación, duda o bloqueo encontrado.
- [X] Incluir notas adicionales si aplica.
- [X] Subir el repo completo a Github y compartir el enlace.

---

### (Opcional)
- [ ] Automatizar tests y jacoco coverage (`./mvnw jacoco:report`).
- [ ] Formatear código con Spotless (`./mvnw spotless:apply`).
- [ ] Implementar documentación OpenAPI desde código.

---

## **Resumen**
El reto exige manejo eficiente de archivos grandes (stream/upload minio), persistencia de metadata, búsquedas flexibles y exposición segura de archivos, todo dentro de restricciones estrictas de memoria/diseño, buenas prácticas de desarrollo, y pruebas automáticas.

