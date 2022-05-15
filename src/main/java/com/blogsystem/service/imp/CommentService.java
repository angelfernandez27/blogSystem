package com.blogsystem.service.imp;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.blogsystem.dto.CommentDTO;
import com.blogsystem.exception.BlogAppException;
import com.blogsystem.exception.ResourceNotFoundException;
import com.blogsystem.persistence.entity.Comment;
import com.blogsystem.persistence.entity.Publication;
import com.blogsystem.persistence.repository.ICommentRepository;
import com.blogsystem.persistence.repository.IPublicatinRepository;
import com.blogsystem.service.ICommentService;

@Service
public class CommentService implements ICommentService{
	
	@Autowired
	private ICommentRepository commentRepository;
	
	@Autowired
	private IPublicatinRepository publicatinRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public List<CommentDTO> getCommentByPublicationId(Long publicationId) {
		List<Comment> comments=commentRepository.findByPublicationId(publicationId);
		return comments.stream().map(coomment-> mapperDTO(coomment)).collect(Collectors.toList());
	}
	@Override
	public CommentDTO getCommentById(Long publicationId, Long commentId) {
		Publication publication=publicatinRepository.findById(publicationId)
				.orElseThrow(()-> new ResourceNotFoundException("Publication", "id", publicationId));
		
		Comment comment=commentRepository.findById(commentId)
				.orElseThrow(()->new ResourceNotFoundException("Comment", "id", commentId));
		
		if(!comment.getPublication().getId().equals(publication.getId())) {
			throw new BlogAppException(HttpStatus.BAD_REQUEST, "The comment does not belong to the publication");
		}
		return mapperDTO(comment);
	}

	@Override
	public CommentDTO create(Long publicationId, CommentDTO commentDTO) {
		Comment comment=mapperComment(commentDTO);
		Publication publication=publicatinRepository.findById(publicationId)
				.orElseThrow(()-> new ResourceNotFoundException("Publication", "id", publicationId));
		comment.setPublication(publication);
		Comment newComment= commentRepository.save(comment);
		return mapperDTO(newComment);
	}
	@Override
	public CommentDTO updateComment(Long publicationId, Long commentId, CommentDTO commentDTO) {
		Publication publication=publicatinRepository.findById(publicationId)
				.orElseThrow(()-> new ResourceNotFoundException("Publication", "id", publicationId));
		
		Comment comment=commentRepository.findById(commentId)
				.orElseThrow(()->new ResourceNotFoundException("Comment", "id", commentId));
		
		if(!comment.getPublication().getId().equals(publication.getId())) {
			throw new BlogAppException(HttpStatus.BAD_REQUEST, "The comment does not belong to the publication");
		}
		comment.setName(commentDTO.getName());
		comment.setBody(commentDTO.getBody());
		comment.setEmail(commentDTO.getEmail());
		
		Comment commentUpdated=commentRepository.save(comment);
		return mapperDTO(commentUpdated);
	}
	@Override
	public void deleteById(Long publicationId, Long commentId) {
		Publication publication=publicatinRepository.findById(publicationId)
				.orElseThrow(()-> new ResourceNotFoundException("Publication", "id", publicationId));
		
		Comment comment=commentRepository.findById(commentId)
				.orElseThrow(()->new ResourceNotFoundException("Comment", "id", commentId));
		
		if(!comment.getPublication().getId().equals(publication.getId())) {
			throw new BlogAppException(HttpStatus.BAD_REQUEST, "The comment does not belong to the publication");
		}
		
		commentRepository.deleteById(commentId);
		
	}
	
	
	private CommentDTO mapperDTO(Comment comment) {
		CommentDTO commentDTO=mapper.map(comment, CommentDTO.class);
		
		return commentDTO;
	}
	
	private Comment mapperComment(CommentDTO commentDTO) {
		Comment comment=mapper.map(commentDTO, Comment.class);
		return comment;
	}
	
	
	

	

	

}
