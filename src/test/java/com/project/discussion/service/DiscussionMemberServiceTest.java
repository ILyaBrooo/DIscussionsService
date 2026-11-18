package com.project.discussion.service;

import com.project.discussion.dto.MemberRequest;
import com.project.discussion.entity.DiscussionMembers;
import com.project.discussion.repository.DiscussionMemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DiscussionMemberServiceTest {

    @Mock
    private DiscussionMemberRepository repository;

    @InjectMocks
    private DiscussionMemberService service;

    private DiscussionMembers member(Long discussionId, String userId, String role) {
        DiscussionMembers m = new DiscussionMembers();
        m.setDiscussionId(discussionId);
        m.setUserId(userId);
        m.setRole(role);
        return m;
    }

    @Test
    void addMember_savesNewMemberWithDefaultRole() {
        when(repository.existsByDiscussionIdAndUserId(1L, "5")).thenReturn(false);

        MemberRequest request = new MemberRequest();
        request.setUserId("5");
        request.setRole(null); // роль не передали — должна подставиться MEMBER

        service.addMember(1L, request);

        ArgumentCaptor<DiscussionMembers> captor = ArgumentCaptor.forClass(DiscussionMembers.class);
        verify(repository).save(captor.capture());
        DiscussionMembers saved = captor.getValue();
        assertEquals("5", saved.getUserId());
        assertEquals("MEMBER", saved.getRole());
    }

    @Test
    void addMember_duplicate_throws() {
        when(repository.existsByDiscussionIdAndUserId(1L, "5")).thenReturn(true);

        MemberRequest request = new MemberRequest();
        request.setUserId("5");

        assertThrows(IllegalStateException.class, () -> service.addMember(1L, request));
        verify(repository, never()).save(any());
    }

    @Test
    void addMember_nullUserId_throws() {
        MemberRequest request = new MemberRequest();
        request.setUserId(null);

        assertThrows(IllegalArgumentException.class, () -> service.addMember(1L, request));
    }

    @Test
    void isMember_returnsRepositoryResult() {
        when(repository.existsByDiscussionIdAndUserId(1L, "5")).thenReturn(true);
        assertTrue(service.isMember(1L, "5"));

        when(repository.existsByDiscussionIdAndUserId(1L, "6")).thenReturn(false);
        assertFalse(service.isMember(1L, "6"));
    }

    @Test
    void isAdmin_trueForAdmin() {
        when(repository.findByDiscussionId(1L))
                .thenReturn(List.of(member(1L, "5", "ADMIN")));

        assertTrue(service.isAdmin(1L, "5"));
    }

    @Test
    void isAdmin_falseForRegularMember() {
        when(repository.findByDiscussionId(1L))
                .thenReturn(List.of(member(1L, "5", "MEMBER")));

        assertFalse(service.isAdmin(1L, "5"));
        assertFalse(service.isAdmin(1L, "42")); // пользователя вообще нет в списке
    }

    @Test
    void removeMember_regularMember_deletes() {
        when(repository.findByDiscussionId(1L))
                .thenReturn(List.of(member(1L, "5", "MEMBER")));
        when(repository.deleteByDiscussionIdAndUserId(1L, "5")).thenReturn(1);

        assertDoesNotThrow(() -> service.removeMember(1L, "5"));
        verify(repository).deleteByDiscussionIdAndUserId(1L, "5");
    }

    @Test
    void removeMember_lastAdmin_throws() {
        // в обсуждении один-единственный участник — и он админ
        when(repository.findByDiscussionId(1L))
                .thenReturn(List.of(member(1L, "5", "ADMIN")));

        IllegalStateException ex = assertThrows(IllegalStateException.class,
                () -> service.removeMember(1L, "5"));
        assertTrue(ex.getMessage().contains("last administrator"));
        verify(repository, never()).deleteByDiscussionIdAndUserId(any(), any());
    }

    @Test
    void removeMember_adminButOthersExist_deletes() {
        // админ уходит, но есть второй админ — удалять можно
        when(repository.findByDiscussionId(1L))
                .thenReturn(List.of(member(1L, "5", "ADMIN"), member(1L, "6", "ADMIN")));
        when(repository.deleteByDiscussionIdAndUserId(1L, "5")).thenReturn(1);

        assertDoesNotThrow(() -> service.removeMember(1L, "5"));
        verify(repository).deleteByDiscussionIdAndUserId(1L, "5");
    }

    @Test
    void removeMember_notFound_throws() {
        when(repository.findByDiscussionId(1L)).thenReturn(List.of());
        when(repository.deleteByDiscussionIdAndUserId(1L, "99")).thenReturn(0);

        assertThrows(IllegalStateException.class, () -> service.removeMember(1L, "99"));
    }

    @Test
    void getMembers_returnsList() {
        List<DiscussionMembers> expected = List.of(member(1L, "5", "ADMIN"));
        when(repository.findByDiscussionId(1L)).thenReturn(expected);

        assertEquals(expected, service.getMembers(1L));
    }
}
