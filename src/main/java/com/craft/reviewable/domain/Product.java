package com.craft.reviewable.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.apache.commons.lang.builder.EqualsBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * The Product entity
 * 
 * @author Vignesh
 *
 */
@Entity
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Product {

	@Id
	private String id;
	private String name;
	private double averageRating;
	private int oneStar;
	private int twoStar;
	private int threeStar;
	private int fourStar;
	private int fiveStar;

	public Product() {
		super();
	}

	public Product(String id, String name, double averageRating, int oneStar, int twoStar, int threeStar, int fourStar,
			int fiveStar) {
		super();
		this.id = id;
		this.name = name;
		this.averageRating = averageRating;
		this.oneStar = oneStar;
		this.twoStar = twoStar;
		this.threeStar = threeStar;
		this.fourStar = fourStar;
		this.fiveStar = fiveStar;
	}

	/**
	 * Calculate the average rating for the product when a new review is added,
	 * based on the number of one, two, three, four and five star reviews
	 * 
	 * @param rating
	 *            The rating of the new review added to the product
	 * @return double value representing the new average rating for the product
	 */
	public double calculateNewAverageRating(int rating) {

		if (rating == 1)
			this.setOneStar(this.getOneStar() + 1);
		else if (rating == 2)
			this.setTwoStar(this.getTwoStar() + 1);
		else if (rating == 3)
			this.setThreeStar(this.getThreeStar() + 1);
		else if (rating == 4)
			this.setFourStar(this.getFourStar() + 1);
		else
			this.setFiveStar(this.getFiveStar() + 1);

		double newAverageRating;
		newAverageRating = (1.0 * oneStar + 2.0 * twoStar + 3.0 * threeStar + 4.0 * fourStar + 5.0 * fiveStar)
				/ ((double) (oneStar + twoStar + threeStar + fourStar + fiveStar));

		return Math.round(newAverageRating * 10.0) / 10.0;
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

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(averageRating);
		result = PRIME * result + (int) (temp ^ (temp >>> 32));
		result = PRIME * result + fiveStar;
		result = PRIME * result + fourStar;
		result = PRIME * result + ((id == null) ? 0 : id.hashCode());
		result = PRIME * result + ((name == null) ? 0 : name.hashCode());
		result = PRIME * result + oneStar;
		result = PRIME * result + threeStar;
		result = PRIME * result + twoStar;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Product))
			return false;
		Product p = (Product) obj;
		return new EqualsBuilder().append(id, p.id).append(name, p.name).append(averageRating, p.averageRating)
				.append(oneStar, p.oneStar).append(twoStar, p.twoStar).append(threeStar, p.threeStar)
				.append(fourStar, p.fourStar).append(fiveStar, p.fiveStar).isEquals();
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", averageRating=" + averageRating + ", oneStar=" + oneStar
				+ ", twoStar=" + twoStar + ", threeStar=" + threeStar + ", fourStar=" + fourStar + ", fiveStar="
				+ fiveStar + "]";
	}

}
