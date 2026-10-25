package com.project.discussion.controller;

import com.project.discussion.dto.CreateDiscussionRequest;
import com.project.discussion.dto.InvitationCreateRequest;
import com.project.discussion.dto.MemberRequest;
import com.project.discussion.dto.UpdateDiscussionRequest;
import com.project.discussion.entity.DiscussionMembers;
import com.project.discussion.service.DiscussionMemberService;
import com.project.discussion.service.DiscussionService;
import com.project.discussion.service.InvitationService;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/discussions")
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
    public void createDiscussion(@RequestBody CreateDiscussionRequest request) {
        discussionService.createDiscussion(request);
    }

    @GetMapping("/{id}")
    public Object getDiscussion(@PathVariable Long id) {
        return discussionService.getById(id);
    }

    @PutMapping("/{id}")
    public void updateDiscussion(@PathVariable Long id,
                                 @RequestBody UpdateDiscussionRequest request) {
        discussionService.updateDiscussion(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteDiscussion(@PathVariable Long id) {
        discussionService.deleteDiscussion(id);
    }

    @PostMapping("/{id}/members")
    public void addMember(@PathVariable Long id,
                          @RequestBody MemberRequest request) {
        memberService.addMember(id, request);
    }

    @GetMapping("/{id}/members")
    public List<DiscussionMembers> getMembers(@PathVariable Long id) {
        return memberService.getMembers(id);
    }

    @DeleteMapping("/{id}/members/{userId}")
    public void removeMember(@PathVariable Long id,
                             @PathVariable String userId) {
        memberService.removeMember(id, userId);
    }

    @PostMapping("/{id}/invitations")
    public void createInvitation(@PathVariable Long id,
                                 @RequestBody InvitationCreateRequest request) {
        invitationService.createInvitation(id, request);
    }
}
