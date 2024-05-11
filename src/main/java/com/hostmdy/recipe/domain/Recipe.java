package com.hostmdy.recipe.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Entity
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	@Lob
	private String description;// large object
	private Double review;

	@Enumerated(EnumType.STRING)
	private Difficulty difficulty;

	private Integer prepTime;
	private Integer cookTime;
	private Integer totalTime;
	private Integer servings;
	private String source;

	@Lob
	private Byte[] image;

	// @Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime createdAt;// TimeStamp
	private LocalDateTime updatedAt;

	// Join with Note
	// One Way
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "note_id")
	private Note note;

	// Join with Ingredient
	@OneToMany(mappedBy = "recipe", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Ingredient> ingredients = new ArrayList<>();
 
	// Join with Direction
	@OneToMany(mappedBy = "recipe", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Direction> directions = new ArrayList<>();

	// Join with Category
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "recipe_category", joinColumns = @JoinColumn(name = "recipe_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	private Set<Category> categories = new HashSet<>();

	public Recipe() {
	}

	@PrePersist
	void onCreate() {
		this.createdAt = LocalDateTime.now();
		this.totalTime = this.cookTime+this.prepTime;
	}

	@PreUpdate
	void onUpdate() {
		this.updatedAt = LocalDateTime.now();
		this.totalTime = this.cookTime+this.prepTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getReview() {
		return review;
	}

	public void setReview(Double review) {
		this.review = review;
	}

	public Difficulty getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	public Integer getPrepTime() {
		return prepTime;
	}

	public void setPrepTime(Integer prepTime) {
		this.prepTime = prepTime;
	}

	public Integer getCookTime() {
		return cookTime;
	}

	public void setCookTime(Integer cookTime) {
		this.cookTime = cookTime;
	}

	public Integer getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(Integer totalTime) {
		this.totalTime = totalTime;
	}

	public Integer getServings() {
		return servings;
	}

	public void setServings(Integer servings) {
		this.servings = servings;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Byte[] getImage() {
		return image;
	}

	public void setImage(Byte[] image) {
		this.image = image;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Note getNote() {
		return note;
	}

	public void setNote(Note note) {
		this.note = note;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public List<Direction> getDirections() {
		return directions;
	}

	public void setDirections(List<Direction> directions) {
		this.directions = directions;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(image);
		result = prime * result + Objects.hash(cookTime, createdAt, description, difficulty, id, name, prepTime, review,
				servings, source, totalTime, updatedAt);
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
		Recipe other = (Recipe) obj;
		return Objects.equals(cookTime, other.cookTime) && Objects.equals(createdAt, other.createdAt)
				&& Objects.equals(description, other.description) && difficulty == other.difficulty
				&& Objects.equals(id, other.id) && Arrays.equals(image, other.image) && Objects.equals(name, other.name)
				&& Objects.equals(prepTime, other.prepTime) && Objects.equals(review, other.review)
				&& Objects.equals(servings, other.servings) && Objects.equals(source, other.source)
				&& Objects.equals(totalTime, other.totalTime) && Objects.equals(updatedAt, other.updatedAt);
	}

	@Override
	public String toString() {
		return "Recipe [id=" + id + ", name=" + name + ", description=" + description + ", review=" + review
				+ ", difficulty=" + difficulty + ", prepTime=" + prepTime + ", cookTime=" + cookTime + ", totalTime="
				+ totalTime + ", servings=" + servings + ", source=" + source + ", image=" + Arrays.toString(image)
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

}
