package car_service.service;

import car_service.data.entity.History;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface HistoryService {
    List<History> getHistory();

    History getHistory(long id);

    History createHistory(History history);

    History updateHistory(History history, long id);

    void deleteHistory(long id);

    //All Toma
    List<History> findByIsPaidOrderByDateOfRepairDesc(boolean isPaid);

    List<History> findByIdHistoryBetweenAndIsPaidFalse(long firstId, long secondId);

    BigDecimal findFinalPriceByBrand(long idHistory);

    List<History> findByDateOfRepairNotLike(LocalDate dateOfRepair);

    List<History> getHistoriesByCustomer(long id);

    List<History> getHistoriesByEmployee(long id);

    Set<History> getHistoriesByAutoService(long id);
}