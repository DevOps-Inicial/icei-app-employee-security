package link.grooverdev.web.api.employeesecurity.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "employees", schema = "icei")
public class GEmployee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id", nullable = false)
    private Long employeeId;

    @Column(name = "first_name")
    @ColumnTransformer(read = "pgp_sym_decrypt(first_name::bytea,current_setting('encrypt.key'))", write = "pgp_sym_encrypt(?,current_setting('encrypt.key'))")
    private String firstName;

    @Column(name = "last_name")
    @ColumnTransformer(read = "pgp_sym_decrypt(last_name::bytea,current_setting('encrypt.key'))", write = "pgp_sym_encrypt(?,current_setting('encrypt.key'))")
    private String lastName;

    @Email
    @ColumnTransformer(read = "pgp_sym_decrypt(email::bytea,current_setting('encrypt.key'))", write = "pgp_sym_encrypt(?,current_setting('encrypt.key'))")
    private String email;

    @ColumnTransformer(read = "pgp_sym_decrypt(address::bytea,current_setting('encrypt.key'))", write = "pgp_sym_encrypt(?,current_setting('encrypt.key'))")
    private String address;

    @Column(name = "cell_phone")
    @ColumnTransformer(read = "pgp_sym_decrypt(cell_phone::bytea,current_setting('encrypt.key'))", write = "pgp_sym_encrypt(?,current_setting('encrypt.key'))")
    private String cellPhone;

    private boolean enabled;

    @CreatedBy
    @Column(name = "created_by_user", nullable = false, updatable = false, length = 20)
    private String createdByUser;

    @CreatedDate
    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedBy
    @Column(name = "last_modified_by_user", nullable = false, updatable = false, length = 20)
    private String lastModifiedByUser;

    @LastModifiedDate
    @Column(name = "last_modified_date", nullable = false)
    private LocalDateTime lastModifiedDate;

    @Version
    @Column(name = "number_of_modifications", nullable = false)
    private long numberOfModification;
}
