package io.github.jhipster.sample.web.rest;

import io.github.jhipster.sample.AbstractNeo4jIT;
import io.github.jhipster.sample.SampleNeo4JNoCacheApp;
import io.github.jhipster.sample.domain.BankAccount;
import io.github.jhipster.sample.repository.BankAccountRepository;
import io.github.jhipster.sample.service.BankAccountService;
import io.github.jhipster.sample.service.dto.BankAccountDTO;
import io.github.jhipster.sample.service.mapper.BankAccountMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Base64Utils;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import io.github.jhipster.sample.domain.enumeration.BankAccountType;
/**
 * Integration tests for the {@link BankAccountResource} REST controller.
 */
@SpringBootTest(classes = SampleNeo4JNoCacheApp.class)
@ExtendWith(AbstractNeo4jIT.class)
@AutoConfigureMockMvc
@WithMockUser
public class BankAccountResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final UUID DEFAULT_GUID = UUID.randomUUID();
    private static final UUID UPDATED_GUID = UUID.randomUUID();

    private static final Integer DEFAULT_BANK_NUMBER = 1;
    private static final Integer UPDATED_BANK_NUMBER = 2;

    private static final Long DEFAULT_AGENCY_NUMBER = 1L;
    private static final Long UPDATED_AGENCY_NUMBER = 2L;

    private static final Float DEFAULT_LAST_OPERATION_DURATION = 1F;
    private static final Float UPDATED_LAST_OPERATION_DURATION = 2F;

    private static final Double DEFAULT_MEAN_OPERATION_DURATION = 1D;
    private static final Double UPDATED_MEAN_OPERATION_DURATION = 2D;

    private static final Duration DEFAULT_MEAN_QUEUE_DURATION = Duration.ofHours(6);
    private static final Duration UPDATED_MEAN_QUEUE_DURATION = Duration.ofHours(12);

    private static final BigDecimal DEFAULT_BALANCE = new BigDecimal(1);
    private static final BigDecimal UPDATED_BALANCE = new BigDecimal(2);

    private static final LocalDate DEFAULT_OPENING_DAY = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_OPENING_DAY = LocalDate.now(ZoneId.systemDefault());

    private static final Instant DEFAULT_LAST_OPERATION_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_OPERATION_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Boolean DEFAULT_ACTIVE = false;
    private static final Boolean UPDATED_ACTIVE = true;

    private static final BankAccountType DEFAULT_ACCOUNT_TYPE = BankAccountType.CHECKING;
    private static final BankAccountType UPDATED_ACCOUNT_TYPE = BankAccountType.SAVINGS;

    private static final byte[] DEFAULT_ATTACHMENT = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_ATTACHMENT = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_ATTACHMENT_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_ATTACHMENT_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private BankAccountMapper bankAccountMapper;

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private MockMvc restBankAccountMockMvc;

    private BankAccount bankAccount;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BankAccount createEntity() {
        BankAccount bankAccount = new BankAccount()
            .name(DEFAULT_NAME)
            .guid(DEFAULT_GUID)
            .bankNumber(DEFAULT_BANK_NUMBER)
            .agencyNumber(DEFAULT_AGENCY_NUMBER)
            .lastOperationDuration(DEFAULT_LAST_OPERATION_DURATION)
            .meanOperationDuration(DEFAULT_MEAN_OPERATION_DURATION)
            .meanQueueDuration(DEFAULT_MEAN_QUEUE_DURATION)
            .balance(DEFAULT_BALANCE)
            .openingDay(DEFAULT_OPENING_DAY)
            .lastOperationDate(DEFAULT_LAST_OPERATION_DATE)
            .active(DEFAULT_ACTIVE)
            .accountType(DEFAULT_ACCOUNT_TYPE)
            .attachment(DEFAULT_ATTACHMENT)
            .attachmentContentType(DEFAULT_ATTACHMENT_CONTENT_TYPE)
            .description(DEFAULT_DESCRIPTION);
        return bankAccount;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BankAccount createUpdatedEntity() {
        BankAccount bankAccount = new BankAccount()
            .name(UPDATED_NAME)
            .guid(UPDATED_GUID)
            .bankNumber(UPDATED_BANK_NUMBER)
            .agencyNumber(UPDATED_AGENCY_NUMBER)
            .lastOperationDuration(UPDATED_LAST_OPERATION_DURATION)
            .meanOperationDuration(UPDATED_MEAN_OPERATION_DURATION)
            .meanQueueDuration(UPDATED_MEAN_QUEUE_DURATION)
            .balance(UPDATED_BALANCE)
            .openingDay(UPDATED_OPENING_DAY)
            .lastOperationDate(UPDATED_LAST_OPERATION_DATE)
            .active(UPDATED_ACTIVE)
            .accountType(UPDATED_ACCOUNT_TYPE)
            .attachment(UPDATED_ATTACHMENT)
            .attachmentContentType(UPDATED_ATTACHMENT_CONTENT_TYPE)
            .description(UPDATED_DESCRIPTION);
        return bankAccount;
    }

    @BeforeEach
    public void initTest() {
        bankAccount = createEntity();
    }

    @Test
    public void createBankAccount() throws Exception {
        int databaseSizeBeforeCreate = bankAccountRepository.findAll().size();

        // Create the BankAccount
        BankAccountDTO bankAccountDTO = bankAccountMapper.toDto(bankAccount);
        restBankAccountMockMvc.perform(post("/api/bank-accounts")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bankAccountDTO)))
            .andExpect(status().isCreated());

        // Validate the BankAccount in the database
        List<BankAccount> bankAccountList = bankAccountRepository.findAll();
        assertThat(bankAccountList).hasSize(databaseSizeBeforeCreate + 1);
        BankAccount testBankAccount = bankAccountList.get(bankAccountList.size() - 1);
        assertThat(testBankAccount.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testBankAccount.getGuid()).isEqualTo(DEFAULT_GUID);
        assertThat(testBankAccount.getBankNumber()).isEqualTo(DEFAULT_BANK_NUMBER);
        assertThat(testBankAccount.getAgencyNumber()).isEqualTo(DEFAULT_AGENCY_NUMBER);
        assertThat(testBankAccount.getLastOperationDuration()).isEqualTo(DEFAULT_LAST_OPERATION_DURATION);
        assertThat(testBankAccount.getMeanOperationDuration()).isEqualTo(DEFAULT_MEAN_OPERATION_DURATION);
        assertThat(testBankAccount.getMeanQueueDuration()).isEqualTo(DEFAULT_MEAN_QUEUE_DURATION);
        assertThat(testBankAccount.getBalance()).isEqualTo(DEFAULT_BALANCE);
        assertThat(testBankAccount.getOpeningDay()).isEqualTo(DEFAULT_OPENING_DAY);
        assertThat(testBankAccount.getLastOperationDate()).isEqualTo(DEFAULT_LAST_OPERATION_DATE);
        assertThat(testBankAccount.isActive()).isEqualTo(DEFAULT_ACTIVE);
        assertThat(testBankAccount.getAccountType()).isEqualTo(DEFAULT_ACCOUNT_TYPE);
        assertThat(testBankAccount.getAttachment()).isEqualTo(DEFAULT_ATTACHMENT);
        assertThat(testBankAccount.getAttachmentContentType()).isEqualTo(DEFAULT_ATTACHMENT_CONTENT_TYPE);
        assertThat(testBankAccount.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    public void createBankAccountWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bankAccountRepository.findAll().size();

        // Create the BankAccount with an existing ID
        bankAccount.setId("existing_id");
        BankAccountDTO bankAccountDTO = bankAccountMapper.toDto(bankAccount);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBankAccountMockMvc.perform(post("/api/bank-accounts")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bankAccountDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BankAccount in the database
        List<BankAccount> bankAccountList = bankAccountRepository.findAll();
        assertThat(bankAccountList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = bankAccountRepository.findAll().size();
        // set the field null
        bankAccount.setName(null);

        // Create the BankAccount, which fails.
        BankAccountDTO bankAccountDTO = bankAccountMapper.toDto(bankAccount);

        restBankAccountMockMvc.perform(post("/api/bank-accounts")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bankAccountDTO)))
            .andExpect(status().isBadRequest());

        List<BankAccount> bankAccountList = bankAccountRepository.findAll();
        assertThat(bankAccountList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkBalanceIsRequired() throws Exception {
        int databaseSizeBeforeTest = bankAccountRepository.findAll().size();
        // set the field null
        bankAccount.setBalance(null);

        // Create the BankAccount, which fails.
        BankAccountDTO bankAccountDTO = bankAccountMapper.toDto(bankAccount);

        restBankAccountMockMvc.perform(post("/api/bank-accounts")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bankAccountDTO)))
            .andExpect(status().isBadRequest());

        List<BankAccount> bankAccountList = bankAccountRepository.findAll();
        assertThat(bankAccountList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllBankAccounts() throws Exception {
        // Initialize the database
        bankAccountRepository.save(bankAccount);

        // Get all the bankAccountList
        restBankAccountMockMvc.perform(get("/api/bank-accounts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].guid").value(hasItem(DEFAULT_GUID.toString())))
            .andExpect(jsonPath("$.[*].bankNumber").value(hasItem(DEFAULT_BANK_NUMBER)))
            .andExpect(jsonPath("$.[*].agencyNumber").value(hasItem(DEFAULT_AGENCY_NUMBER.intValue())))
            .andExpect(jsonPath("$.[*].lastOperationDuration").value(hasItem(DEFAULT_LAST_OPERATION_DURATION.doubleValue())))
            .andExpect(jsonPath("$.[*].meanOperationDuration").value(hasItem(DEFAULT_MEAN_OPERATION_DURATION.doubleValue())))
            .andExpect(jsonPath("$.[*].meanQueueDuration").value(hasItem(DEFAULT_MEAN_QUEUE_DURATION.toString())))
            .andExpect(jsonPath("$.[*].balance").value(hasItem(DEFAULT_BALANCE.intValue())))
            .andExpect(jsonPath("$.[*].openingDay").value(hasItem(DEFAULT_OPENING_DAY.toString())))
            .andExpect(jsonPath("$.[*].lastOperationDate").value(hasItem(DEFAULT_LAST_OPERATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].active").value(hasItem(DEFAULT_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].accountType").value(hasItem(DEFAULT_ACCOUNT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].attachmentContentType").value(hasItem(DEFAULT_ATTACHMENT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].attachment").value(hasItem(Base64Utils.encodeToString(DEFAULT_ATTACHMENT))))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }

    @Test
    public void getBankAccount() throws Exception {
        // Initialize the database
        bankAccountRepository.save(bankAccount);

        // Get the bankAccount
        restBankAccountMockMvc.perform(get("/api/bank-accounts/{id}", bankAccount.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.guid").value(DEFAULT_GUID.toString()))
            .andExpect(jsonPath("$.bankNumber").value(DEFAULT_BANK_NUMBER))
            .andExpect(jsonPath("$.agencyNumber").value(DEFAULT_AGENCY_NUMBER.intValue()))
            .andExpect(jsonPath("$.lastOperationDuration").value(DEFAULT_LAST_OPERATION_DURATION.doubleValue()))
            .andExpect(jsonPath("$.meanOperationDuration").value(DEFAULT_MEAN_OPERATION_DURATION.doubleValue()))
            .andExpect(jsonPath("$.meanQueueDuration").value(DEFAULT_MEAN_QUEUE_DURATION.toString()))
            .andExpect(jsonPath("$.balance").value(DEFAULT_BALANCE.intValue()))
            .andExpect(jsonPath("$.openingDay").value(DEFAULT_OPENING_DAY.toString()))
            .andExpect(jsonPath("$.lastOperationDate").value(DEFAULT_LAST_OPERATION_DATE.toString()))
            .andExpect(jsonPath("$.active").value(DEFAULT_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.accountType").value(DEFAULT_ACCOUNT_TYPE.toString()))
            .andExpect(jsonPath("$.attachmentContentType").value(DEFAULT_ATTACHMENT_CONTENT_TYPE))
            .andExpect(jsonPath("$.attachment").value(Base64Utils.encodeToString(DEFAULT_ATTACHMENT)))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    public void getNonExistingBankAccount() throws Exception {
        // Get the bankAccount
        restBankAccountMockMvc.perform(get("/api/bank-accounts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateBankAccount() throws Exception {
        // Initialize the database
        bankAccountRepository.save(bankAccount);

        int databaseSizeBeforeUpdate = bankAccountRepository.findAll().size();

        // Update the bankAccount
        BankAccount updatedBankAccount = bankAccountRepository.findById(bankAccount.getId()).get();
        updatedBankAccount
            .name(UPDATED_NAME)
            .guid(UPDATED_GUID)
            .bankNumber(UPDATED_BANK_NUMBER)
            .agencyNumber(UPDATED_AGENCY_NUMBER)
            .lastOperationDuration(UPDATED_LAST_OPERATION_DURATION)
            .meanOperationDuration(UPDATED_MEAN_OPERATION_DURATION)
            .meanQueueDuration(UPDATED_MEAN_QUEUE_DURATION)
            .balance(UPDATED_BALANCE)
            .openingDay(UPDATED_OPENING_DAY)
            .lastOperationDate(UPDATED_LAST_OPERATION_DATE)
            .active(UPDATED_ACTIVE)
            .accountType(UPDATED_ACCOUNT_TYPE)
            .attachment(UPDATED_ATTACHMENT)
            .attachmentContentType(UPDATED_ATTACHMENT_CONTENT_TYPE)
            .description(UPDATED_DESCRIPTION);
        BankAccountDTO bankAccountDTO = bankAccountMapper.toDto(updatedBankAccount);

        restBankAccountMockMvc.perform(put("/api/bank-accounts")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bankAccountDTO)))
            .andExpect(status().isOk());

        // Validate the BankAccount in the database
        List<BankAccount> bankAccountList = bankAccountRepository.findAll();
        assertThat(bankAccountList).hasSize(databaseSizeBeforeUpdate);
        BankAccount testBankAccount = bankAccountList.get(bankAccountList.size() - 1);
        assertThat(testBankAccount.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testBankAccount.getGuid()).isEqualTo(UPDATED_GUID);
        assertThat(testBankAccount.getBankNumber()).isEqualTo(UPDATED_BANK_NUMBER);
        assertThat(testBankAccount.getAgencyNumber()).isEqualTo(UPDATED_AGENCY_NUMBER);
        assertThat(testBankAccount.getLastOperationDuration()).isEqualTo(UPDATED_LAST_OPERATION_DURATION);
        assertThat(testBankAccount.getMeanOperationDuration()).isEqualTo(UPDATED_MEAN_OPERATION_DURATION);
        assertThat(testBankAccount.getMeanQueueDuration()).isEqualTo(UPDATED_MEAN_QUEUE_DURATION);
        assertThat(testBankAccount.getBalance()).isEqualTo(UPDATED_BALANCE);
        assertThat(testBankAccount.getOpeningDay()).isEqualTo(UPDATED_OPENING_DAY);
        assertThat(testBankAccount.getLastOperationDate()).isEqualTo(UPDATED_LAST_OPERATION_DATE);
        assertThat(testBankAccount.isActive()).isEqualTo(UPDATED_ACTIVE);
        assertThat(testBankAccount.getAccountType()).isEqualTo(UPDATED_ACCOUNT_TYPE);
        assertThat(testBankAccount.getAttachment()).isEqualTo(UPDATED_ATTACHMENT);
        assertThat(testBankAccount.getAttachmentContentType()).isEqualTo(UPDATED_ATTACHMENT_CONTENT_TYPE);
        assertThat(testBankAccount.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    public void updateNonExistingBankAccount() throws Exception {
        int databaseSizeBeforeUpdate = bankAccountRepository.findAll().size();

        // Create the BankAccount
        BankAccountDTO bankAccountDTO = bankAccountMapper.toDto(bankAccount);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBankAccountMockMvc.perform(put("/api/bank-accounts")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bankAccountDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BankAccount in the database
        List<BankAccount> bankAccountList = bankAccountRepository.findAll();
        assertThat(bankAccountList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteBankAccount() throws Exception {
        // Initialize the database
        bankAccountRepository.save(bankAccount);

        int databaseSizeBeforeDelete = bankAccountRepository.findAll().size();

        // Delete the bankAccount
        restBankAccountMockMvc.perform(delete("/api/bank-accounts/{id}", bankAccount.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BankAccount> bankAccountList = bankAccountRepository.findAll();
        assertThat(bankAccountList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
