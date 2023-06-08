package org.tgbotusers.bot.handler.status.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tgbotusers.bot.handler.status.model.entity.File;

public interface FileDAO extends JpaRepository<File, Integer> {
}
