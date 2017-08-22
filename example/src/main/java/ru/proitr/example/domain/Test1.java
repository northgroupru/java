package ru.proitr.example.domain;

import javax.persistence.*;

@Entity
@Cacheable(false)
@Table(name = "test1", schema = "platform")
public class Test1 {
    @Id
    @Column(length = 40)
    private String id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "short_name")
    private String shortName;
}
