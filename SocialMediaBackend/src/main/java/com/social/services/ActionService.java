package com.social.services;

import com.social.dto.request.CommentActionRequest;
import com.social.dto.request.PostActionRequest;
import com.social.dto.request.ReplyActionRequest;
import com.social.pojo.CommentAction;
import com.social.pojo.PostAction;
import com.social.pojo.SubCommentAction;

/**
 *
 * @author LENOVO
 */
public interface ActionService {

    CommentAction saveOrUpdateOrDelete(CommentActionRequest request);

    PostAction saveOrUpdateOrDelete(PostActionRequest request);

    SubCommentAction saveOrUpdateOrDelete(ReplyActionRequest request);
}
