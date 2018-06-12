package com.maryanto.dimas.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "nasabah_dokumen")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NasabahDokumen {

    @Id
    @SequenceGenerator(name = "seq_nasabah_dokumen", sequenceName = "seq_nasabah_dokumen")
    @GeneratedValue(generator = "seq_nasabah_dokumen")
    private Integer id;
    @Column(name = "filename", length = 50)
    private String filename;
    @Column(name = "path", length = 255)
    private String path;
    @ManyToOne
    @JoinColumn(name = "nasabah_general_id", nullable = false)
    private NasabahGeneral nasabahGeneral;
}
