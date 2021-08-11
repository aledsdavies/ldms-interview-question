package com.aledsdavies.ldms.contollers;

import com.aledsdavies.ldms.models.PaymentPlan;
import com.aledsdavies.ldms.models.RepaymentSchedule;
import com.aledsdavies.ldms.service.interfaces.RepaymentScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
class PaymentPlanController {

    /*
        I am using the interface here instead of the implementation as this allow me to mock the service for unit testing
        the controller without having to have access to the data layer or other integrations which  may be required in
        future iterations.
     */
    private final RepaymentScheduleService repaymentScheduleService;

    @GetMapping()
    public List<PaymentPlan> getAll() {
        return this.repaymentScheduleService.getAll();
    }

    @GetMapping("/{id}")
    public RepaymentSchedule getRepaymentSchedule(@PathVariable String id) {
        return this.repaymentScheduleService.get(id);
    }

    @PostMapping()
    public void create(@RequestBody PaymentPlan paymentSchedule) {
        this.repaymentScheduleService.create(paymentSchedule);
    }

    @PutMapping()
    public void update(@RequestBody PaymentPlan paymentSchedule) {
        this.repaymentScheduleService.update(paymentSchedule);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        this.repaymentScheduleService.delete(id);
    }

    /*
     * This allows me to format validation errors and return them directly to the user. I have added it in this class
     * but generally this could be added into a larger exception handler component which is where you can handle all
     * you errors.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }

}


