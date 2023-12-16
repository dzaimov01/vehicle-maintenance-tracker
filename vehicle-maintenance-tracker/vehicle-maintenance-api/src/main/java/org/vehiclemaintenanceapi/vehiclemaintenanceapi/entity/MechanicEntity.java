package org.vehiclemaintenanceapi.vehiclemaintenanceapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mechanics")
public class MechanicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String specialization;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name="mechanics_maintenance_logs",
            joinColumns = @JoinColumn(name = "mechanics_id"),
            inverseJoinColumns = @JoinColumn(name = "maintenance_logs_id")
    )
    private List<MaintenanceLogEntity> maintenanceLogs;
}
