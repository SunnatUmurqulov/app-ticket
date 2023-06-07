package uz.pdp.appticket.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    private String description;
    @Column(nullable = false)
    private Timestamp time;
    @ManyToOne
    private Hall hall;
    @Column(nullable = false)
    private Integer maxTicketAmount;
    private Boolean isDeleted = false;
}
