package com.jcerdeira.base_saij_normativa_provincial.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Builder;
import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;

import java.time.LocalDate;

@Entity
@Table(name="normas")
@Getter
@Builder
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Norma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="provincia_nombre", nullable = false)
    private String provinciaNombre;

    @Column(name="tipo_norma", nullable = false, length = 50)
    private String tipoNorma;

    @Column(name="numero_norma", nullable = false)
    private Integer numeroNorma;

    @Column(name="estado_vigencia", nullable = false, length = 100)
    private String estadoVigencia;

    @Column(name="fecha", nullable = false)
    private LocalDate fecha;

    @Column(name="fecha_publicacion")
    private LocalDate fechaPublicacion;

    @Column(name="nombre_norma", length = 200)
    private String nombreNorma;

    @Column(name="titulo_resumido", columnDefinition = "TEXT")
    private String tituloResumido;

    @Column(name="titulo_sumario", columnDefinition = "TEXT")
    private String tituloSumario;

    @Column(name="informacion_digesto", columnDefinition = "TEXT")
    private String informacionDigesto;

    @Column(name="texto_actualizado", nullable = false)
    private String textoActualizado;

}