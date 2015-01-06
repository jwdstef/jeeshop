package net.jeeshop.services.manage.comment.impl;import net.jeeshop.core.ServersManager;import net.jeeshop.services.manage.comment.CommentService;import net.jeeshop.services.manage.comment.bean.Comment;import net.jeeshop.services.manage.comment.dao.CommentDao;import org.apache.commons.lang.StringUtils;public class CommentServiceImpl extends ServersManager<Comment> implements		CommentService {	private CommentDao commentDao;	public void setCommentDao(CommentDao commentDao) {		this.commentDao = commentDao;	}	@Override	public void updateStatus(String[] ids, String status) {		if (ids == null || ids.length == 0) {			throw new NullPointerException("评论ID不能为空！");		}		for (int i = 0; i < ids.length; i++) {			if(StringUtils.isBlank(ids[i])){				throw new NullPointerException("评论ID不能为空！");			}						Comment c = new Comment();			c.setId(ids[i]);			c.setStatus(status);			commentDao.updateStatus(c);		}			}	@Override	public int selectNotReplyCount() {		return commentDao.selectNotReplyCount();	}}