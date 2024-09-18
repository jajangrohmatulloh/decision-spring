package com.jajangrohmatulloh.decision.controller;

import org.springframework.web.bind.annotation.RestController;

import com.jajangrohmatulloh.decision.data.Decision;
import com.jajangrohmatulloh.decision.model.WebResponse;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class Controller {

    @Value("${decision.automatch.value}")
    private int automatch;

    @Value("${decision.autonotmatch.value}")
    private int autonotmatch;


    @PostMapping(path = "/",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse getDecisions(@RequestBody List<Integer> scores) {
        
        List<String> results = new ArrayList<>();

        for (Integer score : scores) {
            if (score >= automatch) {
                results.add(Decision.AUTOMATCH.toString());
            } else if (score <= autonotmatch) {
                results.add(Decision.AUTONOTMATCH.toString());
            } else {
                results.add(Decision.AMBIGUOUS.toString());
            }
        }

        WebResponse webResponse = new WebResponse();
        webResponse.setScores(scores);
        
        return webResponse;
    }
    
}
