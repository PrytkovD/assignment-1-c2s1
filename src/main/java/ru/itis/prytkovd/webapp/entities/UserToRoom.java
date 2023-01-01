package ru.itis.prytkovd.webapp.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.prytkovd.persist.annotations.ForeignKey;
import ru.itis.prytkovd.persist.annotations.PrimaryKey;
import ru.itis.prytkovd.persist.annotations.Table;

@Table(name = "users_to_rooms")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserToRoom {
    @PrimaryKey
    private Long id;

    @ForeignKey(references = User.class)
    private Long userId;

    @ForeignKey(references = Room.class)
    private Long roomId;
}
