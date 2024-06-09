package chap07.debit_registerer.repository;

import chap07.debit_registerer.domain.AutoDebitInfo;

public interface AutoDebitInfoRepository {
    void save(AutoDebitInfo info);

    AutoDebitInfo findOne(String userId);
}
