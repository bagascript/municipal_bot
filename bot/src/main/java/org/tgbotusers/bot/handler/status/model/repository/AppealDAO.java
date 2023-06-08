package org.tgbotusers.bot.handler.status.model.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.tgbotusers.bot.handler.status.model.entity.Appeal;

@Repository
public interface AppealDAO extends JpaRepository<Appeal, Integer>
{
    @Transactional
    @Query(value = "SELECT case WHEN count(telegram_bot.appeal_data.number)>0 THEN true else false end " +
            "FROM telegram_bot.appeal_data WHERE telegram_bot.appeal_data.number=:number", nativeQuery = true)
    long checkAppealUniqueNumber(@Param("number") long appealNumber);

    @Transactional
    @Query(value = "SELECT COALESCE(MAX(telegram_bot.appeal_data.id), 0) " +
            "FROM telegram_bot.appeal_data", nativeQuery = true)
    int getLastAppealId();

    @Query(value = "SELECT t.id FROM telegram_bot.appeal_data t WHERE t.number = :number", nativeQuery = true)
    int getAppealId(@Param("number") long appealNumber);

    @Transactional
    @Modifying
    @Query(value = "UPDATE appeal_data a SET a.text = :text, a.address = :address," +
            "a.status_time = now() WHERE a.id = :id", nativeQuery = true)
    void updateAppealData(@Param("id") int id, @Param("text") String appealText,
                    @Param("address") String address);

    @Query(value = "SELECT t.number FROM telegram_bot.appeal_data t WHERE t.id = :id", nativeQuery = true)
    long getUserAppealNumber(int id);
}
