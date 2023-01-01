package ru.itis.prytkovd.webapp.entities;

import lombok.*;
import ru.itis.prytkovd.persist.annotations.Column;
import ru.itis.prytkovd.persist.annotations.PrimaryKey;
import ru.itis.prytkovd.persist.annotations.Table;

@Table(name = "rooms")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Room {
    @PrimaryKey
    private Long id;

    @Column
    @NonNull
    private String name;

    @Column
    @NonNull
    private String code;
}
