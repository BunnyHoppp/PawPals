package pawpals.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
    private String name;
    private String owner; // Link to the user who owns this pet

    private String start;
    private String end;

    @Override
    public String toString() {
        return "Pet{" +
                "name='" + name + '\'' +
                ", owner=" + owner + '\'' +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
