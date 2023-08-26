package link.grooverdev.web.api.employeesecurity.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "users", schema = "icei")
public class GUser {

    @Id
    @UuidGenerator
    @Column(name = "user_id", nullable = false)
    private String userId;

    @ColumnTransformer(read = "pgp_sym_decrypt(username::bytea,current_setting('encrypt.key'))", write = "pgp_sym_encrypt(?,current_setting('encrypt.key'))")
    private String username;

    @Email
    @ColumnTransformer(read = "pgp_sym_decrypt(email::bytea,current_setting('encrypt.key'))", write = "pgp_sym_encrypt(?,current_setting('encrypt.key'))")
    private String email;

    @ColumnTransformer(read = "pgp_sym_decrypt(password::bytea,current_setting('encrypt.key'))", write = "pgp_sym_encrypt(?,current_setting('encrypt.key'))")
    private String password;

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

    //Custom Constructor
    public GUser(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
