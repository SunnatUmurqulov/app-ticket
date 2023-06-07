package uz.pdp.appticket.entity.template;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import uz.pdp.appticket.entity.User;

import java.sql.Timestamp;

@MappedSuperclass
@Data
public abstract class AbstractEntity {

    @CreationTimestamp
    private Timestamp createdAt;

    @Column(nullable = false)
    @UpdateTimestamp
    private Timestamp updateAt;

    @JoinColumn(updatable = false)
    @CreatedBy
    @ManyToOne(fetch = FetchType.EAGER)
    private User createdBy;

    @LastModifiedBy
    @ManyToOne(fetch = FetchType.EAGER)
    private User updateBy;
}
