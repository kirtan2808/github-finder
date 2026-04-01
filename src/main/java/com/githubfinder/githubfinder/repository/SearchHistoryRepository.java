package com.githubfinder.githubfinder.repository;

import com.githubfinder.githubfinder.entity.SearchHistory;
import org.springframework.data.jpa.repository.*;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface SearchHistoryRepository extends JpaRepository<SearchHistory, Integer> {

    List<SearchHistory> findTop5ByUsernameOrderByIdDesc(String username);

    // 🔥 FIXED DELETE
    @Transactional
    @Modifying
    @Query("DELETE FROM SearchHistory s WHERE s.username = :username")
    void deleteAllByUsername(String username);
}