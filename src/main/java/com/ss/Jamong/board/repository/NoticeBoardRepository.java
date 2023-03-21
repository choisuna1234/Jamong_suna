package com.ss.Jamong.board.repository;

import com.ss.Jamong.board.entity.NoticeBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeBoardRepository extends JpaRepository<NoticeBoard, Long> {
    List<NoticeBoard> findAllByOrderByIdxDesc();
    Page<NoticeBoard> findByTitleContainingOrContentContaining(String searchText, String searchText1, Pageable pageable);
}
