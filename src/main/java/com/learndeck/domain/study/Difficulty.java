package com.learndeck.domain.study;

public enum Difficulty {

    HARD(1L), MEDIUM(2L), EASY(3L), VERY_EASY(4L);

    long value;

    Difficulty(Long value) {
        this.value = value;
    }

}
