package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.AdsService;
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
@RequestMapping("ads")
public class AdsController {
    private final AdsService adsService;

    @GetMapping
    public ResponseEntity<ResponseWrapperAds> getAds() {
        ResponseWrapperAds ads = adsService.getAds();
        return new ResponseEntity<>(ads, HttpStatus.OK);
    }

    @GetMapping("{ad_pk}/comments")
    public ResponseEntity<ResponseWrapperComment> getComments(@PathVariable String ad_pk) {
        ResponseWrapperComment comments = adsService.getComments(ad_pk);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @PostMapping("{ad_pk}/comments")
    public ResponseEntity<Comment> addComments(@PathVariable String ad_pk, @RequestBody Comment comment) {
        Comment cmt = adsService.addComments(ad_pk, comment);
        return new ResponseEntity<>(cmt, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<FullAds> getFullAd(@PathVariable Integer id) {
        FullAds fullAd = adsService.getFullAd(id);
        return new ResponseEntity<>(fullAd, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAds(@PathVariable Integer id) {
        adsService.removeAds(id);
    }

    @PatchMapping("{id}")
    public ResponseEntity<CreateAds> updateAds(@PathVariable Integer id, @RequestBody CreateAds createAds) {
        CreateAds ads = adsService.updateAds(id, createAds);
        return new ResponseEntity<>(ads, HttpStatus.OK);
    }

    @GetMapping("{ad_pk}/comments/{id}")
    public ResponseEntity<Comment> getComments(@PathVariable Integer id, @PathVariable String ad_pk) {
        Comment comments = adsService.getComments(id, ad_pk);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @DeleteMapping("{ad_pk}/comments/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteComments(@PathVariable Integer id, @PathVariable String ad_pk) {
        adsService.deleteComments(id, ad_pk);
    }

    @PatchMapping("{ad_pk}/comments/{id}")
    public ResponseEntity<Comment> updateComments(@PathVariable Integer id, @PathVariable String ad_pk, @RequestBody Comment comment) {
        Comment updateComments = adsService.updateComments(id, ad_pk, comment);
        return new ResponseEntity<>(updateComments, HttpStatus.OK);
    }

    @GetMapping("me")
    public ResponseEntity<ResponseWrapperAds> getAdsMe(@RequestParam(value = "authenticated", required = false) Boolean authenticated,
                                                       @RequestParam(value = "authorities[0].authority", required = false) String authority,
                                                       @RequestParam(value = "credentials", required = false) Object credentials,
                                                       @RequestParam(value = "details", required = false) Object details,
                                                       @RequestParam(value = "principal", required = false) Object principal) {
        ResponseWrapperAds ads = adsService.getAdsMe(authenticated, authority, credentials, details, principal);
        return new ResponseEntity<>(ads, HttpStatus.OK);
    }
}
