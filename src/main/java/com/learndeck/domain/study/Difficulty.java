package com.learndeck.domain.study;

public enum Difficulty {
    HARD(Long.valueOf(1)), MEDIUM(Long.valueOf(2)), EASY(Long.valueOf(3)), VERY_EASY(Long.valueOf(4));

    long value;

    Difficulty(Long value) {
        this.value = value;
    }


}
