package com.tenprj.multimoduleadminboard.dto.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 어드민 프로젝트 전용 프로퍼티
 *
 * @param board 게시판 관련 프로퍼티
 *          하단의 param3개 이어서 보면 application.yml에 작성한 프러퍼티 project.board.url 에 접근하게 작성됨
 */
@ConfigurationProperties("project")
public record ProjectProperties(Board board) {

    /**
     * 게시판 관련 프로퍼티
     *
     * @param url 게시판 서비스 호스트명
     */
    public record Board(String url) {}
}
