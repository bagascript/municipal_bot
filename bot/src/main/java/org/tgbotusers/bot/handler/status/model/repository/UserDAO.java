package org.tgbotusers.bot.handler.status.model.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.tgbotusers.bot.handler.status.model.entity.User;

@Repository
public interface UserDAO extends JpaRepository<User, Integer>
{
    @Transactional
    @Query(value = "SELECT case WHEN count(telegram_bot.user_data.chat_id)>0 THEN true else false end " +
            "FROM telegram_bot.user_data WHERE telegram_bot.user_data.chat_id=:chat_id", nativeQuery = true)
    long checkUserChatId(@Param("chat_id") long chatId);

    @Transactional
    @Query(value = "SELECT t.id FROM telegram_bot.user_data t WHERE t.chat_id = :chat_id", nativeQuery = true)
    int getUserId(@Param("chat_id") long chatId);

    @Transactional
    @Query(value = "SELECT COALESCE(MAX(telegram_bot.user_data.id), 0) FROM telegram_bot.user_data", nativeQuery = true)
    int getLastUserId();

    @Transactional
    @Modifying
    @Query(value = "UPDATE user_data t SET t.email = :email, t.full_name = :full_name, " +
            "t.phone_number = :phone_number WHERE t.id = :id", nativeQuery = true)
    void updateData(@Param("id") int id, @Param("email") String email, @Param("full_name") String fullName,
                    @Param("phone_number") String phoneNumber);
}
