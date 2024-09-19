package com.jajangrohmatulloh.decision.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jajangrohmatulloh.decision.data.Decision;
import com.jajangrohmatulloh.decision.model.ClientRequest;
import com.jajangrohmatulloh.decision.model.WebResponse;

@RestController
public class Controller {

    @Value("${decision.automatch.value}")
    private int automatch;

    @Value("${decision.autonotmatch.value}")
    private int autonotmatch;

    @PostMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse getDecisions(@RequestBody ClientRequest request) {

        List<Integer> scores = request.getScores();

        List<String> decisions = new ArrayList<>();

        for (Integer score : scores) {
            if (score >= automatch) {
                decisions.add(Decision.AUTOMATCH.toString());
            } else if (score <= autonotmatch) {
                decisions.add(Decision.AUTONOTMATCH.toString());
            } else {
                decisions.add(Decision.AMBIGUOUS.toString());
            }
        }

        WebResponse webResponse = new WebResponse();
        webResponse.setDecisions(decisions);

        return webResponse;
    }

}
