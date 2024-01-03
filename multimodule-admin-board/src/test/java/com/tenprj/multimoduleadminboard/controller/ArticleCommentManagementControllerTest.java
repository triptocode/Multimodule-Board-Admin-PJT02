//package com.tenprj.multimoduleadminboard.controller;
//
//import com.tenprj.multimoduleadminboard.config.SecurityConfig;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.context.annotation.Import;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@DisplayName("View 컨트롤러 - 댓글 관리")
//@Import(SecurityConfig.class)
//@WebMvcTest(ArticleCommnetManagementController.class)
//class ArticleCommnetManagementControllerTest {
//
//    private final MockMvc mvc;
//
//    public ArticleCommnetManagementControllerTest(@Autowired MockMvc mvc) {
//        this.mvc = mvc;
//    }
//
//    @DisplayName("[view][GET] 댓글 관리 페이지 - 정상 호출")
//    @Test
//    void givenNothing_whenRequestingArticleCommentManagementView_thenReturnsArticleCommentManagementView() throws Exception {
//        // Given
//
//        // When & Then
//        mvc.perform(get("/management/article-comments"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
//                .andExpect(view().name("management/article-comments"));
////                .andExpect(view().name("management/articleComments"));
//    }
//
//}







package com.tenprj.multimoduleadminboard.controller;

//import com.tenprj.multimoduleadminboard.config.GlobalControllerConfig;
import com.tenprj.multimoduleadminboard.config.GlobalControllerConfig;
import com.tenprj.multimoduleadminboard.config.SecurityConfig;
//import com.tenprj.multimoduleadminboard.config.TestSecurityConfig;
import com.tenprj.multimoduleadminboard.config.TestSecurityConfig;
import com.tenprj.multimoduleadminboard.domain.constant.RoleType;
import com.tenprj.multimoduleadminboard.dto.ArticleCommentDto;
import com.tenprj.multimoduleadminboard.dto.UserAccountDto;
import com.tenprj.multimoduleadminboard.service.ArticleCommentManagementService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.mockito.BDDMockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


//@Import(SecurityConfig.class)
@DisplayName("컨트롤러 - 댓글 관리")
@Import({TestSecurityConfig.class, GlobalControllerConfig.class})
@WebMvcTest(ArticleCommentManagementControllerTest.class)
class ArticleCommentManagementControllerTest {

    private final MockMvc mvc;

    @MockBean private ArticleCommentManagementService articleCommentManagementService;

    public ArticleCommentManagementControllerTest(@Autowired MockMvc mvc) {

        this.mvc = mvc;
    }

    @WithMockUser(username = "tester", roles = "USER")
    @DisplayName("[view][GET] 댓글 관리 페이지 - 정상 호출")
    @Test
    void givenNothing_whenRequestingArticleCommentManagementView_thenReturnsArticleCommentManagementView() throws Exception {
        // Given
        given(articleCommentManagementService.getArticleComments()).willReturn(List.of());

        // When & Then
        mvc.perform(get("/management/article-comments"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("management/article-comments"))
                .andExpect(model().attribute("comments", List.of()));
        then(articleCommentManagementService).should().getArticleComments();
    }

    @WithMockUser(username = "tester", roles = "USER")
    @DisplayName("[data][GET] 댓글 1개 - 정상 호출")
    @Test
    void givenCommentId_whenRequestingArticleComment_thenReturnsArticleComment() throws Exception {
        // Given
        Long articleCommentId = 1L;
        ArticleCommentDto articleCommentDto = createArticleCommentDto("comment");
        given(articleCommentManagementService.getArticleComment(articleCommentId)).willReturn(articleCommentDto);

        // When & Then
        mvc.perform(get("/management/article-comments/" + articleCommentId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(articleCommentId))
                .andExpect(jsonPath("$.content").value(articleCommentDto.content()))
                .andExpect(jsonPath("$.userAccount.nickname").value(articleCommentDto.userAccount().nickname()));
        then(articleCommentManagementService).should().getArticleComment(articleCommentId);
    }

    @WithMockUser(username = "tester", roles = "MANAGER")
    @DisplayName("[view][POST] 댓글 삭제 - 정상 호출")
    @Test
    void givenCommentId_whenRequestingDeletion_thenRedirectsToArticleCommentManagementView() throws Exception {
        // Given
        Long articleCommentId = 1L;
        willDoNothing().given(articleCommentManagementService).deleteArticleComment(articleCommentId);

        // When & Then
        mvc.perform(
                post("/management/article-comments/" + articleCommentId)
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/management/article-comments"))
                .andExpect(redirectedUrl("/management/article-comments"));
        then(articleCommentManagementService).should().deleteArticleComment(articleCommentId);
    }


    private ArticleCommentDto createArticleCommentDto(String content) {
        return ArticleCommentDto.of(
                1L,
                1L,
                createUserAccountDto(),
                null,
                content,
                LocalDateTime.now(),
                "Uno",
                LocalDateTime.now(),
                "Uno"
        );
    }

    private UserAccountDto createUserAccountDto() {
        return UserAccountDto.of(
                "unoTest",
                "uno-test@email.com",
                "uno-test",
                "test memo"
        );
    }


}
