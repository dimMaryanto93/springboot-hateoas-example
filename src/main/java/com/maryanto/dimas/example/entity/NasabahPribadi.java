package com.maryanto.dimas.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "nasabah_pribadi")
public class NasabahPribadi {

    @Id
    @SequenceGenerator(name = "seq_nasabah_pribadi", sequenceName = "seq_nasabah_pribadi")
    @GeneratedValue(generator = "seq_nasabah_pribadi")
    private Integer id;
    @Column(name = "nama_ibu_kandung", nullable = false, length = 100)
    private String namaIbuKandung;
    @Column(name = "nomor_telephone_orangtua", length = 15)
    private String nomorTelephon;
}
