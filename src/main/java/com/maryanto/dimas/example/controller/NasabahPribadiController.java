package com.maryanto.dimas.example.controller;

import com.maryanto.dimas.example.entity.NasabahPribadi;
import com.maryanto.dimas.example.repository.NasabahPribadiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/nasabah/pribadi")
public class NasabahPribadiController {

    @Autowired
    private NasabahPribadiRepository repositoryNasabahPribadi;

    @PostMapping("/new")
    public ResponseEntity<NasabahPribadi> save(@RequestBody NasabahPribadi nasabah) {
        nasabah = repositoryNasabahPribadi.save(nasabah);
        return new ResponseEntity<>(nasabah, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NasabahPribadi> findById(@PathVariable("id") NasabahPribadi nasabah) {
        return new ResponseEntity<>(nasabah, HttpStatus.OK);
    }
}
