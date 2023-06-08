package org.tgbotusers.bot.handler.status.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Collection;

@Getter
@Setter
@EqualsAndHashCode(exclude = "id")
@Builder
@Entity
@Table(name = "appeal_data")
@NoArgsConstructor
@AllArgsConstructor
public class Appeal
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "number")
    private long appealNumber;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userAppeal;

    @Column(name = "address")
    private String address;

    @Column(name = "text", columnDefinition = "MEDIUMTEXT")
    private String appealText;

    @Column(name = "appeal_type")
    private String appealType;

    @Column(name = "status_time", columnDefinition = "DATETIME")
    private LocalDateTime statusTime;

    @OneToMany(mappedBy = "appeal", fetch = FetchType.EAGER)
    private Collection<File> files;

}
