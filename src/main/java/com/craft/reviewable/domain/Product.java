package com.craft.reviewable.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;

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

		double newAverageRating = 0.0;
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
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(averageRating);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + fiveStar;
		result = prime * result + fourStar;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + oneStar;
		result = prime * result + threeStar;
		result = prime * result + twoStar;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (Double.doubleToLongBits(averageRating) != Double.doubleToLongBits(other.averageRating))
			return false;
		if (fiveStar != other.fiveStar)
			return false;
		if (fourStar != other.fourStar)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (oneStar != other.oneStar)
			return false;
		if (threeStar != other.threeStar)
			return false;
		if (twoStar != other.twoStar)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", averageRating=" + averageRating + ", oneStar=" + oneStar
				+ ", twoStar=" + twoStar + ", threeStar=" + threeStar + ", fourStar=" + fourStar + ", fiveStar="
				+ fiveStar + "]";
	}

}
