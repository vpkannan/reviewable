package com.craft.reviewable.domain;

import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Entity
public class Review {

	@Id
	@GeneratedValue
	private String id;
	private int rating;
	private String reviewTitle;
	private String reviewText;
	private String userId;
	private OffsetDateTime date;

	public Review() {
		super();
	}

	public Review(String id, int rating, String reviewTitle, String reviewText, String userId, OffsetDateTime date) {
		super();
		this.id = id;
		this.rating = rating;
		this.reviewTitle = reviewTitle;
		this.reviewText = reviewText;
		this.userId = userId;
		this.date = date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getReviewTitle() {
		return reviewTitle;
	}

	public void setReviewTitle(String reviewTitle) {
		this.reviewTitle = reviewTitle;
	}

	public String getReviewText() {
		return reviewText;
	}

	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public OffsetDateTime getDate() {
		return date;
	}

	public void setDate(OffsetDateTime date) {
		this.date = date;
	}

}
