package com.maryanto.dimas.example.controller;

import com.maryanto.dimas.example.entity.NasabahDokumen;
import com.maryanto.dimas.example.entity.NasabahGeneral;
import com.maryanto.dimas.example.entity.NasabahPribadi;
import com.maryanto.dimas.example.repository.NasabahGeneralRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/nasabah/general")
public class NasabahGeneralController {

    @Autowired
    private NasabahGeneralRepository repositoryNasabahGeneral;

    @GetMapping("/list")
    public Page<NasabahGeneral> list(Pageable page) {
        return repositoryNasabahGeneral.findAll(page);
    }

    @GetMapping("/{id}")
    public Resource<NasabahGeneral> findById(@PathVariable("id") NasabahGeneral nasabah) {
        NasabahPribadi pribadi = nasabah.getNasabahPribadi();

        Link linkPribadi = null;
        if (pribadi != null) {
            linkPribadi = ControllerLinkBuilder
                    .linkTo(NasabahPribadiController.class)
                    .slash(pribadi.getId())
                    .withRel("nasabah-pribadi");
        } else {
            linkPribadi = ControllerLinkBuilder
                    .linkTo(NasabahPribadiController.class)
                    .slash("new")
                    .withRel("nasabah-pribadi");
        }

        List<Link> linkDokument = new ArrayList<>();
        if (!nasabah.getListDokument().isEmpty()) {
            for (NasabahDokumen dokument : nasabah.getListDokument())
                linkDokument.add(
                        ControllerLinkBuilder
                                .linkTo(NasabahDokumenController.class)
                                .slash(dokument.getId())
                                .withRel("nasabah-dokumen"));
        }

        Links linksDokument = new Links(linkDokument);
        Resource<NasabahGeneral> resource = new Resource<>(nasabah, linkPribadi);
        resource.add(linksDokument);
        return resource;
    }
}
