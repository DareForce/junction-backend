package com.dareforce.junctionbackend.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name = "menu_ingred",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "unq_menu_ingred_menu_id_ingred_id",
                        columnNames = {"ingred_id", "menu_id"}
                )
        }
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MenuIngred {
    @Id
    @Column(name = "menu_ingred_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingred_id")
    private Ingredient ingredient;

    private Long quantity;

}
