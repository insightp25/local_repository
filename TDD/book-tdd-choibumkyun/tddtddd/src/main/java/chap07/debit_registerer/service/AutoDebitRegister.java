package chap07.debit_registerer.service;

import chap07.debit_registerer.domain.AutoDebitInfo;
import chap07.debit_registerer.repository.AutoDebitInfoRepository;
import chap07.debit_registerer.util.CardValidity;
import chap07.debit_registerer.validation.StubCardNumberValidator;
import chap07.debit_registerer.domain.AutoDebitReq;
import chap07.debit_registerer.domain.RegisterResult;
import java.time.LocalDateTime;

public class AutoDebitRegister {
    private StubCardNumberValidator validator;
    private AutoDebitInfoRepository repository;

    public AutoDebitRegister(StubCardNumberValidator validator,
        AutoDebitInfoRepository repository) {
        this.validator = validator;
        this.repository = repository;
    }

    public RegisterResult register(AutoDebitReq req) {
        CardValidity validity = validator.validate(req.getCardNumber());

        if (validity == CardValidity.THEFT) {
            return RegisterResult.theft(validity);
        }

        if (validity == CardValidity.INVALID) {
            return RegisterResult.invalid(validity);
        }

        if (validity != CardValidity.VALID) {
            return RegisterResult.error(validity);
        }

        AutoDebitInfo info = repository.findOne(req.getUserId());

        if (info != null) {
            info.changeCardNumber(req.getCardNumber());
        } else {
            AutoDebitInfo newInfo = new AutoDebitInfo(
                req.getUserId(), req.getCardNumber(), LocalDateTime.now());

            repository.save(newInfo);
        }

        return RegisterResult.success();
    }
}
