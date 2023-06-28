package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.AdsService;

import java.io.IOException;

@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
@RequestMapping("ads")
public class AdsController {
    private final AdsService adsService;

    @GetMapping
    public ResponseEntity<ResponseWrapperAds> getAds(@RequestParam(value = "search", required = false) String search) {
        ResponseWrapperAds ads = adsService.getAds(search);
        return new ResponseEntity<>(ads, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Ads> addAds(@RequestParam("properties") CreateAds properties,
                                      @RequestParam MultipartFile image) throws IOException {
        Ads ads = adsService.addAds(properties, image);
        return new ResponseEntity<>(ads, HttpStatus.OK);
    }

    @GetMapping("{id}/comments")
    public ResponseEntity<ResponseWrapperComment> getComments(@PathVariable Integer id, Authentication authentication) {
        ResponseWrapperComment comments = adsService.getComments(id, authentication.getName());
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @PostMapping("{id}/comments")
    public ResponseEntity<Comment> addComments(@PathVariable Integer id, @RequestBody Comment comment, Authentication authentication) {
        Comment cmt = adsService.addComments(id, comment, authentication.getName());
        return new ResponseEntity<>(cmt, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<FullAds> getFullAd(@PathVariable Integer id, Authentication authentication) {
        FullAds fullAd = adsService.getFullAd(id, authentication.getName());
        return new ResponseEntity<>(fullAd, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAds(@PathVariable Integer id, Authentication authentication) {
        adsService.removeAds(id, authentication.getName());
    }

    @PatchMapping("{id}")
    public ResponseEntity<Ads> updateAds(@PathVariable Integer id, @RequestBody CreateAds createAds, Authentication authentication) {
        Ads ads = adsService.updateAds(id, createAds, authentication.getName());
        return new ResponseEntity<>(ads, HttpStatus.OK);
    }

    @DeleteMapping("{adId}/comments/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteComments(@PathVariable Integer adId, @PathVariable Integer commentId, Authentication authentication) {
        adsService.deleteComments(adId, commentId, authentication.getName());
    }

    @PatchMapping("{adId}/comments/{commentId}")
    public ResponseEntity<Comment> updateComments(@PathVariable Integer adId, @PathVariable Integer commentId,
                                                  @RequestBody Comment comment, Authentication authentication) {
        Comment updateComments = adsService.updateComments(adId, commentId, comment, authentication.getName());
        return new ResponseEntity<>(updateComments, HttpStatus.OK);
    }

    @GetMapping("me")
    public ResponseEntity<ResponseWrapperAds> getAdsMe(Authentication authentication) {
        ResponseWrapperAds ads = adsService.getAdsMe(authentication.getName());
        return new ResponseEntity<>(ads, HttpStatus.OK);
    }

    @PatchMapping(value = "{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateAdsImage(@PathVariable Integer id, @RequestParam MultipartFile image) {
        adsService.updateAdsImage(image, id);
        return ResponseEntity.ok().build();
    }
    @GetMapping(value = "/images/{id}/", produces = {MediaType.IMAGE_PNG_VALUE})
    public byte[] getImage(@PathVariable Integer id) {
        return adsService.getAdsImage(id);
    }
}
