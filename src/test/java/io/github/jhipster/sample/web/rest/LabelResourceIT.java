package io.github.jhipster.sample.web.rest;

import io.github.jhipster.sample.AbstractNeo4jIT;
import io.github.jhipster.sample.SampleNeo4JNoCacheApp;
import io.github.jhipster.sample.domain.Label;
import io.github.jhipster.sample.repository.LabelRepository;
import io.github.jhipster.sample.service.LabelService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link LabelResource} REST controller.
 */
@SpringBootTest(classes = SampleNeo4JNoCacheApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class LabelResourceIT extends AbstractNeo4jIT {

    private static final String DEFAULT_LABEL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LABEL_NAME = "BBBBBBBBBB";

    @Autowired
    private LabelRepository labelRepository;

    @Autowired
    private LabelService labelService;

    @Autowired
    private MockMvc restLabelMockMvc;

    private Label label;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Label createEntity() {
        Label label = new Label()
            .labelName(DEFAULT_LABEL_NAME);
        return label;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Label createUpdatedEntity() {
        Label label = new Label()
            .labelName(UPDATED_LABEL_NAME);
        return label;
    }

    @BeforeEach
    public void initTest() {
        label = createEntity();
    }

    @Test
    public void getAllLabels() throws Exception {
        // Initialize the database
        labelRepository.save(label);

        // Get all the labelList
        restLabelMockMvc.perform(get("/api/labels?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].labelName").value(hasItem(DEFAULT_LABEL_NAME)));
    }
    
    @Test
    public void getLabel() throws Exception {
        // Initialize the database
        labelRepository.save(label);

        // Get the label
        restLabelMockMvc.perform(get("/api/labels/{id}", label.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.labelName").value(DEFAULT_LABEL_NAME));
    }

    @Test
    public void getNonExistingLabel() throws Exception {
        // Get the label
        restLabelMockMvc.perform(get("/api/labels/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }
}
