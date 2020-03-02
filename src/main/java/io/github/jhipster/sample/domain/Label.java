package io.github.jhipster.sample.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.neo4j.springframework.data.core.schema.GeneratedValue;
import org.neo4j.springframework.data.core.schema.Id;
import org.neo4j.springframework.data.core.schema.Node;
import org.neo4j.springframework.data.core.schema.Property;
import org.neo4j.springframework.data.core.support.UUIDStringGenerator;
import org.neo4j.springframework.data.core.schema.Relationship;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

/**
 * A Label.
 */
@Node
public class Label implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    private String id;

    @NotNull
    @Size(min = 3)
    @Property("label_name")
    private String labelName;

    @JsonIgnore
    private Set<Operation> operations = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabelName() {
        return labelName;
    }

    public Label labelName(String labelName) {
        this.labelName = labelName;
        return this;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public Set<Operation> getOperations() {
        return operations;
    }

    public Label operations(Set<Operation> operations) {
        this.operations = operations;
        return this;
    }

    public Label addOperation(Operation operation) {
        this.operations.add(operation);
        operation.getLabels().add(this);
        return this;
    }

    public Label removeOperation(Operation operation) {
        this.operations.remove(operation);
        operation.getLabels().remove(this);
        return this;
    }

    public void setOperations(Set<Operation> operations) {
        this.operations = operations;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Label)) {
            return false;
        }
        return id != null && id.equals(((Label) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Label{" +
            "id=" + getId() +
            ", labelName='" + getLabelName() + "'" +
            "}";
    }
}
