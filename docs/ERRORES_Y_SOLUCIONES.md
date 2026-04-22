# Errores detectados y soluciones (ProyectCAE)

## 1) DTOs vacíos
- **Problema:** `DocumentDTO`, `DocumentCreateDTO`, `DocumentUpdateDTO`, `FolderDTO`, `FolderCreateDTO`, `FolderUpdateDTO`, `DocumentTypeDTO`, `DocumentTypeCreateDTO`, `DocumentTypeUpdateDTO` estaban vacíos, pero los `DAO`/`Mapper` los usaban como si tuvieran campos → MapStruct fallaba en compilación.
- **Solución:** se definieron campos mínimos coherentes con las entidades y con los flujos CRUD.

## 2) MapStruct con mapeos incorrectos/duplicados
- **Problema:** en `FolderMapper` y `DocumentMapper` había `@Mapping` duplicados (mismo `target` varias veces) y propiedades inexistentes (`users.*`, `idFolder`, `idUsuario`, `createDate`, etc.).
- **Solución:** se reescribieron los mapeos usando los nombres reales de las entidades (`createdBy.id`, `folder.id`, `documentType.idType`, etc.) y se agregaron métodos `@Named` para mapear IDs a entidades relacionadas.

## 3) Repositories con métodos imposibles (nombres que no existen)
- **Problema:** `UserRepository`, `FolderRepository`, `DocumentRepository`, `DocumentTypeRepository` tenían métodos derivados por nombre que no correspondían a propiedades reales (por ejemplo `findByNameContainingIgnoreCase` cuando la entidad tiene `names`, o `findByOwnerId` cuando la entidad tiene `createdBy`).
- **Solución:** se corrigieron firmas para usar paths válidos (por ejemplo `findByCreatedBy_Id(...)`, `findByFolder_IdAndActive(...)`) y se agregaron `@Query` donde no se puede derivar fácilmente.

## 4) Relación incorrecta en `DocumentTypeEntity`
- **Problema:** `DocumentTypeEntity` tenía un `@ManyToOne` hacia sí misma llamado `documentType` (no correspondía al modelo) y no exponía la relación con `DocumentEntity`.
- **Solución:** se cambió a `@OneToMany(mappedBy = "documentType") List<DocumentEntity> documents` y se completaron timestamps.

## 5) Tipos inconsistentes
- **Problema:** `UserEntity.status` era `String` pero el repositorio/DTOs lo trataban como booleano.
- **Solución:** `status` pasó a `Boolean` para que los filtros y DTOs sean consistentes.

## 6) Controladores CRUD faltantes
- **Problema:** solo existía `DocumentController` y no había endpoints CRUD para `User`, `Folder`, `DocumentType`.
- **Solución:** se agregaron `UserController`, `FolderController`, `DocumentTypeController` y se completaron endpoints CRUD adicionales en `DocumentController`.

## 7) Tests en paquete incorrecto
- **Problema:** el test estaba en `com.example.demo` y no correspondía con el paquete real (`com.ProyectCAE`), además podía intentar levantar perfil `dev` (PostgreSQL remoto).
- **Solución:** se movió a `src/test/java/com/ProyectCAE/ApplicationTests.java` y se forzó `@ActiveProfiles("tester")` para usar H2 en memoria.

## 8) Observación de seguridad (recomendación)
- **Problema:** hay credenciales de BD en texto plano en `src/main/resources/application*.properties`.
- **Recomendación:** mover `spring.datasource.*` a variables de entorno (por ejemplo `${DB_URL}`, `${DB_USER}`, `${DB_PASS}`) y no versionar secretos.

