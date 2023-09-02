package com.shin.ricu;

import com.shin.ricu.dto.gallery.GalleryCreateDTO;
import com.shin.ricu.service.GalleryService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.AssertTrue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

@SpringBootTest
public class DataValidationTest {

   private static ValidatorFactory factory;
   private static Validator validator;

   @BeforeAll
   public static void init()
   {
       factory = Validation.buildDefaultValidatorFactory();
       validator = factory.getValidator();
   }
    @Test
    public void GalleryValidTest()
    {
        GalleryCreateDTO galleryCreateDTO = GalleryCreateDTO.builder()
                .galleryID("Hello")
                .manager("aaa")
                .title("")
                .explanation("AAAAA")
                .build();

        Set<ConstraintViolation<GalleryCreateDTO>> violationSet = validator.validate(galleryCreateDTO);
        Assertions.assertFalse(violationSet.isEmpty());

        violationSet.forEach(error -> {
            Assertions.assertEquals(error.getMessage(), "Title cannot be Empty");
        });
    }
}
