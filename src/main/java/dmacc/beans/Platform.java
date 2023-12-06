package dmacc.beans;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents a gaming platform.
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Platform {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long platformId;

	@Column(nullable = false, unique = true)
	private String name;

	@OneToMany(mappedBy = "platform")
	@ToString.Exclude
	private List<Game> games;

    public Long getId() {
        return null;
    }
}
