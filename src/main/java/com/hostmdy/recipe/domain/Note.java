package com.hostmdy.recipe.domain;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Entity
public class Note {
	 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Lob
	private String note;
	
	
	// @Temporal(TemporalType.TIMESTAMP)
		private LocalDateTime createdAt;// TimeStamp
		private LocalDateTime updatedAt;
		
		public Note() {}

		@PrePersist
		void onCreate() {
			this.createdAt = LocalDateTime.now();
		}

		@PreUpdate
		void onUpdate() {
			this.updatedAt = LocalDateTime.now();
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getNote() {
			return note;
		}

		public void setNote(String note) {
			this.note = note;
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

		@Override
		public String toString() {
			return "Note [id=" + id + ", note=" + note + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
		}

		@Override
		public int hashCode() {
			return Objects.hash(createdAt, id, note, updatedAt);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Note other = (Note) obj;
			return Objects.equals(createdAt, other.createdAt) && Objects.equals(id, other.id)
					&& Objects.equals(note, other.note) && Objects.equals(updatedAt, other.updatedAt);
		}
		
		

}
