package com.innoq.mploed.ddd.application.integration;

import com.innoq.mploed.ddd.application.domain.CreditApplicationForm;
import com.innoq.mploed.ddd.application.domain.Customer;
import com.innoq.mploed.ddd.scoring.shared.ScoringInput;
import com.innoq.mploed.ddd.scoring.shared.ScoringResult;
import com.innoq.mploed.ddd.scoring.shared.ScoringService;
import org.springframework.stereotype.Service;

@Service
public class ScoringClient {
    private ScoringService scoringService;

    public ScoringClient(ScoringService scoringService) {
        this.scoringService = scoringService;

    }

    public ScoringResult performScoring(CreditApplicationForm creditApplicationForm, Customer customer) {
        ScoringInput scoringInput = new ScoringInput();
        scoringInput.setIncome(creditApplicationForm.getSelfDisclosure().getEarnings().sum());
        scoringInput.setSpendings(creditApplicationForm.getSelfDisclosure().getOutgoings().sum());
        scoringInput.setReason(creditApplicationForm.getPurpose());
        scoringInput.setMonthlyPayment(creditApplicationForm.getMonthlyPayment().longValue());
        scoringInput.setFirstName(customer.getFirstName());
        scoringInput.setLastName(customer.getLastName());
        scoringInput.setStreet(customer.getStreet());
        scoringInput.setPostCode(customer.getPostCode());
        return scoringService.performScoring(scoringInput);
    }

}
