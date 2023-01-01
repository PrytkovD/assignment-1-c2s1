package ru.itis.prytkovd.webapp.entities;

import lombok.*;
import ru.itis.prytkovd.persist.annotations.Column;
import ru.itis.prytkovd.persist.annotations.ForeignKey;
import ru.itis.prytkovd.persist.annotations.PrimaryKey;
import ru.itis.prytkovd.persist.annotations.Table;

@Table(name = "money_requests")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MoneyRequest {
    @PrimaryKey
    private Long id;

    @ForeignKey(references = User.class)
    @NonNull
    private Long assignerId;

    @ForeignKey(references = User.class)
    @NonNull
    private Long assigneeId;

    @ForeignKey(references = Room.class)
    @NonNull
    private Long roomId;

    @Column
    @NonNull
    private Integer amount;

    @Column
    @NonNull
    private String description;
}
