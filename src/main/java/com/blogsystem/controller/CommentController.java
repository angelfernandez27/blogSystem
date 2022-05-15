package com.blogsystem.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogsystem.dto.CommentDTO;
import com.blogsystem.persistence.entity.Comment;
import com.blogsystem.service.imp.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@GetMapping("/publications/{publicationId}/comments")
	public ResponseEntity<List<CommentDTO>> findCommentsByPublicationId(@PathVariable(value="publicationId") Long publicationId){
		return new ResponseEntity<>(commentService.getCommentByPublicationId(publicationId),HttpStatus.OK);
	}
	
	@GetMapping("/publications/{publicationId}/comments/{commentId}")
	public ResponseEntity<CommentDTO> getCommentById(@PathVariable(value="publicationId") Long publicationId,@PathVariable(value="commentId") Long commentId){
		CommentDTO commentDTO=commentService.getCommentById(publicationId, commentId);
		return new ResponseEntity<>(commentDTO,HttpStatus.OK);
		
	}
	
	@PutMapping("/publications/{publicationId}/comments/{commentId}")
	public ResponseEntity<CommentDTO> updateById(@PathVariable(value="publicationId") Long publicationId, @PathVariable(value="commentId") Long commentId, @Valid  @RequestBody CommentDTO commentDTO){
		return new ResponseEntity<>(commentService.updateComment(publicationId, commentId, commentDTO),HttpStatus.OK);
	}
	
	@PostMapping("/publications/{publicationId}/comments")
	public ResponseEntity<CommentDTO> save(@PathVariable(value="publicationId") Long publicationId, @Valid @RequestBody CommentDTO commentDTO){
		return new  ResponseEntity<>(commentService.create(publicationId, commentDTO),HttpStatus.CREATED);
	}
	
	@DeleteMapping("/publications/{publicationId}/comments/{commentId}")
	public ResponseEntity<?> deleteById(@PathVariable(value="publicationId") Long publicationId, @PathVariable(value="commentId") Long commentId){
		commentService.deleteById(publicationId, commentId);
		return ResponseEntity.ok("Comment Deleted");
		
	}
}
