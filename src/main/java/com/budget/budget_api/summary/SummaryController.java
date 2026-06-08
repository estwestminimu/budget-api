package com.budget.budget_api.summary;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import com.budget.budget_api.summary.dto.SummaryResponse;

@RestController
@RequestMapping("/accounts/{accountId}/summary")
public class SummaryController {
    private final SummaryService summaryService;

    public SummaryController(SummaryService summaryService)
    {
        this.summaryService = summaryService;
    }

    @GetMapping
    public ResponseEntity<SummaryResponse> getSummary(@PathVariable Long accountId)
    {
        return ResponseEntity.ok(summaryService.getSummary(accountId));
    }

}
