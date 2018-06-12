# Web service dengan HATEOAS

Dengan menggunakan konsep HATEOAS, kita bisa menyematkan link tambahan di response body, jika pada response body web service dengan response type `application/json` seperti berikut:


```json
{
    "id": 1,
    "identitas": "621234-12344-1234-5343",
    "namaLengkap": "Dimas maryanto",
    "tanggalLahir": "1993-03-28",
    "nomorTelephon": "082117355133"
}
```

Dengan menggunkan HATEOAS kita bisa menyematkan link tambahkan seperti berikut contohnya:

```json
{
    "id": 1,
    "identitas": "621234-12344-1234-5343",
    "namaLengkap": "Dimas maryanto",
    "tanggalLahir": "1993-03-28",
    "nomorTelephon": "082117355133",
    "_links": {
        "nasabah-dokumen": [
            { "href": "http://localhost:8080/api/nasabah/dokumen/1" },
            { "href": "http://localhost:8080/api/nasabah/dokumen/2" },
            { "href": "http://localhost:8080/api/nasabah/dokumen/3" }
        ],
        "nasabah-pribadi": { "href": "http://localhost:8080/api/nasabah/pribadi/1" }
    }
}
```

Tanpa ada perubahan di model / entity / domain di project spring. Dengan mengimplementasikan seperti berikut:

```java
@GetMapping("/{id}")
public Resource<NasabahGeneral> findById(@PathVariable("id") NasabahGeneral nasabah) {
    NasabahPribadi pribadi = nasabah.getNasabahPribadi();

    Link linkPribadi = null;
    if (pribadi != null) {
        linkPribadi = linkTo(NasabahPribadiController.class)
                .slash(pribadi.getId())
                .withRel("nasabah-pribadi");
    } else {
        linkPribadi = linkTo(NasabahPribadiController.class)
                .slash("new")
                .withRel("nasabah-pribadi");
    }

    List<Link> linkDokument = new ArrayList<>();
    if (!nasabah.getListDokument().isEmpty()) {
        linkDokument = nasabah.getListDokument().stream().map(dokument -> linkTo(NasabahDokumenController.class)
                .slash(dokument.getId())
                .withRel("nasabah-dokumen")).collect(Collectors.toList());
    }

    Links linksDokument = new Links(linkDokument);
    Resource<NasabahGeneral> resource = new Resource<>(nasabah, new ArrayList<>());
    resource.add(linksDokument);
    resource.add(linkPribadi);
    return resource;
}
```