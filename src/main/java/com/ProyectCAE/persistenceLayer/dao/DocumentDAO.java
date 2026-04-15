
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * DAO para operaciones de persistencia de documentos
 */
@Repository
@RequiredArgsConstructor
public class DocumentDAO {

    //private final DocumentRepository documentRepository;
    //private final DocumentMapper documentMapper;

    /**
     * Crear un nuevo documento
     *
     * FLUJO:
     * 1. CreateDTO → Entity (Mapper convierte sellerId → SellerEntity)
     * 2. Guardar Entity en BD
     * 3. Entity guardada → DTO (con información del vendedor)
     *
     * NOTA: El Mapper maneja la conversión sellerId → SellerEntity automáticamente
     */
    public DocumentDTO save(DocumentCreateDTO createDTO) {
        DocumentEntity entity = documentMapper.toEntity(createDTO);
        DocumentEntity savedEntity = documentRepository.save(entity);
        return documentMapper.toDTO(savedEntity);
    }

    public Optional<DocumentDTO> findById(Long id) {
        return documentRepository.findById(id)
                .map(documentMapper::toDTO);
    }

    public List<DocumentDTO> findAll() {
        List<DocumentEntity> entities = documentRepository.findAll();
        return documentMapper.toDTOList(entities);
    }

    public Optional<DocumentDTO> update(Long id, DocumentUpdateDTO updateDTO) {
        return documentRepository.findById(id)
                .map(existingEntity -> {
                    documentMapper.updateEntityFromDTO(updateDTO, existingEntity);
                    DocumentEntity updatedEntity = documentRepository.save(existingEntity);
                    return documentMapper.toDTO(updatedEntity);
                });
    }

    public boolean deleteById(Long id) {
        if (documentRepository.existsById(id)) {
            documentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<DocumentDTO> findBySellerEntityId(Long sellerId) {
        List<DocumentEntity> entities = documentRepository.findBySellerEntityId(sellerId);
        return documentMapper.toDTOList(entities);
    }

    public List<DocumentDTO> findByNameContaining(String name) {
        List<DocumentEntity> entities = documentRepository.findByNameContainingIgnoreCase(name);
        return documentMapper.toDTOList(entities);
    }

    public List<DocumentDTO> findAvailableDocuments() {
        List<DocumentEntity> entities = documentRepository.findByStockGreaterThan(0);
        return documentMapper.toDTOList(entities);
    }

    public List<DocumentDTO> findAllOrderByIdAsc() {
        List<DocumentEntity> entities = documentRepository.findAllByOrderByIdAsc();
        return documentMapper.toDTOList(entities);
    }

    public List<DocumentDTO> findAllOrderByIdDesc() {
        List<DocumentEntity> entities = documentRepository.findAllByOrderByIdDesc();
        return documentMapper.toDTOList(entities);
    }

    public List<DocumentDTO> findLatestDocuments() {
        List<DocumentEntity> entities = documentRepository.findAllByOrderByCreatedAtDesc();
        return documentMapper.toDTOList(entities);
    }


    /**
     * Contar total de documentos
     */
    public long count() {
        return documentRepository.count();
    }

}
