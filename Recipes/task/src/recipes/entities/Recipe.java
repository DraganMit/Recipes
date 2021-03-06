package recipes.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Recipe {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NonNull
    @NotBlank
    private String name;

    @NonNull
    @NotBlank
    private String category;

    @NonNull
    private LocalDateTime date;

    @NonNull
    @NotBlank
    private String description;

    @NonNull
    @NotNull
    @Size(min = 1)
    @ElementCollection
    private List<String> ingredients;

    @NonNull
    @NotNull
    @Size(min = 1)
    @ElementCollection
    private List<String> directions;

    @JsonIgnore
    private String email;

}
