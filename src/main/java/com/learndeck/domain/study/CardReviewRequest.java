package com.learndeck.domain.study;

public class CardReviewRequest {

    private int dateModifier;
    private String nextReview;
    private String buttonStats;

    public void setDateModifier(int dateModifier) {
        this.dateModifier = dateModifier;
    }

    public void setNextReview(String nextReview) {
        this.nextReview = nextReview;
    }

    public void setButtonStats(String buttonStats) {this.buttonStats = buttonStats; }

    public String getButtonStats() { return buttonStats; }

    public String getNextReview() {
        return nextReview;
    }

    public int getDateModifier() {
        return dateModifier;
    }
}
