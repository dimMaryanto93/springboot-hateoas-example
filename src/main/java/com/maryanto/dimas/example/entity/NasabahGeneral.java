package com.maryanto.dimas.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "nasabah_general")
public class NasabahGeneral {

    @Id
    @SequenceGenerator(name = "seq_nasabah_general", sequenceName = "seq_nasabah_general")
    @GeneratedValue(generator = "seq_nasabah_general")
    private Integer id;
    @Column(name = "identitas_nasabah", nullable = false, length = 64)
    private String identitas;
    @Column(name = "nama_lengkap", nullable = false, length = 100)
    private String namaLengkap;
    @Column(name = "tanggal_lahir")
    private Date tanggalLahir;
    @Column(name = "nomor_telephon", length = 15)
    private String nomorTelephon;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "nasabah_pribadi_id")
    private NasabahPribadi nasabahPribadi;
    @JsonIgnore
    @OneToMany(mappedBy = "nasabahGeneral")
    private List<NasabahDokumen> listDokument = new ArrayList<>();
}
