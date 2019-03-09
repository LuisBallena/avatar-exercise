package com.scrap.avatar.api.dto;


import com.scrap.avatar.entity.GenderEntity;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * PersonDTO.
 *
 * @author Luis Alonso Ballena Garcia
 *
 * Por temas prácticos, no se agrega el message en un archivo properties para ser internacionalizado
 *
 */

public class PersonDTO {

    @NotBlank(message = "el campo 'name' no puede ser nulo o vacío")
    @Length(max = 128, message = "el campo 'name' permite hasta 128 caracteres")
    private String name;

    @Digits(integer = 5, fraction = 2, message = "el campo 'height' permite 5 enteros y 2 decimales")
    @DecimalMin(value = "0.01", message = "el valor minimo del campo 'height' es 0.01")
    private Double height;

    @Digits(integer = 5, fraction = 2, message = "el campo 'mass' permite 5 enteros y 2 decimales")
    @DecimalMin(value = "0.01", message = "el valor minimo del campo 'mass' es 0.01")
    private Double mass;

    private String hairColor;

    @NotNull(message = "el campo 'gender' no puede ser nulo")
    private GenderEntity gender;

    @NotBlank(message = "el campo 'planet' no puede ser nulo o vacío")
    @Length(max = 64, message = "el campo 'name' permite hasta 64 caracteres")
    private String planet;

    public PersonDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getMass() {
        return mass;
    }

    public void setMass(Double mass) {
        this.mass = mass;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public GenderEntity getGender() {
        return gender;
    }

    public void setGender(GenderEntity gender) {
        this.gender = gender;
    }

    public String getPlanet() {
        return planet;
    }

    public void setPlanet(String planet) {
        this.planet = planet;
    }
}
