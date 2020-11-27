package ru.dexsys.telegrambot.domain.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
@Setter
@Getter
@ToString
public class TelegramUser {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "command")
    private String command;

    @Column(name = "question_ident")
    private String questionIdent;

    @Column(name = "phone")
    private String phone;
}
