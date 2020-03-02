package io.github.jhipster.sample.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.neo4j.springframework.data.core.schema.GeneratedValue;
import org.neo4j.springframework.data.core.schema.Id;
import org.neo4j.springframework.data.core.schema.Node;
import org.neo4j.springframework.data.core.schema.Property;
import org.neo4j.springframework.data.core.support.UUIDStringGenerator;
import org.neo4j.springframework.data.core.schema.Relationship;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Duration;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import io.github.jhipster.sample.domain.enumeration.BankAccountType;

/**
 * A BankAccount.
 */
@Node
public class BankAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    private String id;

    @NotNull
    @Property("name")
    private String name;

    @Property("guid")
    private UUID guid;

    @Property("bank_number")
    private Integer bankNumber;

    @Property("agency_number")
    private Long agencyNumber;

    @Property("last_operation_duration")
    private Float lastOperationDuration;

    @Property("mean_operation_duration")
    private Double meanOperationDuration;

    @Property("mean_queue_duration")
    private Duration meanQueueDuration;

    @NotNull
    @Property("balance")
    private BigDecimal balance;

    @Property("opening_day")
    private LocalDate openingDay;

    @Property("last_operation_date")
    private Instant lastOperationDate;

    @Property("active")
    private Boolean active;

    @Property("account_type")
    private BankAccountType accountType;

    @Property("attachment")
    private byte[] attachment;

    @Property("attachment_content_type")
    private String attachmentContentType;

    @Property("description")
    private String description;

    @Relationship("user")
    @JsonIgnoreProperties("bankAccounts")
    private User user;

    @Relationship(value = "operations", direction = Relationship.Direction.OUTGOING)
    private Set<Operation> operations = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public BankAccount name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getGuid() {
        return guid;
    }

    public BankAccount guid(UUID guid) {
        this.guid = guid;
        return this;
    }

    public void setGuid(UUID guid) {
        this.guid = guid;
    }

    public Integer getBankNumber() {
        return bankNumber;
    }

    public BankAccount bankNumber(Integer bankNumber) {
        this.bankNumber = bankNumber;
        return this;
    }

    public void setBankNumber(Integer bankNumber) {
        this.bankNumber = bankNumber;
    }

    public Long getAgencyNumber() {
        return agencyNumber;
    }

    public BankAccount agencyNumber(Long agencyNumber) {
        this.agencyNumber = agencyNumber;
        return this;
    }

    public void setAgencyNumber(Long agencyNumber) {
        this.agencyNumber = agencyNumber;
    }

    public Float getLastOperationDuration() {
        return lastOperationDuration;
    }

    public BankAccount lastOperationDuration(Float lastOperationDuration) {
        this.lastOperationDuration = lastOperationDuration;
        return this;
    }

    public void setLastOperationDuration(Float lastOperationDuration) {
        this.lastOperationDuration = lastOperationDuration;
    }

    public Double getMeanOperationDuration() {
        return meanOperationDuration;
    }

    public BankAccount meanOperationDuration(Double meanOperationDuration) {
        this.meanOperationDuration = meanOperationDuration;
        return this;
    }

    public void setMeanOperationDuration(Double meanOperationDuration) {
        this.meanOperationDuration = meanOperationDuration;
    }

    public Duration getMeanQueueDuration() {
        return meanQueueDuration;
    }

    public BankAccount meanQueueDuration(Duration meanQueueDuration) {
        this.meanQueueDuration = meanQueueDuration;
        return this;
    }

    public void setMeanQueueDuration(Duration meanQueueDuration) {
        this.meanQueueDuration = meanQueueDuration;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public BankAccount balance(BigDecimal balance) {
        this.balance = balance;
        return this;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public LocalDate getOpeningDay() {
        return openingDay;
    }

    public BankAccount openingDay(LocalDate openingDay) {
        this.openingDay = openingDay;
        return this;
    }

    public void setOpeningDay(LocalDate openingDay) {
        this.openingDay = openingDay;
    }

    public Instant getLastOperationDate() {
        return lastOperationDate;
    }

    public BankAccount lastOperationDate(Instant lastOperationDate) {
        this.lastOperationDate = lastOperationDate;
        return this;
    }

    public void setLastOperationDate(Instant lastOperationDate) {
        this.lastOperationDate = lastOperationDate;
    }

    public Boolean isActive() {
        return active;
    }

    public BankAccount active(Boolean active) {
        this.active = active;
        return this;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public BankAccountType getAccountType() {
        return accountType;
    }

    public BankAccount accountType(BankAccountType accountType) {
        this.accountType = accountType;
        return this;
    }

    public void setAccountType(BankAccountType accountType) {
        this.accountType = accountType;
    }

    public byte[] getAttachment() {
        return attachment;
    }

    public BankAccount attachment(byte[] attachment) {
        this.attachment = attachment;
        return this;
    }

    public void setAttachment(byte[] attachment) {
        this.attachment = attachment;
    }

    public String getAttachmentContentType() {
        return attachmentContentType;
    }

    public BankAccount attachmentContentType(String attachmentContentType) {
        this.attachmentContentType = attachmentContentType;
        return this;
    }

    public void setAttachmentContentType(String attachmentContentType) {
        this.attachmentContentType = attachmentContentType;
    }

    public String getDescription() {
        return description;
    }

    public BankAccount description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public BankAccount user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Operation> getOperations() {
        return operations;
    }

    public BankAccount operations(Set<Operation> operations) {
        this.operations = operations;
        return this;
    }

    public BankAccount addOperation(Operation operation) {
        this.operations.add(operation);
        operation.setBankAccount(this);
        return this;
    }

    public BankAccount removeOperation(Operation operation) {
        this.operations.remove(operation);
        operation.setBankAccount(null);
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
        if (!(o instanceof BankAccount)) {
            return false;
        }
        return id != null && id.equals(((BankAccount) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", guid='" + getGuid() + "'" +
            ", bankNumber=" + getBankNumber() +
            ", agencyNumber=" + getAgencyNumber() +
            ", lastOperationDuration=" + getLastOperationDuration() +
            ", meanOperationDuration=" + getMeanOperationDuration() +
            ", meanQueueDuration='" + getMeanQueueDuration() + "'" +
            ", balance=" + getBalance() +
            ", openingDay='" + getOpeningDay() + "'" +
            ", lastOperationDate='" + getLastOperationDate() + "'" +
            ", active='" + isActive() + "'" +
            ", accountType='" + getAccountType() + "'" +
            ", attachment='" + getAttachment() + "'" +
            ", attachmentContentType='" + getAttachmentContentType() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
