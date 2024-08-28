package com.rickyandmorti.rickyandmorti.dto;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DireccionDTO {
    private String calle;
    private String ciudad;
    private String codigoPostal;
    private String pais;
}
