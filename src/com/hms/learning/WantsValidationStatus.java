package com.hms.learning;
public interface WantsValidationStatus {
    void validateFailed();  // Called when a component has failed validation.
    void validatePassed();  // Called when a component has passed validation.
}