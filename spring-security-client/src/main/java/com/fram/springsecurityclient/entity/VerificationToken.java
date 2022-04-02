package com.fram.springsecurityclient.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;


/*
* This is an entity that will store user verification token
* that will expire in 10 mins time
* */
@Entity
@Data
@NoArgsConstructor
public class VerificationToken {

    private static final int EXPIRATION_TIME = 10;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private Date exprirationTime;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_USER_VERIFY_TOKEN"))
    private AppUser user;

    public VerificationToken(String token, AppUser user) {
        super();
        this.token = token;
        this.user = user;
        this.exprirationTime =  calculateExpirationDates(EXPIRATION_TIME);
    }
    public VerificationToken(String token){
        super();
        this.token = token;
        this.exprirationTime = calculateExpirationDates(EXPIRATION_TIME);
    }

    private Date calculateExpirationDates(int exprirationTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE, exprirationTime);
        return new Date(calendar.getTime().getTime());
    }
}
