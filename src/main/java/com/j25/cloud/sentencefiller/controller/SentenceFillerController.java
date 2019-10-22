package com.j25.cloud.sentencefiller.controller;

import com.j25.cloud.sentencefiller.api.Randomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SentenceFillerController {
    // localhost:8061/fill?sentence=Siema jestem sobie taki Marcin 00###!

    @Autowired
    private Randomizer randomizer;

    @GetMapping("/fill")
    public String fill(@RequestParam(name = "sentence", required = false) String sentence) {
        if (sentence != null) {
            // Todo: zastąp wszystkie !!! słowem j25
            while (sentence.contains("!!!")) {

                sentence = sentence.replaceFirst("!!!", getRandomNumber());
            }
            // Todo: zastąp wszystkie @@@ liczbą 7
            while (sentence.contains("@@@")) {

                sentence = sentence.replaceFirst("@@@", getRandomWord());
            }
            return sentence; /*sentence PO ZAMIANIE*/
        }
        return "Proszę o podanie zdania do wypełnienia";
    }

    private String getRandomWord() {
        return randomizer.randomWord();
    }

    private String getRandomNumber() {
        return randomizer.randomNumber();
    }
}
