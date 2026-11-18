package com.project.discussion.controller;

import com.project.discussion.dto.CreateDiscussionRequest;
import com.project.discussion.dto.InvitationCreateRequest;
import com.project.discussion.dto.MemberRequest;
import com.project.discussion.dto.UpdateDiscussionRequest;
import com.project.discussion.entity.DiscussionMembers;
import com.project.discussion.entity.Discussions;
import com.project.discussion.entity.Invitation;
import com.project.discussion.service.DiscussionMemberService;
import com.project.discussion.service.DiscussionService;
import com.project.discussion.service.InvitationService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/discussions")
@Validated
public class DiscussionController {

    private final DiscussionService discussionService;
    private final DiscussionMemberService memberService;
    private final InvitationService invitationService;

    public DiscussionController(DiscussionService discussionService,
                                DiscussionMemberService memberService,
                                InvitationService invitationService) {
        this.discussionService = discussionService;
        this.memberService = memberService;
        this.invitationService = invitationService;
    }

    @PostMapping
    public ResponseEntity<Discussions> createDiscussion( @Valid @RequestBody CreateDiscussionRequest request) {
        Discussions created = discussionService.createDiscussion(request);
        return ResponseEntity.created(URI.create("/api/discussions/" + created.getId()))
                .body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Discussions> getDiscussion(@PathVariable Long id) {
        return ResponseEntity.ok(discussionService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Discussions> updateDiscussion(@PathVariable Long id,
                                                        @RequestBody UpdateDiscussionRequest request) {
        return ResponseEntity.ok(discussionService.updateDiscussion(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiscussion(@PathVariable Long id) {
        discussionService.deleteDiscussion(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/members")
    public ResponseEntity<Void> addMember(@PathVariable Long id,
                                          @RequestBody MemberRequest request) {
        memberService.addMember(id, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/members")
    public ResponseEntity<List<DiscussionMembers>> getMembers(@PathVariable Long id) {
        return ResponseEntity.ok(memberService.getMembers(id));
    }

    @DeleteMapping("/{id}/members/{userId}")
    public ResponseEntity<Void> removeMember(@PathVariable Long id,
                                             @PathVariable String userId) {
        memberService.removeMember(id, userId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/invitations")
    public ResponseEntity<Invitation> createInvitation(@PathVariable Long id,
                                                       @RequestBody InvitationCreateRequest request) {
        Invitation created = invitationService.createInvitation(id, request);
        return ResponseEntity.created(URI.create("/api/discussions/" + id + "/invitations/" + created.getId()))
                .body(created);
    }
}
