package com.craft.reviewable.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Entity
public class Product {

	@Id
	@GeneratedValue
	private String id;
	private String name;
	private double averageRating;
	private List<Review> reviews;
	private int oneStar;
	private int twoStar;
	private int threeStar;
	private int fourStar;
	private int fiveStar;

	public Product() {
		super();
	}

	public Product(String id, String name, double averageRating, List<Review> reviews, int oneStar, int twoStar,
			int threeStar, int fourStar, int fiveStar) {
		super();
		this.id = id;
		this.name = name;
		this.averageRating = averageRating;
		this.reviews = reviews;
		this.oneStar = oneStar;
		this.twoStar = twoStar;
		this.threeStar = threeStar;
		this.fourStar = fourStar;
		this.fiveStar = fiveStar;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public int getOneStar() {
		return oneStar;
	}

	public void setOneStar(int oneStar) {
		this.oneStar = oneStar;
	}

	public int getTwoStar() {
		return twoStar;
	}

	public void setTwoStar(int twoStar) {
		this.twoStar = twoStar;
	}

	public int getThreeStar() {
		return threeStar;
	}

	public void setThreeStar(int threeStar) {
		this.threeStar = threeStar;
	}

	public int getFourStar() {
		return fourStar;
	}

	public void setFourStar(int fourStar) {
		this.fourStar = fourStar;
	}

	public int getFiveStar() {
		return fiveStar;
	}

	public void setFiveStar(int fiveStar) {
		this.fiveStar = fiveStar;
	}

}
