package org.tgbotusers.bot.handler.status.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(exclude = "id")
@Builder
@Entity
@Table(name = "files")
@NoArgsConstructor
@AllArgsConstructor
public class File
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "link")
    private String link;

    @Column(name = "status_time", columnDefinition = "DATETIME")
    private LocalDateTime statusTime;

    @ManyToOne
    @JoinColumn(name = "appeal_id")
    private Appeal appeal;
}
