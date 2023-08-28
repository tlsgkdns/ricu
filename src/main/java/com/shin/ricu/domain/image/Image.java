package com.shin.ricu.domain.image;

import com.shin.ricu.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Image extends BaseEntity {
    @Id
    private String uuid;
    private String fileName;

    public String getLink()
    {
        return uuid + "_" + fileName;
    }
}
