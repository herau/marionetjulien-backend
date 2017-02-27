package org.marionetjulien.domains;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * Created by n27 on 2/27/17.
 */
@Getter @Setter
public class Form {

   @NotNull
   private String name;
    @NotNull
   private String email;
    @NotNull
   private boolean present;



}
