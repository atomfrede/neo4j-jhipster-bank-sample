package io.github.jhipster.sample.service;

import io.github.jhipster.sample.domain.Label;
import io.github.jhipster.sample.repository.LabelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Label}.
 */
@Service
public class LabelService {

    private final Logger log = LoggerFactory.getLogger(LabelService.class);

    private final LabelRepository labelRepository;

    public LabelService(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }

    /**
     * Save a label.
     *
     * @param label the entity to save.
     * @return the persisted entity.
     */
    public Label save(Label label) {
        log.debug("Request to save Label : {}", label);
        return labelRepository.save(label);
    }

    /**
     * Get all the labels.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<Label> findAll(Pageable pageable) {
        log.debug("Request to get all Labels");
        return labelRepository.findAll(pageable);
    }

    /**
     * Get one label by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<Label> findOne(String id) {
        log.debug("Request to get Label : {}", id);
        return labelRepository.findById(id);
    }

    /**
     * Delete the label by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete Label : {}", id);
        labelRepository.deleteById(id);
    }
}
