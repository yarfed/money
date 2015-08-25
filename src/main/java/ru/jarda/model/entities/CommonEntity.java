package ru.jarda.model.entities;

import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;

/**
 * Created by User on 15.08.2015.
 */
@MappedSuperclass
public  class CommonEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "user_id")
    private int userId;
   public CommonEntity(){
try {
    CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    this.userId = user.getUserID();
} catch (NullPointerException e){
    //do nothing
}

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}

