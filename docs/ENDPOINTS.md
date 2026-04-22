# Endpoints (ProyectCAE)

## Servidor
- Puerto: `8080`
- Context path: `/gestor`
- Base URL: `http://localhost:8080/gestor`

## Documentos
Base: `/api/documents`
- `GET /api/documents`
- `GET /api/documents/{id}`
- `POST /api/documents`
- `PUT /api/documents/{id}`
- `DELETE /api/documents/{id}`
- `POST /api/documents/upload`
- `GET /api/documents/folder/{folderId}`

## Usuarios
Base: `/api/users`
- `GET /api/users`
- `GET /api/users/{id}`
- `POST /api/users`
- `PUT /api/users/{id}`
- `DELETE /api/users/{id}`

## Carpetas
Base: `/api/folders`
- `GET /api/folders`
- `GET /api/folders/{id}`
- `GET /api/folders/by-user/{userId}`
- `POST /api/folders`
- `PUT /api/folders/{id}`
- `DELETE /api/folders/{id}`

## Tipos de documento
Base: `/api/document-types`
- `GET /api/document-types`
- `GET /api/document-types/{id}`
- `GET /api/document-types/global`
- `POST /api/document-types`
- `PUT /api/document-types/{id}`
- `DELETE /api/document-types/{id}`

