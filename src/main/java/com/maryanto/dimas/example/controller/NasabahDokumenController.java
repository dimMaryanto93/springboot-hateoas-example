package com.maryanto.dimas.example.controller;

import com.maryanto.dimas.example.entity.NasabahDokumen;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/nasabah/dokumen")
public class NasabahDokumenController {

    @GetMapping("/{id}")
    public ResponseEntity<NasabahDokumen> findById(@PathVariable("id") NasabahDokumen nasabah) {
        return new ResponseEntity<>(nasabah, HttpStatus.OK);
    }
}
